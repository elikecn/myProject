package com.jspxcms.abc.repository;

import java.util.List;

import com.jspxcms.abc.domain.Demo;
import com.jspxcms.common.orm.Limitable;

public interface DemoDaoPlus {
	
	public List<Demo> getList(Integer[] siteId, Limitable limitable);
}
