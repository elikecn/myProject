package com.ixiaofan.tools;

import java.util.Random;
/**
 * 随机生成6位数字验证码
 * @author ixiaofan
 * @version 创建时间：2016年9月12日 下午5:59:51
 */
public class ValidateCode {
	
	public static String getCode() {
		String codeSource = "0123456789";
		StringBuffer validateCode = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			validateCode.append(codeSource.charAt(r.nextInt(codeSource.length()-1)));
		}
		String result = validateCode.toString();
		return result;
	}
}
