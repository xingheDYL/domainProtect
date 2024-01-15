package com.dyl.system.service.impl;

import com.dyl.common.annotation.DataScope;
import com.dyl.system.domain.SysUserProject;
import com.dyl.system.mapper.SysUserProjectMapper;
import com.dyl.system.service.ISysUserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员项目管理 服务实现
 *
 * @author dyl
 */
@Service
public class SysUserProjectServiceImpl implements ISysUserProjectService {

    @Autowired
    private SysUserProjectMapper userProjectMapper;

    /**
     * 根据用户ID查询人员项目信息
     *
     * @param userId 用户ID
     * @return 选中人员项目列表
     */
    @Override
    public List<SysUserProject> selectUserProjectListByUserId(Long userId) {
        return userProjectMapper.selectUserProjectListByUserId(userId);
    }

    /**
     * 查询用户项目管理数据
     *
     * @param userProject 用户项目信息
     * @return 用户项目信息集合
     */
    @Override
    @DataScope(deptAlias = "p")
    public List<SysUserProject> selectProjectList(SysUserProject userProject) {
        return userProjectMapper.selectUserProjectList(userProject);
    }
}

