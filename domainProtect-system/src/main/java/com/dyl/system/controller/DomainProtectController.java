package com.dyl.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.dyl.common.core.controller.BaseController;
import com.dyl.common.core.domain.AjaxResult;
import com.dyl.common.core.page.TableDataInfo;
import com.dyl.common.utils.poi.ExcelUtil;
import com.dyl.system.domain.DomainProtect;
import com.dyl.system.domain.DomainProtectVO;
import com.dyl.system.domain.dto.DomainProtectSearchDTO;
import com.dyl.system.service.DomainProtectService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 域名防护controller层
 *
 * @author xinghe
 */
@RestController
@RequestMapping("/tool/domainProtect")
public class DomainProtectController extends BaseController {
    @Resource
    private DomainProtectService domainProtectService;

    /**
     * 查询域名防护管理数据
     */
    @GetMapping("/list")
    public TableDataInfo list(DomainProtectSearchDTO domainProtectSearchDTO) {
        startPage();
        List<DomainProtect> domainProtects = domainProtectService.selectDomainProtectList(domainProtectSearchDTO);
        return getDataTable(domainProtects);
    }

    /**
     * 导出信息
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, DomainProtectSearchDTO domainProtectSearchDTO) {
        List<DomainProtect> list = domainProtectService.selectDomainProtectList(domainProtectSearchDTO);
        ExcelUtil<DomainProtect> util = new ExcelUtil<>(DomainProtect.class);
        util.exportExcel(response, list, "防护信息");
    }

    /**
     * 根据ID获取详细信息
     */
    @GetMapping(value = {"/", "/{id}"})
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) String id) {
        AjaxResult ajax = AjaxResult.success();
        if (CharSequenceUtil.isNotBlank(id)) {
            DomainProtect domainProtect = domainProtectService.getById(id);
            ajax.put(AjaxResult.DATA_TAG, domainProtect);
        }
        return ajax;
    }

    /**
     * 通过域名获取域名防护管理数据
     */
    @GetMapping(value = {"/getDomainProtectByDomain/{domain}"})
    public TableDataInfo getDomainProtectByDomain(@PathVariable(value = "domain", required = false) String domain) {
        startPage();
        List<DomainProtectVO> domainProtectVOList = new ArrayList<>();
        DomainProtectVO domainProtectVO = new DomainProtectVO();
        DomainProtect domainProtect = domainProtectService.getDomainProtectByDomain(domain);
        if (domainProtect != null) {
            domainProtectVO = BeanUtil.copyProperties(domainProtect, DomainProtectVO.class);
        }
        domainProtectVO.setDomain(domain);
        domainProtectVOList.add(domainProtectVO);
        return getDataTable(domainProtectVOList);
    }

    /**
     * 新增信息
     */
    @PostMapping
    public AjaxResult addDomainProtect(@Validated @RequestBody DomainProtect domainProtect) {
        domainProtect.setCreateBy(getUsername());
        domainProtectService.addDomainProtect(domainProtect);
        return AjaxResult.success();
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<DomainProtect> util = new ExcelUtil<>(DomainProtect.class);
        List<DomainProtect> domainProtectList = util.importExcel(file.getInputStream());
        String operaName = getUsername();
        String message = domainProtectService.importDomainProtect(domainProtectList, updateSupport, operaName);
        return success(message);
    }

    /**
     * 导入模板
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<DomainProtect> util = new ExcelUtil<>(DomainProtect.class);
        util.importTemplateExcel(response, "域名防护模版表");
    }

    /**
     * 修改信息
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody DomainProtect domainProtect) {
        domainProtect.setUpdateBy(getUsername());
        domainProtectService.updateDomainProtect(domainProtect);
        return AjaxResult.success();
    }

    /**
     * 删除信息
     */
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(domainProtectService.removeById(id));
    }
}
