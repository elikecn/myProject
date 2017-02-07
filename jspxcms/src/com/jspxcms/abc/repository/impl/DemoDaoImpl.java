package com.jspxcms.abc.repository.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;

import com.jspxcms.abc.domain.Demo;
import com.jspxcms.abc.repository.DemoDaoPlus;
import com.jspxcms.common.orm.JpqlBuilder;
import com.jspxcms.common.orm.Limitable;

public class DemoDaoImpl implements DemoDaoPlus{

	@SuppressWarnings("unchecked")
	@Override
	public List<Demo> getList(Integer[] siteId, Limitable limitable) {
		JpqlBuilder jb = new JpqlBuilder();
		jb.append("from Demo bean where 1=1");
		if (ArrayUtils.isNotEmpty(siteId)) {
			jb.append(" and bean.site.id in (:siteId)");
			jb.setParameter("siteId", Arrays.asList(siteId));
		}
		return jb.list(em, limitable);
	}
	
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
