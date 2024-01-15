package com.dyl.system.mapper;

import com.dyl.system.domain.SysUserProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与项目关联表 数据层
 *
 * @author dyl
 */
public interface SysUserProjectMapper {

    /**
     * 通过用户ID删除用户和项目关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserProjectByUserId(Long userId);

    /**
     * 通过项目ID查询项目使用数量
     *
     * @param projectId 项目ID
     * @return 结果
     */
    public int countUserProjectById(Long projectId);

    /**
     * 查询用户项目管理数据
     *
     * @param userProject 用户项目信息
     * @return 用户项目信息集合
     */
    public List<SysUserProject> selectUserProjectList(SysUserProject userProject);

    /**
     * 通过用户ID查询项目使用数量
     *
     * @param userId 用户ID
     * @return 结果
     */
    public List<SysUserProject> selectUserProjectListByUserId(Long userId);

    /**
     * 批量删除用户和项目关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserProject(Long[] ids);

    /**
     * 批量新增用户项目信息
     *
     * @param userProjectList 用户项目列表
     * @return 结果
     */
    public int batchUserProject(List<SysUserProject> userProjectList);


    /**
     * 删除用户和项目关联信息
     *
     * @param userProject 用户和项目关联信息
     * @return 结果
     */
    public int deleteUserProjectInfo(SysUserProject userProject);

    /**
     * 批量取消授权用户角色
     *
     * @param projectId  项目ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteUserProjectInfos(@Param("projectId") Long projectId, @Param("userIds") Long[] userIds);
}
