package com.jspxcms.core.repository;

import java.util.List;

import com.jspxcms.core.domain.Attribute;

/**
 * AttributeDaoPlus
 * 
 * @author liufang
 * 
 */
public interface AttributeDaoPlus {
	public List<Attribute> findByNumbers(String[] numbers, Integer[] siteIds);
}
