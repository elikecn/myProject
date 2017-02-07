package com.jspxcms.abc.web.back;

import static com.jspxcms.core.support.Constants.CREATE;
import static com.jspxcms.core.support.Constants.EDIT;
import static com.jspxcms.core.support.Constants.MESSAGE;
import static com.jspxcms.core.support.Constants.OPRT;
import static com.jspxcms.core.support.Constants.SAVE_SUCCESS;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jspxcms.abc.domain.Demo;
import com.jspxcms.abc.service.DemoService;
import com.jspxcms.common.web.Servlets;
import com.jspxcms.core.support.Constants;
import com.jspxcms.core.support.Context;

@Controller
@RequestMapping("/abc/demo")
public class DemoController {
	
	@Autowired
	private DemoService service;
	
	@RequiresPermissions("abc:demo:list")
	@RequestMapping("list.do")
	public String list(
			@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable
			,HttpServletRequest request,Model model) {
		Integer siteId = Context.getCurrentSiteId(request);
		Map<String, String[]> params = Servlets.getParameterValuesMap(request, Constants.SEARCH_PREFIX);
		Page<Demo> pageList = service.findAll(siteId, params, pageable);
		model.addAttribute("pagedList", pageList);
		return "abc/demo/list";
	}

	@RequiresPermissions("abc:demo:create")
	@RequestMapping("create.do")
	public String create(Integer id,Model model) {
		if(id != null){
			Demo demo = service.findOne(id);
			model.addAttribute("demo", demo);
		}
		model.addAttribute(OPRT, CREATE);
		return "abc/demo/create";
	}
	
	@RequiresPermissions("abc:demo:save")
	@RequestMapping("save.do")
	public String save(@Valid Demo bean,HttpServletRequest request,
			RedirectAttributes ra,String redirect){
		Integer siteId = Context.getCurrentSiteId(request);
		service.save(bean, siteId);
		//为了防止用户刷新重复提交，save操作之后一般会redirect到另一个页面，同时带点操作成功的提示信息。
		//因为是Redirect，Request里的attribute不会传递过去，
		//如果放在session中，则需要在显示后及时清理，不然下面每一页都带着这个信息也不对。
		//Spring在3.1才提供了这个能力
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if(Constants.REDIRECT_LIST.equals(redirect)){
			return "redirect:list.do";
		}else if (Constants.REDIRECT_CREATE.equals(redirect)) {
			return "redirect:create.do";
		}else {
			ra.addAttribute("id", bean.getId());
			return "redirect:edit.do";
		}
	}
	
	@RequiresPermissions("abc:demo:edit")
	@RequestMapping("edit.do")
	public String edit(Integer id,Model model){
		Demo demo = service.findOne(id);
		model.addAttribute("demo", demo);
		model.addAttribute(OPRT, EDIT);
		return "abc/demo/create";
	}
	
	@RequiresPermissions("abc:demo:update")
	@RequestMapping("update.do")
	public String update(@ModelAttribute("bean") Demo demo,String redirect, 
			HttpServletRequest request, RedirectAttributes ra){
		service.update(demo);
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if (Constants.REDIRECT_LIST.equals(redirect)) {
			return "redirect:list.do";
		} else {
			ra.addAttribute("id", demo.getId());
			return "redirect:edit.do";
		}
	}
	
	@ModelAttribute("bean")
	public Demo preloadbean(@RequestParam(required=false) Integer id){
		return id != null ? service.findOne(id):null;
	}
}

