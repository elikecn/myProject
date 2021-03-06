package com.jspxcms.core.support;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UrlPathHelper;

import com.jspxcms.common.freemarker.Freemarkers;
import com.jspxcms.common.web.PageUrlResolver;
import com.jspxcms.core.domain.MemberGroup;
import com.jspxcms.core.domain.Org;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.domain.User;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * ForeContext
 * 
 * @author liufang
 * 
 */
public abstract class ForeContext {
	/**
	 * 用户资源路径名
	 */
	public static final String FILES = "_files";
	/**
	 * 前台资源路径名
	 */
	public static final String FORE = "fore";
	/**
	 * 前台资源路径
	 */
	public static final String FORE_PATH = "/static/fore";
	/**
	 * 动态地址前缀
	 */
	public static final String DY = "dy";
	/**
	 * 站点
	 */
	public static final String SITE = "site";
	/**
	 * 全局
	 */
	public static final String GLOBAL = "global";
	/**
	 * 用户
	 */
	public static final String USER = "user";
	/**
	 * 主会员组
	 */
	public static final String GROUP = "group";
	/**
	 * 扩展会员组
	 */
	public static final String GROUPS = "groups";
	/**
	 * 主组织
	 */
	public static final String ORG = "org";
	/**
	 * 扩展组织
	 */
	public static final String ORGS = "orgs";
	/**
	 * 分页对象
	 */
	public static final String PAGED_LIST = "pagedList";

	public static void setData(Map<String, Object> data, Site site, User user,
			MemberGroup group, Collection<MemberGroup> groups, Org org,
			Collection<Org> orgs, String url) {
		String ctx = site.getContextPath();
		String dy = site.getDy();
		String files = site.getFilesUrl();
		String fore = ctx != null ? ctx + FORE_PATH : FORE_PATH;
		data.put(Constants.CTX, ctx != null ? ctx : "");
		data.put(DY, dy);
		data.put(FILES, files);
		data.put(FORE, fore);
		data.put(USER, user);
		data.put(GROUP, group);
		data.put(GROUPS, groups);
		data.put(ORG, org);
		data.put(ORGS, orgs);
		data.put(SITE, site);
		data.put(GLOBAL, site.getGlobal());
		data.put(Freemarkers.URL, url);
	}

	public static void setData(Map<String, Object> data,
			HttpServletRequest request) {
		String url = getCurrentUrl(request);
		User user = Context.getCurrentUser(request);
		Site site = Context.getCurrentSite(request);
		MemberGroup group = Context.getCurrentGroup(request);
		Collection<MemberGroup> groups = Context.getCurrentGroups(request);
		Org org = Context.getCurrentOrg(request);
		Collection<Org> orgs = Context.getCurrentOrgs(request);
		setData(data, site, user, group, groups, org, orgs, url);
	}

	public static void setPage(Map<String, Object> data, Integer page,
			PageUrlResolver pageUrlResolver, Page<?> pagedList) {
		if (page == null || page < 1) {
			page = 1;
		}
		data.put(Freemarkers.PAGE, page);
		if (pageUrlResolver != null) {
			data.put(Freemarkers.PAGE_URL_RESOLVER, pageUrlResolver);
		}
		if (pagedList != null) {
			data.put(PAGED_LIST, pagedList);
		}
	}

	public static void setPage(Map<String, Object> data, Integer page,
			PageUrlResolver pageUrlResolver) {
		setPage(data, page, pageUrlResolver, null);
	}

	public static void setPage(Map<String, Object> data, Integer page) {
		setPage(data, page, null, null);
	}

	public static String getCurrentUrl(HttpServletRequest request) {
		UrlPathHelper urlPath = new UrlPathHelper();
		String uri = urlPath.getOriginatingRequestUri(request);
		String queryString = urlPath.getOriginatingQueryString(request);
		if (StringUtils.isNotBlank(queryString)) {
			uri += "?" + queryString;
		}
		return uri;
	}

	public static Site getSite(Environment env) throws TemplateModelException {
		TemplateModel model = env.getDataModel().get(SITE);
		if (model instanceof AdapterTemplateModel) {
			return (Site) ((AdapterTemplateModel) model)
					.getAdaptedObject(Site.class);
		} else {
			throw new TemplateModelException("\"" + SITE
					+ "\" not found in DataModel");
		}
	}

	public static Integer getSiteId(Environment env)
			throws TemplateModelException {
		return getSite(env).getId();
	}

	public static User getUser(Environment env) throws TemplateModelException {
		TemplateModel model = env.getDataModel().get(USER);
		if (model instanceof AdapterTemplateModel) {
			return (User) ((AdapterTemplateModel) model)
					.getAdaptedObject(User.class);
		} else {
			return null;
		}
	}

	public static Integer getUserId(Environment env)
			throws TemplateModelException {
		User user = getUser(env);
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	public static MemberGroup getGroup(Environment env)
			throws TemplateModelException {
		TemplateModel model = env.getDataModel().get(USER);
		if (model instanceof AdapterTemplateModel) {
			return (MemberGroup) ((AdapterTemplateModel) model)
					.getAdaptedObject(User.class);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static Collection<MemberGroup> getGroups(Environment env)
			throws TemplateModelException {
		TemplateModel model = env.getDataModel().get(USER);
		if (model instanceof AdapterTemplateModel) {
			return (Collection<MemberGroup>) ((AdapterTemplateModel) model)
					.getAdaptedObject(Collection.class);
		} else {
			return null;
		}
	}

	public static Org getOrg(Environment env) throws TemplateModelException {
		TemplateModel model = env.getDataModel().get(ORG);
		if (model instanceof AdapterTemplateModel) {
			return (Org) ((AdapterTemplateModel) model)
					.getAdaptedObject(Org.class);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static Collection<Org> getOrgs(Environment env)
			throws TemplateModelException {
		TemplateModel model = env.getDataModel().get(ORGS);
		if (model instanceof AdapterTemplateModel) {
			return (Collection<Org>) ((AdapterTemplateModel) model)
					.getAdaptedObject(Collection.class);
		} else {
			return null;
		}
	}

	/**
	 * 页数线程变量
	 */
	private static ThreadLocal<Integer> totalPagesHolder = new ThreadLocal<Integer>();

	public static void setTotalPages(Integer totalPages) {
		totalPagesHolder.set(totalPages);
	}

	public static Integer getTotalPages() {
		return totalPagesHolder.get();
	}

	public static void resetTotalPages() {
		totalPagesHolder.remove();
	}

}
