package com.ixiaofan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ixiaofan.entity.Audio;


@Service
public interface AudioService {
	
	List<Audio> findAll();
	
	Page<Audio> findAll(Pageable pageable);

	Page<Audio> findAll(Specification<Audio> spec,Pageable pageable);
	
	List<Audio> findAll(Specification<Audio> spec,Sort sort);
	
	Audio findone(Long id);
	
	Audio findByTitle(String title);
	
	void save(Audio audio);
	
	void delete(Audio audio);
}
