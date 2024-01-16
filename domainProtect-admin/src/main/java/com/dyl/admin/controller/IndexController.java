package com.dyl.admin.controller;

import com.dyl.common.config.BaseConfig;
import com.dyl.common.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 首页
 *
 * @author dyl
 */
@RestController
public class IndexController {
    /**
     * 系统基础配置
     */
    @Resource
    private BaseConfig baseConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", baseConfig.getName(), baseConfig.getVersion());
    }
}
