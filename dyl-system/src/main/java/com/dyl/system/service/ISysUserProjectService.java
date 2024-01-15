package com.dyl.system.service;

import com.dyl.system.domain.SysUserProject;

import java.util.List;

/**
 * 人员项目管理 服务层
 *
 * @author dyl
 */
public interface ISysUserProjectService {
    /**
     * 根据用户ID获取项目列表
     *
     * @param userId 用户ID
     * @return 选中项目ID列表
     */
    public List<SysUserProject> selectUserProjectListByUserId(Long userId);

    /**
     * 查询用户项目管理数据
     *
     * @param userProject 用户项目信息
     * @return 用户项目信息集合
     */
    public List<SysUserProject> selectProjectList(SysUserProject userProject);

}
