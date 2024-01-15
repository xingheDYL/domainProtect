package com.dyl.system.mapper;

import com.dyl.system.domain.SysProject;

import java.util.List;

/**
 * 项目管理 数据层
 *
 * @author dyl
 */
public interface SysProjectMapper {

    /**
     * 查询所有项目
     *
     * @return 项目列表
     */
    public List<SysProject> selectProjectAll();

    /**
     * 查询项目管理数据
     *
     * @param project 项目信息
     * @return 项目信息集合
     */
    public List<SysProject> selectProjectList(SysProject project);

    /**
     * 根据用户ID获取项目选择框列表
     *
     * @param userId 用户ID
     * @return 选中项目ID列表
     */
    public List<SysProject> selectProjectListByUserId(Long userId);

    /**
     * 根据项目ID查询信息
     *
     * @param projectId 项目ID
     * @return 项目信息
     */
    public SysProject selectProjectById(Long projectId);

    /**
     * 查询项目是否存在用户
     *
     * @param projectId 项目ID
     * @return 结果
     */
    public int checkProjectExistUser(Long projectId);

    /**
     * 校验项目名称
     *
     * @param projectName 项目名称
     * @return 结果
     */
    public SysProject checkProjectNameUnique(String projectName);


    /**
     * 新增项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(SysProject project);

    /**
     * 修改项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(SysProject project);


    /**
     * 删除项目管理信息
     *
     * @param projectId 项目ID
     * @return 结果
     */
    public int deleteProjectById(Long projectId);
}
