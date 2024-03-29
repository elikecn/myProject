package com.jspxcms.plug.web.back;

import static com.jspxcms.core.support.Constants.CREATE;
import static com.jspxcms.core.support.Constants.DELETE_SUCCESS;
import static com.jspxcms.core.support.Constants.EDIT;
import static com.jspxcms.core.support.Constants.MESSAGE;
import static com.jspxcms.core.support.Constants.OPRT;
import static com.jspxcms.core.support.Constants.SAVE_SUCCESS;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jspxcms.common.orm.LimitRequest;
import com.jspxcms.common.orm.Limitable;
import com.jspxcms.common.orm.RowSide;
import com.jspxcms.common.web.Servlets;
import com.jspxcms.core.service.OperationLogService;
import com.jspxcms.core.support.Constants;
import com.jspxcms.core.support.Context;
import com.jspxcms.plug.domain.Resume;
import com.jspxcms.plug.service.ResumeService;

@Controller
@RequestMapping("/plug/resume")
public class ResumeController {
	private static final Logger logger = LoggerFactory
			.getLogger(ResumeController.class);

	@RequiresPermissions("plug:resume:list")
	@RequestMapping("list.do")
	public String list(
			@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap) {
		Integer siteId = Context.getCurrentSiteId(request);
		Map<String, String[]> params = Servlets.getParameterValuesMap(request,
				Constants.SEARCH_PREFIX);
		Page<Resume> pagedList = service.findAll(siteId, params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		return "plug/resume/resume_list";
	}

	@RequiresPermissions("plug:resume:create")
	@RequestMapping("create.do")
	public String create(Integer id, org.springframework.ui.Model modelMap) {
		if (id != null) {
			Resume bean = service.get(id);
			modelMap.addAttribute("bean", bean);
		}
		modelMap.addAttribute(OPRT, CREATE);
		return "plug/resume/resume_form";
	}

	@RequiresPermissions("plug:resume:edit")
	@RequestMapping("edit.do")
	public String edit(
			Integer id,
			Integer position,
			@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap) {
		Integer siteId = Context.getCurrentSiteId(request);
		Resume bean = service.get(id);
		Map<String, String[]> params = Servlets.getParameterValuesMap(request,
				Constants.SEARCH_PREFIX);
		RowSide<Resume> side = service.findSide(siteId, params, bean, position,
				pageable.getSort());
		modelMap.addAttribute("bean", bean);
		modelMap.addAttribute("side", side);
		modelMap.addAttribute("position", position);
		modelMap.addAttribute(OPRT, EDIT);
		return "plug/resume/resume_form";
	}

	@RequiresPermissions("plug:resume:save")
	@RequestMapping("save.do")
	public String save(@Valid Resume bean, String redirect,
			HttpServletRequest request, RedirectAttributes ra) {
		Integer siteId = Context.getCurrentSiteId(request);
		service.save(bean, siteId);
		logService.operation("opr.resume.add", bean.getName(), null,
				bean.getId(), request);
		logger.info("save Resume, name={}.", bean.getName());
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if (Constants.REDIRECT_LIST.equals(redirect)) {
			return "redirect:list.do";
		} else if (Constants.REDIRECT_CREATE.equals(redirect)) {
			return "redirect:create.do";
		} else {
			ra.addAttribute("id", bean.getId());
			return "redirect:edit.do";
		}
	}

	@RequiresPermissions("plug:resume:update")
	@RequestMapping("update.do")
	public String update(@ModelAttribute("bean") Resume bean, Integer position,
			String redirect, HttpServletRequest request, RedirectAttributes ra) {
		service.update(bean);
		logService.operation("opr.resume.edit", bean.getName(), null,
				bean.getId(), request);
		logger.info("update Resume, name={}.", bean.getName());
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if (Constants.REDIRECT_LIST.equals(redirect)) {
			return "redirect:list.do";
		} else {
			ra.addAttribute("id", bean.getId());
			ra.addAttribute("position", position);
			return "redirect:edit.do";
		}
	}

	@RequiresPermissions("plug:resume:delete")
	@RequestMapping("delete.do")
	public String delete(Integer[] ids, HttpServletRequest request,
			RedirectAttributes ra) {
		Resume[] beans = service.delete(ids);
		for (Resume bean : beans) {
			logService.operation("opr.resume.delete", bean.getName(), null,
					bean.getId(), request);
			logger.info("delete Resume, name={}.", bean.getName());
		}
		ra.addFlashAttribute(MESSAGE, DELETE_SUCCESS);
		return "redirect:list.do";
	}
	
	@RequiresPermissions("plug:resume:export")
	@RequestMapping("export.do")
    public String exportXls(Resume course,HttpServletRequest request,HttpServletResponse response
            ,ModelMap map) {
		Integer siteId = Context.getCurrentSiteId(request);
		Sort sort = new Sort("id");
		Limitable limitable = new LimitRequest(null, null, sort);
		List<Resume> resumes = service.findList(new Integer[]{siteId}, limitable);
		for (Resume bean : resumes) {
			logService.operation("opr.resume.export", bean.getName(), null,
					bean.getId(), request);
		}
        map.put(NormalExcelConstants.FILE_NAME,"用户信息");
        map.put(NormalExcelConstants.CLASS,Resume.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("课程列表", "导出人:Jeecg", "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,resumes);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }
	@ModelAttribute("bean")
	public Resume preloadBean(@RequestParam(required = false) Integer oid) {
		return oid != null ? service.get(oid) : null;
	}

	@Autowired
	private OperationLogService logService;
	@Autowired
	private ResumeService service;
}
