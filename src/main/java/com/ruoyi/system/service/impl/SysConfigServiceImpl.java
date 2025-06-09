package com.ruoyi.system.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 参数配置 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Autowired
    private SysConfigMapper configMapper;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        loadingConfigCache();
    }

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public SysConfig selectConfigById(Long configId) {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return configMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     * <p>
     * 首先尝试从缓存中获取配置信息，如果缓存中不存在，则从数据库中查询
     * 如果数据库中存在该配置信息，则将其值存入缓存中，以优化后续查询性能
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        // 从缓存中获取配置值
        String configValue = Convert.toStr(CacheUtils.get(getCacheName(), getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue)) {
            // 如果缓存中存在配置值，则直接返回
            return configValue;
        }

        // 如果缓存中不存在配置值，则创建一个新的SysConfig对象，并设置其配置键值为传入的configKey
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);

        // 从数据库中查询配置信息
        SysConfig retConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig)) {
            // 如果数据库中查询到了配置信息，则将其值存入缓存，并返回该值
            CacheUtils.put(getCacheName(), getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }

        // 如果数据库中也未查询到配置信息，则返回空字符串
        return StringUtils.EMPTY;
    }


    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return configMapper.selectConfigList(config);
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config) {
        int row = configMapper.insertConfig(config);
        if (row > 0) {
            CacheUtils.put(getCacheName(), getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config) {
        // 根据配置ID查询原有的配置信息
        SysConfig temp = configMapper.selectConfigById(config.getConfigId());
        // 检查配置键是否被修改，如果被修改，则移除旧的配置键在缓存中的值
        if (!StringUtils.equals(temp.getConfigKey(), config.getConfigKey())) {
            CacheUtils.remove(getCacheName(), getCacheKey(temp.getConfigKey()));
        }

        // 更新配置信息
        int row = configMapper.updateConfig(config);
        // 如果更新成功，将新的配置值存入缓存
        if (row > 0) {
            CacheUtils.put(getCacheName(), getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        // 返回更新结果
        return row;
    }


    /**
     * 批量删除参数配置对象
     *
     * @param ids 需要删除的数据ID
     */
    @Override
    public void deleteConfigByIds(String ids) {
        // 将字符串ID数组转换为Long型数组
        Long[] configIds = Convert.toLongArray(ids);
        for (Long configId : configIds) {
            // 根据ID查询参数配置对象
            SysConfig config = selectConfigById(configId);
            // 检查参数类型，如果为内置参数，则抛出异常，不允许删除
            if (StringUtils.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            // 删除参数配置
            configMapper.deleteConfigById(configId);
            // 从缓存中移除对应的参数配置
            CacheUtils.remove(getCacheName(), getCacheKey(config.getConfigKey()));
        }
    }


    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
        List<SysConfig> configsList = configMapper.selectConfigList(new SysConfig());
        for (SysConfig config : configsList) {
            CacheUtils.put(getCacheName(), getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
        CacheUtils.removeAll(getCacheName());
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public boolean checkConfigKeyUnique(SysConfig config) {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    private String getCacheName() {
        return Constants.SYS_CONFIG_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
