package com.ixiaofan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ixiaofan.dao.AudioDao;
import com.ixiaofan.entity.Audio;

@Service
public class AudioServiceImpl implements AudioService{
	
	@Autowired
	private AudioDao audioDao;
	

	@Override
	public Audio findone(Long id) {
		return audioDao.findOne(id);
	}

	@Override
	public Audio findByTitle(String title) {
		return audioDao.findByTitle(title);
	}

	@Override
	public void save(Audio audio) {
		audioDao.save(audio);
	}

	@Override
	public void delete(Audio audio) {
		audioDao.delete(audio);
	}

	@Override
	public Page<Audio> findAll(Specification<Audio> spec, Pageable pageable) {
		return audioDao.findAll(spec, pageable);
	}

	@Override
	public List<Audio> findAll(Specification<Audio> spec, Sort sort) {
		return audioDao.findAll(spec, sort);
	}

	@Override
	public Page<Audio> findAll(Pageable pageable) {
		return audioDao.findAll(pageable);
	}

	@Override
	public List<Audio> findAll() {
		return audioDao.findAll();
	}


}
