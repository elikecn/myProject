package com.ixiaofan.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ixiaofan.entity.Audio;
import com.ixiaofan.entity.FileMD5;
import com.ixiaofan.service.AudioService;
import com.ixiaofan.service.FileMD5Service;
import com.ixiaofan.tools.DynamicSpecification;
import com.ixiaofan.tools.Md5;
import com.ixiaofan.tools.MyUtils;

@Controller
@RequestMapping(value="/audio")
public class AudioController {
	
	@Autowired
	private AudioService audioService;
	
	@Autowired
	private FileMD5Service fileMD5Service;
	
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public String list(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			@RequestParam(value="pageSize",defaultValue="5")Integer pageSize,
			HttpServletRequest request,Map<String, Object> map){
		Specification<Audio>  spec = DynamicSpecification.bySearchFilter(Audio.class,request);
		Pageable pageable = new PageRequest(pageNum-1, pageSize, new Sort(Direction.DESC,"id"));
		Page<Audio> page = audioService.findAll(spec, pageable);
		map.put("page", page);
		return "audio/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryList",method={RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> queryList(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize,
			HttpServletRequest request,Map<String, Object> map){
		Specification<Audio>  spec = DynamicSpecification.bySearchFilter(Audio.class,request);
		Pageable pageable = new PageRequest(pageNum-1, pageSize, new Sort(Direction.DESC,"id"));
		Page<Audio> page = audioService.findAll(spec, pageable);
		map.put("page", page);
		return map;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String add(Map<String, Object> map){
		Audio audio = new Audio();
		map.put("audio", audio);
		return "audio/create";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(@Validated Audio audio,
			@RequestParam(value="file",required = false) MultipartFile file,
			HttpServletRequest request){
		if(StringUtils.isNotBlank(file.getOriginalFilename())){
			String stringmd5 = Md5.filesMD5(MyUtils.testTransFile(file));
			String postfix = MyUtils.testGetPostfix(file);
			FileMD5 fileMD5 = fileMD5Service.findOne(postfix, stringmd5);
			if(fileMD5 == null){
				String name = MyUtils.testRebuildFileName(postfix);
				FileMD5 fm = new FileMD5();
				fm.setPostfix(postfix);
				fm.setStringmd5(stringmd5);
				fm.setName(name);
				fileMD5Service.save(fm);
				MyUtils.testFileUplode(file, postfix, name, request);
				audio.setMd5(fm);
			}else{
				audio.setMd5(fileMD5);
			}
		}
		audioService.save(audio);
		return "redirect:/audio/list";
	}
}
