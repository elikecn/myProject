package com.jspxcms.plug.web.fore;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.jspxcms.common.upload.Uploader;
import com.jspxcms.core.domain.Site;
import com.jspxcms.core.support.Context;
import com.jspxcms.core.support.ForeContext;
import com.jspxcms.core.support.Response;
import com.jspxcms.core.support.UploadUtils;
import com.jspxcms.plug.domain.Resume;
import com.jspxcms.plug.service.ResumeService;

/**
 * ResumeController
 * 
 * @author liufang
 * 
 */
@Controller
public class ResumeController {
	public static final String TEMPLATE = "plug_resume.html";
	
	@RequestMapping(value = "/resume/avatar.jspx", method = RequestMethod.POST)
	public @ResponseBody String fileUpload(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request, org.springframework.ui.Model modelMap) {
		Site site = Context.getCurrentSite(request);
		Map<String, Object> value = modelMap.asMap();
		String extPath = UploadUtils.getUrl(site.getId(), Uploader.FILE);
		String path = request.getSession().getServletContext().getRealPath("/uploads/"+extPath);
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		try {
			StringBuffer buffer = new StringBuffer("/uploads");
			buffer.append(extPath+"/");
			CommonsMultipartFile f = file;
			// 获取图片的文件名
			String fileName = f.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            String newFileName = String.valueOf(System.currentTimeMillis())+ "." + extensionName;
			File goalFile = new File(dir, newFileName);
			FileOutputStream os = new FileOutputStream(goalFile);
			value.put("filename", buffer.append(goalFile.getName()).toString());
			IOUtils.write(file.getBytes(), os);
			IOUtils.closeQuietly(os);
		} catch (Exception e) {
			e.printStackTrace();
			value.put("error", "fail");
			System.out.println("上传出错");
		}
		return JSON.toJSONString(value);
	}
	@RequestMapping(value = "/resume.jspx")
	public String resumeForm(Integer page, HttpServletRequest request,
			org.springframework.ui.Model modelMap) {
		Site site = Context.getCurrentSite(request);
		Map<String, Object> data = modelMap.asMap();
		ForeContext.setData(data, request);
		ForeContext.setPage(data, page);
		return site.getTemplate(TEMPLATE);
	}

	@RequestMapping(value = "/resume.jspx", method = RequestMethod.POST)
	public String resumeSubmit(@Valid Resume bean, HttpServletRequest request,
			HttpServletResponse response, org.springframework.ui.Model modelMap) {
		Response resp = new Response(request, response, modelMap);
		Site site = Context.getCurrentSite(request);
		service.save(bean, site.getId());
		return resp.post();
	}

	@Autowired
	private ResumeService service;
}
