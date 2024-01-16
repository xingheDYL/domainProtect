package com.dyl.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 域名防护VO层
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DomainProtectVO extends DomainProtect {
    @TableField(exist = false)
    private String domain;
}