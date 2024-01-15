package com.dyl.common.api;

import com.dyl.common.core.domain.entity.SysOperLog;

/**
 * 系统管理api接口
 *
 * @author dyl
 **/
public interface SystemApi {

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLog operLog);
}
