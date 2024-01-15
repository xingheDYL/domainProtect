package com.dyl.system.service.impl;

import com.dyl.common.annotation.DataScope;
import com.dyl.common.constant.UserConstants;
import com.dyl.common.core.domain.entity.SysUser;
import com.dyl.common.exception.ServiceException;
import com.dyl.common.utils.SecurityUtils;
import com.dyl.common.utils.StringUtils;
import com.dyl.common.utils.spring.SpringUtils;
import com.dyl.system.domain.SysPost;
import com.dyl.system.domain.SysProject;
import com.dyl.system.domain.SysUserProject;
import com.dyl.system.mapper.SysProjectMapper;
import com.dyl.system.mapper.SysUserProjectMapper;
import com.dyl.system.service.ISysProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目管理 服务实现
 *
 * @author dyl
 */
@Service
public class SysProjectServiceImpl implements ISysProjectService {
    @Autowired
    private SysProjectMapper projectMapper;

    @Autowired
    private SysUserProjectMapper userProjectMapper;


    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysProject> selectProjectAll() {
        return projectMapper.selectProjectAll();
    }

    /**
     * 查询项目管理数据
     *
     * @param project 项目信息
     * @return 项目信息集合
     */
    @Override
    @DataScope(deptAlias = "p")
    public List<SysProject> selectProjectList(SysProject project) {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 根据项目ID查询信息
     *
     * @param projectId 项目ID
     * @return 项目信息
     */
    @Override
    public SysProject selectProjectById(Long projectId) {
        return projectMapper.selectProjectById(projectId);
    }

    /**
     * 根据用户ID获取项目列表
     *
     * @param userId 用户ID
     * @return 选中项目ID列表
     */
    @Override
    public List<SysProject> selectProjectListByUserId(Long userId) {
        return projectMapper.selectProjectListByUserId(userId);
    }

    /**
     * 查询项目是否存在用户
     *
     * @param projectId 项目ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkProjectExistUser(Long projectId) {
        int result = projectMapper.checkProjectExistUser(projectId);
        return result > 0;
    }

    /**
     * 校验项目名称是否唯一
     *
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public boolean checkProjectNameUnique(SysProject project) {
        Long projectId = StringUtils.isNull(project.getProjectId()) ? -1L : project.getProjectId();
        SysProject info = projectMapper.checkProjectNameUnique(project.getProjectName());
        if (StringUtils.isNotNull(info) && info.getProjectId().longValue() != projectId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验项目是否有数据权限
     *
     * @param projectId 项目id
     */
    @Override
    public void checkProjectDataScope(Long projectId) {
        if (!SysUser.isAdmin(SecurityUtils.getUserId())) {
            SysProject project = new SysProject();
            project.setProjectId(projectId);
            List<SysProject> projects = SpringUtils.getAopProxy(this).selectProjectList(project);
            if (StringUtils.isEmpty(projects)) {
                throw new ServiceException("没有权限访问项目数据！");
            }
        }
    }

    /**
     * 新增保存项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public int insertProject(SysProject project) {
        return projectMapper.insertProject(project);
    }

    /**
     * 修改保存项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public int updateProject(SysProject project) {
        return projectMapper.updateProject(project);
    }

    /**
     * 删除项目管理信息
     *
     * @param projectId 项目ID
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long projectId) {
        return projectMapper.deleteProjectById(projectId);
    }

    /**
     * 取消授权用户项目
     *
     * @param userProject 用户和项目关联信息
     * @return 结果
     */
    @Override
    public int deleteAuthUser(SysUserProject userProject) {
        return userProjectMapper.deleteUserProjectInfo(userProject);
    }

    /**
     * 批量取消授权用户项目
     *
     * @param projectId 项目ID
     * @param userIds   需要取消授权的用户数据ID
     * @return 结果
     */
    @Override
    public int deleteAuthUsers(Long projectId, Long[] userIds) {
        return userProjectMapper.deleteUserProjectInfos(projectId, userIds);
    }

    /**
     * 批量选择授权用户角色
     *
     * @param projectId 项目ID
     * @param userIds   需要授权的用户数据ID
     * @return 结果
     */
    @Override
    public int insertAuthUsers(Long projectId, Long[] userIds) {
        // 新增用户与项目管理
        List<SysUserProject> list = new ArrayList<>();
        for (Long userId : userIds) {
            SysUserProject ur = new SysUserProject();
            ur.setUserId(userId);
            ur.setProjectId(projectId);
            list.add(ur);
        }
        return userProjectMapper.batchUserProject(list);
    }
}
