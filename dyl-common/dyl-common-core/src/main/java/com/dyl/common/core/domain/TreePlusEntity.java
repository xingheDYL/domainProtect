package com.dyl.common.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Tree基类
 *
 * @author dyl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreePlusEntity extends BasePlusEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 子部门
     */
    @TableField(exist = false)
    private List<?> children = new ArrayList<>();
}
