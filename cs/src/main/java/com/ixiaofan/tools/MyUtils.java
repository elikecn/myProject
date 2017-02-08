package com.ixiaofan.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * 自己手写的工具类
 * @author ixiaofan
 * @version 创建时间：2016年10月10日 上午10:11:27
 */
public class MyUtils {
	
	/**
	 * 获取文件的相对路径
	 * @param request 请求域
	 * @param path 文件的路径，一般为"/"或者"WEB-INF/styles/upload"之类
	 * @return
	 */
	public static String testGetRealPath(HttpServletRequest request,String path){
		String realpath = request.getSession().getServletContext().getRealPath(path);
		return realpath;
	}
	
	/**
	 * 文件下载
	 * @param request 请求域，httpServletRequest，用来获取相对路径
	 * @param fileName 文件名，是数据库保存对应在upload文件下名称
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> testFileDownLoad(HttpServletRequest request,String fileName) throws IOException{
		byte[] body = null; 
		String postfix = fileName.substring(fileName.lastIndexOf(".")+1);
		String path = testGetRealPath(request, "WEB-INF/styles/upload/"+postfix+"/"+fileName);
		File file = new File(path);
		body = FileUtils.readFileToByteArray(file);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename="+fileName);
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	/**
	 * 文件上传
	 * @param file 上传的文件
	 * @param postfix 文件的后缀名
	 * @param rebuildFileName 文件重构后的名称
	 * @param request
	 */
	public static void testFileUplode(MultipartFile file,String postfix,
			String rebuildFileName,HttpServletRequest request){
		try {
			InputStream is = file.getInputStream();
			String path = testGetRealPath(request, "WEB-INF/styles/upload");
			File file2 = new File(path+"/"+postfix);
			if(!file2.isDirectory()){
				file2.mkdirs();
			}
			File f = new File(file2,rebuildFileName);
			byte[] bs = new byte[is.available()];
			FileOutputStream fos = new FileOutputStream(f);
			while(is.read(bs)!=-1){
				fos.write(bs);
			}
			is.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 将MultipartFile转成file
	 * @param file 上传的文件
	 * @return
	 */
	public static File testTransFile(MultipartFile file){
        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
        return f;
	}
	
	/**
	 * 得到文件的后缀名
	 * @param file 上传的文件
	 * @return
	 */
	public static String testGetPostfix(MultipartFile file){
		String OriginalFilename = file.getOriginalFilename();
		String postfix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
		return postfix;
	}
	
	/**
	 * 重新生成文件名
	 * @param postfix 文件的后缀名
	 * @return
	 */
	public static String testRebuildFileName(String postfix){
		Date date = new Date();
		Long long1 = date.getTime();
		String rebuildFileName = long1+"."+postfix;
		return rebuildFileName;
	}
}
