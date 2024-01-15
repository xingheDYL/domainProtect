package com.dyl.system.service;

import com.dyl.system.domain.SysPost;
import com.dyl.system.domain.SysProject;
import com.dyl.system.domain.SysUserProject;

import java.util.List;
/**
 * 项目管理 服务层
 *
 * @author dyl
 */
public interface ISysProjectService {

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
     * 根据项目ID查询信息
     *
     * @param projectId 项目ID
     * @return 项目信息
     */
    public SysProject selectProjectById(Long projectId);

    /**
     * 根据用户ID获取项目列表
     *
     * @param userId 用户ID
     * @return 选中项目ID列表
     */
    public List<SysProject> selectProjectListByUserId(Long userId);

    /**
     * 查询项目是否存在用户
     *
     * @param projectId 项目ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkProjectExistUser(Long projectId);

    /**
     * 校验项目名称是否唯一
     *
     * @param project 项目信息
     * @return 结果
     */
    public boolean checkProjectNameUnique(SysProject project);

    /**
     * 校验项目是否有数据权限
     *
     * @param projectId 项目id
     */
    public void checkProjectDataScope(Long projectId);

    /**
     * 新增保存项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(SysProject project);

    /**
     * 修改保存项目信息
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

    /**
     * 取消授权用户项目
     *
     * @param userProject 用户和项目关联信息
     * @return 结果
     */
    public int deleteAuthUser(SysUserProject userProject);

    /**
     * 批量取消授权用户角色
     *
     * @param projectId  角色ID
     * @param userIds 需要取消授权的用户数据ID
     * @return 结果
     */
    public int deleteAuthUsers(Long projectId, Long[] userIds);

    /**
     * 批量选择授权用户角色
     *
     * @param projectId  项目ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int insertAuthUsers(Long projectId, Long[] userIds);
}
