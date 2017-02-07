package com.jspxcms.abc.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspxcms.abc.domain.Demo;
import com.jspxcms.abc.repository.DemoDao;
import com.jspxcms.abc.service.DemoService;
import com.jspxcms.common.orm.SearchFilter;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.listener.SiteDeleteListener;
import com.jspxcms.core.service.SiteService;

@Service
@Transactional(readOnly=true)
public class DemoServiceImpl implements DemoService,SiteDeleteListener{
	
	@Autowired
	private SiteService SiteService;
	
	@Autowired
	private DemoDao demoDao;
	
	@Override
	@Transactional
	public void preSiteDelete(Integer[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
			demoDao.deleteBySiteId(Arrays.asList(ids));
		}
	}

	@Override
	public Page<Demo> findAll(Integer siteId, Map<String, String[]> params, Pageable pageable) {
		return demoDao.findAll(spec(siteId, params), pageable);
	}

	private Specification<Demo> spec(final Integer siteId, Map<String, String[]> params) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		final Specification<Demo> sp = SearchFilter.spec(filters, Demo.class);
		Specification<Demo> spe = new Specification<Demo>() {
			public Predicate toPredicate(Root<Demo> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = sp.toPredicate(root, query, cb);
				if(siteId != null){
					pred = cb.and(pred,cb.equal(root.get("site").get("id"), siteId));
				}
				return pred;
			}
		};
		return spe;
	}

	@Override
	public Demo findOne(Integer id) {
		return demoDao.findOne(id);
	}

	@Override
	@Transactional
	public Demo delete(Integer id) {
		Demo demo = demoDao.findOne(id);
		demoDao.delete(demo);
		return demo;
	}

	@Override
	@Transactional
	public Demo[] delete(Integer[] ids) {
		Demo[] demos = new Demo[ids.length];
		for (int i = 0; i < ids.length; i++) {
			demos[i] = delete(ids[i]);
		}
		return demos;
	}

	@Override
	@Transactional
	public Demo save(Demo demo, Integer siteId) {
		Site site = SiteService.get(siteId);
		demo.setCreateDate(new Date());
		demo.setSite(site);
		demo = demoDao.save(demo);
		return demo;
	}

	@Override
	@Transactional
	public Demo update(Demo demo) {
		demo = demoDao.save(demo);
		return demo;
	}

}
