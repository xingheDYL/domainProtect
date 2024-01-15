package com.dyl.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dyl.common.api.SystemApi;
import com.dyl.common.core.domain.entity.SysOperLog;
import com.dyl.system.service.ISysOperLogService;

/**
 * 系统管理api接口提供者
 *
 * @author dyl
 **/
@Service
public class SystemApiImpl implements SystemApi {

    @Resource
    private ISysOperLogService sysOperLogService;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        sysOperLogService.insertOperlog(operLog);
    }

}
