package com.jspxcms.core.web.back;

import static com.jspxcms.core.support.Constants.CREATE;
import static com.jspxcms.core.support.Constants.DELETE_SUCCESS;
import static com.jspxcms.core.support.Constants.EDIT;
import static com.jspxcms.core.support.Constants.MESSAGE;
import static com.jspxcms.core.support.Constants.OPERATION_SUCCESS;
import static com.jspxcms.core.support.Constants.OPRT;
import static com.jspxcms.core.support.Constants.SAVE_SUCCESS;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jspxcms.common.orm.RowSide;
import com.jspxcms.common.web.Servlets;
import com.jspxcms.core.domain.MemberGroup;
import com.jspxcms.core.domain.Org;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.domain.User;
import com.jspxcms.core.domain.UserDetail;
import com.jspxcms.core.service.MemberGroupService;
import com.jspxcms.core.service.OperationLogService;
import com.jspxcms.core.service.OrgService;
import com.jspxcms.core.service.SiteService;
import com.jspxcms.core.service.UserService;
import com.jspxcms.core.support.Constants;
import com.jspxcms.core.support.Context;

/**
 * UserController
 * 
 * @author liufang
 * 
 */
@Controller
@RequestMapping("/core/user_global")
public class UserGlobalController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserGlobalController.class);

	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:list")
	@RequestMapping("list.do")
	public String list(
			@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap) {
		User currUser = Context.getCurrentUser(request);
		Map<String, String[]> params = Servlets.getParameterValuesMap(request,
				Constants.SEARCH_PREFIX);
		Page<User> pagedList = service.findPage(currUser.getRank(), null, null,
				params, pageable);
		List<MemberGroup> groupList = groupService.findRealGroups();
		List<Org> orgList = orgService.findList();
		List<Site> siteList = siteService.findList();

		modelMap.addAttribute("pagedList", pagedList);
		modelMap.addAttribute("siteList", siteList);
		modelMap.addAttribute("orgList", orgList);
		modelMap.addAttribute("groupList", groupList);
		return "core/user_global/user_global_list";
	}

	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:create")
	@RequestMapping("create.do")
	public String create(Integer id, Integer orgId, HttpServletRequest request,
			org.springframework.ui.Model modelMap) {
		Site site = Context.getCurrentSite(request);
		User currUser = Context.getCurrentUser(request);
		if (id != null) {
			User bean = service.get(id);
			modelMap.addAttribute("bean", bean);
		}
		Org org = null;
		if (orgId != null) {
			org = orgService.get(orgId);
		} else {
			org = site.getOrg();
		}
		modelMap.addAttribute("org", org);
		List<Site> siteList = siteService.findList();
		List<MemberGroup> groupList = groupService.findRealGroups();
		modelMap.addAttribute("siteList", siteList);
		modelMap.addAttribute("groupList", groupList);
		modelMap.addAttribute("currRank", currUser.getRank());
		modelMap.addAttribute(OPRT, CREATE);
		return "core/user_global/user_global_form";
	}

	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:edit")
	@RequestMapping("edit.do")
	public String edit(
			Integer id,
			Integer position,
			@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap) {
		User currUser = Context.getCurrentUser(request);
		User bean = service.get(id);
		Map<String, String[]> params = Servlets.getParameterValuesMap(request,
				Constants.SEARCH_PREFIX);
		RowSide<User> side = service.findSide(currUser.getRank(), null, null,
				params, bean, position, pageable.getSort());
		List<Site> siteList = siteService.findList();
		List<MemberGroup> groupList = groupService.findRealGroups();

		modelMap.addAttribute("siteList", siteList);
		modelMap.addAttribute("groupList", groupList);
		modelMap.addAttribute("bean", bean);
		modelMap.addAttribute("org", bean.getOrg());
		modelMap.addAttribute("currRank", currUser.getRank());
		modelMap.addAttribute("side", side);
		modelMap.addAttribute("position", position);
		modelMap.addAttribute(OPRT, EDIT);
		return "core/user_global/user_global_form";
	}

	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:save")
	@RequestMapping("save.do")
	public String save(User bean, UserDetail detail, Integer[] roleIds,
			Integer[] orgIds, Integer[] groupIds, Integer orgId,
			Integer groupId, String redirect, HttpServletRequest request,
			RedirectAttributes ra) {
		Integer siteId = Context.getCurrentSiteId(request);
		User currUser = Context.getCurrentUser(request);
		Integer currRank = currUser.getRank();
		if (!bean.isAdmin()) {
			bean.setRank(999);
		}
		if (bean.getRank() < currRank) {
			bean.setRank(currRank);
		}
		String ip = Servlets.getRemoteAddr(request);
		service.save(bean, detail, roleIds, orgIds, groupIds, orgId, groupId,
				ip);
		logService.operation("opr.user.add", bean.getUsername(), null,
				bean.getId(), ip, currUser.getId(), siteId);
		logger.info("save User, username={}.", bean.getUsername());

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

	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:update")
	@RequestMapping("update.do")
	public String update(@ModelAttribute("bean") User bean,
			@ModelAttribute("detail") UserDetail detail, Integer[] roleIds,
			Integer[] orgIds, Integer[] groupIds, Integer orgId,
			Integer groupId, Integer position, String redirect,
			HttpServletRequest request, RedirectAttributes ra) {
		Integer siteId = Context.getCurrentSiteId(request);
		User currUser = Context.getCurrentUser(request);
		Integer currRank = currUser.getRank();
		if (!bean.isAdmin()) {
			bean.setRank(999);
		}
		if (bean.getRank() < currRank) {
			bean.setRank(currRank);
		}
		service.update(bean, detail, roleIds, orgIds, groupIds, orgId, groupId,
				null, null);
		String ip = Servlets.getRemoteAddr(request);
		logService.operation("opr.user.edit", bean.getUsername(), null,
				bean.getId(), ip, currUser.getId(), siteId);
		logger.info("update User, username={}.", bean.getUsername());
		ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
		if (Constants.REDIRECT_LIST.equals(redirect)) {
			return "redirect:list.do";
		} else {
			ra.addAttribute("id", bean.getId());
			ra.addAttribute("position", position);
			return "redirect:edit.do";
		}
	}

	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:delete")
	@RequestMapping("delete.do")
	public String delete(Integer[] ids, HttpServletRequest request,
			RedirectAttributes ra) {
		Integer siteId = Context.getCurrentSiteId(request);
		Integer userId = Context.getCurrentUserId(request);
		User[] beans = service.delete(ids);
		String ip = Servlets.getRemoteAddr(request);
		for (User bean : beans) {
			logService.operation("opr.user.delete", bean.getUsername(), null,
					bean.getId(), ip, userId, siteId);
			logger.info("delete User, username={}.", bean.getUsername());
		}
		ra.addFlashAttribute(MESSAGE, DELETE_SUCCESS);
		return "redirect:list.do";
	}

	// 删除密码
	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:delete_password")
	@RequestMapping("delete_password.do")
	public String deletePassword(Integer[] ids, HttpServletRequest request,
			RedirectAttributes ra) {
		Integer siteId = Context.getCurrentSiteId(request);
		Integer userId = Context.getCurrentUserId(request);
		User[] beans = service.deletePassword(ids);
		String ip = Servlets.getRemoteAddr(request);
		for (User bean : beans) {
			logService.operation("opr.user.deletePassword", bean.getUsername(),
					null, bean.getId(), ip, userId, siteId);
			logger.info("delete User password, username={}..",
					bean.getUsername());
		}
		ra.addFlashAttribute(MESSAGE, OPERATION_SUCCESS);
		return "redirect:list.do";
	}

	// 审核账户
	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:check")
	@RequestMapping("check.do")
	public String check(Integer[] ids, HttpServletRequest request,
			RedirectAttributes ra) {
		User[] beans = service.check(ids);
		for (User bean : beans) {
			logService.operation("opr.user.check", bean.getUsername(), null,
					bean.getId(), request);
			logger.info("check Member, username={}.", bean.getUsername());
		}
		ra.addFlashAttribute(MESSAGE, OPERATION_SUCCESS);
		return "redirect:list.do";
	}

	// 锁定账户
	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:lock")
	@RequestMapping("lock.do")
	public String lock(Integer[] ids, HttpServletRequest request,
			RedirectAttributes ra) {
		User[] beans = service.lock(ids);
		for (User bean : beans) {
			logService.operation("opr.user.lock", bean.getUsername(), null,
					bean.getId(), request);
			logger.info("disable User, username={}..", bean.getUsername());
		}
		ra.addFlashAttribute(MESSAGE, OPERATION_SUCCESS);
		return "redirect:list.do";
	}

	// 解锁账户
	@RequiresRoles("super")
	@RequiresPermissions("core:user_global:unlock")
	@RequestMapping("unlock.do")
	public String unlock(Integer[] ids, HttpServletRequest request,
			RedirectAttributes ra) {
		User[] beans = service.unlock(ids);
		for (User bean : beans) {
			logService.operation("opr.user.unlock", bean.getUsername(), null,
					bean.getId(), request);
			logger.info("undisable User, username={}..", bean.getUsername());
		}
		ra.addFlashAttribute(MESSAGE, OPERATION_SUCCESS);
		return "redirect:list.do";
	}

	@ModelAttribute
	public void preloadBean(@RequestParam(required = false) Integer oid,
			org.springframework.ui.Model modelMap) {
		if (oid != null) {
			User bean = service.get(oid);
			if (bean != null) {
				modelMap.addAttribute("bean", bean);
				modelMap.addAttribute("detail", bean.getDetail());
			}
		}
	}

	@Autowired
	private OrgService orgService;
	@Autowired
	private MemberGroupService groupService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private OperationLogService logService;
	@Autowired
	private UserService service;
}
