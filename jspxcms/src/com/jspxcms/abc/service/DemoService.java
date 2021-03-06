package com.jspxcms.abc.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jspxcms.abc.domain.Demo;
import com.jspxcms.common.orm.Limitable;

public interface DemoService {
	
	public Page<Demo> findAll(Integer siteId, Map<String, String[]> params,
			Pageable pageable);
	
	public Demo findOne(Integer id);
	
	public Demo delete(Integer id);
	
	public Demo[] delete(Integer[] ids);
	
	public Demo save(Demo demo,Integer siteId);
	
	public Demo update(Demo demo);

	public List<Demo> findList(Integer[] integers, Limitable limitable);
}
