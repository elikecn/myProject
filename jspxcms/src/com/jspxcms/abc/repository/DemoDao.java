package com.jspxcms.abc.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.jspxcms.abc.domain.Demo;
import com.jspxcms.common.orm.Limitable;

public interface DemoDao extends Repository<Demo, Integer>,DemoDaoPlus{
	
	public Page<Demo> findAll(Specification<Demo> spec,Pageable pageable);
	
	public List<Demo> findAll(Specification<Demo> spec,Limitable limitable);
	
	public Demo findOne(Integer id);
	
	public void delete(Demo demo);
	
	public Demo save(Demo demo);
	
	@Modifying
	@Query("delete from Demo bean where bean.site.id in (?1)")
	public int deleteBySiteId(Collection<Integer> siteIds);
}
