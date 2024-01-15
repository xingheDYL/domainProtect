package com.dyl.system.domain;

import com.dyl.common.annotation.Excel;
import com.dyl.common.annotation.Excel.ColumnType;
import com.dyl.common.annotation.Excel.Type;
import com.dyl.common.core.domain.BaseEntity;
import com.dyl.common.core.domain.entity.SysRole;
import com.dyl.common.core.domain.entity.SysUser;
import com.dyl.common.xss.Xss;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 项目对象 sys_project
 *
 * @author dyl
 */
public class SysProject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    @Excel(name = "项目序号", cellType = ColumnType.NUMERIC, prompt = "项目编号")
    private Long projectId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 项目负责人
     */
    private String projectLeaderName;

    /**
     * 项目成员
     */
    @Excel(name = "项目成员", type = Type.IMPORT)
    private Long userId;

    /**
     * 成员对象
     */
    private List<SysUser> users;

    /**
     * 项目投入
     */
//    @Excel(name = "项目投入")
    private String projectInput;

    /**
     * 项目产值
     */
//    @Excel(name = "项目产值")
    private String projectOutput;

    /**
     * 项目进度
     */
//    @Excel(name = "项目进度")
    private String projectProgress;

    /**
     * 项目内容
     */
//    @Excel(name = "项目内容")
    private String projectContent;

    /**
     * 状态（0完成 1未完成）
     */
    @Excel(name = "项目状态", readConverterExp = "0=完成,1=未完成")
    private String status;

    /**
     * 项目开始时间
     */
    @Excel(name = "项目开始时间")
    private Date startTime;

    /**
     * 项目结束时间
     */
    @Excel(name = "项目结束时间")
    private Date endTime;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public SysProject() {

    }

    public SysProject(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Xss(message = "项目昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "项目昵称长度不能超过30个字符")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectLeaderName() {
        return projectLeaderName;
    }

    public void setProjectLeaderName(String projectLeaderName) {
        this.projectLeaderName = projectLeaderName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    public String getProjectInput() {
        return projectInput;
    }

    public void setProjectInput(String projectInput) {
        this.projectInput = projectInput;
    }

    public String getProjectOutput() {
        return projectOutput;
    }

    public void setProjectOutput(String projectOutput) {
        this.projectOutput = projectOutput;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    @Override
    public String toString() {
        return "SysProject{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectLeaderName='" + projectLeaderName + '\'' +
                ", userId=" + userId +
                ", projectInput='" + projectInput + '\'' +
                ", projectOutput='" + projectOutput + '\'' +
                ", projectProgress='" + projectProgress + '\'' +
                ", projectContent='" + projectContent + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sort=" + sort +
                '}';
    }
}
