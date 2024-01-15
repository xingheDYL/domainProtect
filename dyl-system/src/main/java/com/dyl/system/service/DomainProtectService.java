package com.dyl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyl.system.domain.DomainProtect;
import com.dyl.system.domain.dto.DomainProtectSearchDTO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【domain_protect(域名防护表)】的数据库操作Service
 */
public interface DomainProtectService extends IService<DomainProtect> {

    /**
     * 查询域名防护管理数据
     */
    List<DomainProtect> selectDomainProtectList(DomainProtectSearchDTO domainProtectSearchDTO);

    /**
     * 通过域名获取域名防护管理数据
     */
    DomainProtect getDomainProtectByDomain(String domain);

    /**
     * 新增信息
     */
    void addDomainProtect(DomainProtect domainProtect);

    /**
     * 更新信息
     */
    void updateDomainProtect(DomainProtect domainProtect);

    DomainProtect getDomainProtectByKeyword(String keyword);

    /**
     * 导入数据
     */
    String importDomainProtect(List<DomainProtect> domainProtectList, Boolean isUpdateSupport, String operaName);
}
