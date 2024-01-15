package com.dyl.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.dyl.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户和岗位关联 sys_user_project
 *
 * @author dyl
 */
public class SysUserProject extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 支撑开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date supportStartTime;

    /**
     * 支撑结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date supportEndTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Date getSupportStartTime() {
        return supportStartTime;
    }

    public void setSupportStartTime(Date supportStartTime) {
        this.supportStartTime = supportStartTime;
    }

    public Date getSupportEndTime() {
        return supportEndTime;
    }

    public void setSupportEndTime(Date supportEndTime) {
        this.supportEndTime = supportEndTime;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("projectId", getProjectId())
                .append("supportStartTime", getSupportStartTime())
                .append("supportEndTime", getSupportEndTime())
                .toString();
    }
}
