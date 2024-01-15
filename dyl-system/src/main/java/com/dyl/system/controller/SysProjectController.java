package com.dyl.system.controller;

import com.dyl.common.core.domain.entity.SysUser;
import com.dyl.common.log.annotation.Log;
import com.dyl.common.core.controller.BaseController;
import com.dyl.common.core.domain.AjaxResult;
import com.dyl.common.core.page.TableDataInfo;
import com.dyl.common.enums.BusinessType;
import com.dyl.common.utils.poi.ExcelUtil;
import com.dyl.system.domain.SysProject;
import com.dyl.system.domain.SysUserProject;
import com.dyl.system.service.ISysProjectService;
import com.dyl.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目信息
 *
 * @author dyl
 */
@RestController
@RequestMapping("/system/project")
public class SysProjectController extends BaseController {
    @Autowired
    private ISysProjectService projectService;

    @Autowired
    private ISysUserService userService;

    /**
     * 获取项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysProject project) {
        startPage();
        List<SysProject> projects = projectService.selectProjectList(project);
        return getDataTable(projects);
    }

    /**
     * 导出项目信息
     */
    @Log(title = "项目管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:project:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysProject project) {
        List<SysProject> list = projectService.selectProjectList(project);
        ExcelUtil<SysProject> util = new ExcelUtil<SysProject>(SysProject.class);
        util.exportExcel(response, list, "项目数据");
    }

    /**
     * 根据项目编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable Long projectId) {
        projectService.checkProjectDataScope(projectId);
        return success(projectService.selectProjectById(projectId));
    }

    /**
     * 新增项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysProject project) {
        if (!projectService.checkProjectNameUnique(project)) {
            return error("新增项目'" + project.getProjectName() + "'失败，项目名称已存在");
        }
        project.setCreateBy(getUsername());
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysProject project) {
        Long projectId = project.getProjectId();
        projectService.checkProjectDataScope(projectId);
        if (!projectService.checkProjectNameUnique(project)) {
            return error("修改项目'" + project.getProjectName() + "'失败，项目名称已存在");
        }
        project.setUpdateBy(getUsername());
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectId}")
    public AjaxResult remove(@PathVariable Long projectId) {
        if (projectService.checkProjectExistUser(projectId)) {
            return warn("项目存在用户,不允许删除");
        }
        projectService.checkProjectDataScope(projectId);
        return toAjax(projectService.deleteProjectById(projectId));
    }

    /**
     * 查询已分配用户项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:project:list')")
    @GetMapping("/authUser/allocatedList")
    public TableDataInfo allocatedProjectList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectAllocatedProjectsList(user);
        return getDataTable(list);
    }

    /**
     * 查询未分配用户项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:project:list')")
    @GetMapping("/authUser/unallocatedList")
    public TableDataInfo unallocatedProjectList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUnallocatedProjectList(user);
        return getDataTable(list);
    }

    /**
     * 取消分配用户
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancel")
    public AjaxResult cancelAuthUser(@RequestBody SysUserProject userProject) {
        return toAjax(projectService.deleteAuthUser(userProject));
    }

    /**
     * 批量取消分配用户
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancelAll")
    public AjaxResult cancelAuthUserAll(Long projectId, Long[] userIds) {
        return toAjax(projectService.deleteAuthUsers(projectId, userIds));
    }

    /**
     * 批量选择用户分配
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/selectAll")
    public AjaxResult selectAuthUserAll(Long projectId, Long[] userIds) {
        projectService.checkProjectDataScope(projectId);
        return toAjax(projectService.insertAuthUsers(projectId, userIds));
    }
}
