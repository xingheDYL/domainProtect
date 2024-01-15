package com.dyl.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dyl.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 域名防护表
 */
@TableName(value = "domain_protect")
@Data
public class DomainProtect implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * CNAME关键词
     */
    @Excel(name = "CNAME关键词", cellType = Excel.ColumnType.STRING, prompt = "CNAME关键词")
    private String keyword;

    /**
     * 防护产品
     */
    @Excel(name = "防护产品", cellType = Excel.ColumnType.STRING, prompt = "防护产品")
    private String companyProduct;

    /**
     * 企业全称
     */
    @Excel(name = "企业全称", cellType = Excel.ColumnType.STRING, prompt = "企业全称")
    private String companyName;

    /**
     * 企业官网
     */
    @Excel(name = "企业官网", cellType = Excel.ColumnType.STRING, prompt = "企业官网")
    private String companyWebsite;

    /**
     * 企业介绍
     */
    @Excel(name = "企业介绍", cellType = Excel.ColumnType.STRING, prompt = "企业介绍")
    private String companyDescription;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 删除标志（0-未删除，1-删除）
     */
    private String delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}