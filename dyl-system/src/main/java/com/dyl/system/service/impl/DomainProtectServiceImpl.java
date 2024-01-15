package com.dyl.system.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyl.common.annotation.DataScope;
import com.dyl.common.exception.GlobalException;
import com.dyl.common.exception.ServiceException;
import com.dyl.common.utils.StringUtils;
import com.dyl.common.utils.bean.BeanValidators;
import com.dyl.system.domain.DomainProtect;
import com.dyl.system.domain.dto.DomainProtectSearchDTO;
import com.dyl.system.mapper.DomainProtectMapper;
import com.dyl.system.service.DomainProtectService;
import org.springframework.stereotype.Service;
import org.xbill.DNS.*;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @description 针对表【domain_protect(域名防护表)】的数据库操作Service实现
 */
@Service
public class DomainProtectServiceImpl extends ServiceImpl<DomainProtectMapper, DomainProtect> implements DomainProtectService {
    @Resource
    protected Validator validator;

    /**
     * 查询域名防护管理数据
     */
    @Override
    @DataScope(deptAlias = "p")
    public List<DomainProtect> selectDomainProtectList(DomainProtectSearchDTO domainProtectSearchDTO) {
        LambdaQueryWrapper<DomainProtect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(CharSequenceUtil.isNotBlank(domainProtectSearchDTO.getKeyword()), DomainProtect::getKeyword, domainProtectSearchDTO.getKeyword());
        queryWrapper.like(CharSequenceUtil.isNotBlank(domainProtectSearchDTO.getCompanyProduct()), DomainProtect::getCompanyProduct, domainProtectSearchDTO.getCompanyProduct());
        return list(queryWrapper);
    }

    @Override
    public DomainProtect getDomainProtectByDomain(String domain) {
        if (CharSequenceUtil.isNotBlank(domain)) {
            try {
                String searchString;
                Resolver resolver = new SimpleResolver("114.114.114.114");
                Lookup lookup = new Lookup(domain, Type.CNAME);
                lookup.setResolver(resolver);
                Cache cache = new Cache();
                lookup.setCache(cache);
                lookup.run();
                if (lookup.getResult() == Lookup.SUCCESSFUL) {
                    Record[] records = lookup.getAnswers();
                    for (Record record : records) {
                        String url = record.rdataToString();
                        String regex = "(\\w+\\.)*([a-zA-Z0-9]+\\.[a-zA-Z]+)";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(url);
                        if (matcher.find()) {
                            searchString = matcher.group(2);
                            return getDomainProtectByKeyword(searchString);
                        }
                    }
                }
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 新增信息
     */
    @Override
    public void addDomainProtect(DomainProtect domainProtect) {
        save(domainProtect);
    }

    /**
     * 更新信息
     */
    @Override
    public void updateDomainProtect(DomainProtect domainProtect) {
        domainProtect.setUpdateTime(new Date());
        updateById(domainProtect);
    }

    /**
     * 更新信息
     */
    @Override
    public DomainProtect getDomainProtectByKeyword(String keyword) {
        LambdaQueryWrapper<DomainProtect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DomainProtect::getKeyword, keyword);
        return getOne(queryWrapper, false);
    }

    /**
     * 导入数据
     */
    @Override
    public String importDomainProtect(List<DomainProtect> domainProtectList, Boolean isUpdateSupport, String operaName) {
        if (StringUtils.isNull(domainProtectList) || domainProtectList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DomainProtect domainProtect : domainProtectList) {
            try {
                // 验证是否存在这个CNAME关键词
                DomainProtect u = getDomainProtectByKeyword(domainProtect.getKeyword());
                if (StringUtils.isNull(u)) {
                    BeanValidators.validateWithException(validator, domainProtect);
                    domainProtect.setCreateBy(operaName);
                    this.addDomainProtect(domainProtect);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、CNAME关键词 " + domainProtect.getKeyword() + " 导入成功");
                } else if (isUpdateSupport) {
                    BeanValidators.validateWithException(validator, domainProtect);
//                    checkUserAllowed(user);
//                    checkUserDataScope(user.getUserId());
                    domainProtect.setUpdateBy(operaName);
                    this.updateDomainProtect(domainProtect);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、CNAME关键词 " + domainProtect.getKeyword() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、CNAME关键词 " + domainProtect.getKeyword() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、CNAME关键词 " + domainProtect.getKeyword() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}




