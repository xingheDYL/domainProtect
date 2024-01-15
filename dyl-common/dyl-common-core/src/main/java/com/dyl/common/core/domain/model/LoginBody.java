package com.dyl.common.core.domain.model;

import lombok.Data;

/**
 * 用户登录对象
 *
 * @author dyl
 */
@Data
public class LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
