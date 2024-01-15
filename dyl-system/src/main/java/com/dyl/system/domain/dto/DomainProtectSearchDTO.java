package com.dyl.system.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 域名防护搜索DTO
 *
 * @author dyl
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DomainProtectSearchDTO extends SearchDTO {
    /**
     * CNAME关键词
     */
    private String keyword;

    /**
     * 防护产品
     */
    private String companyProduct;

    /**
     * 域名
     */
    private List<String> domainList;
}

