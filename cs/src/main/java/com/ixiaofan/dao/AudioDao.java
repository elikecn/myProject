package com.ixiaofan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ixiaofan.entity.Audio;

@Repository
public interface AudioDao extends JpaSpecificationExecutor<Audio>,JpaRepository<Audio, Long>{
	
	public Audio findByTitle(String title);
	
}
