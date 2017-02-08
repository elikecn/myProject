package com.ixiaofan.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ixiaofan.entity.Account;
import com.ixiaofan.service.AccountService;
import com.ixiaofan.tools.Md5;
import com.ixiaofan.tools.SendEmail;
import com.ixiaofan.tools.ValidateCode;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 注册新增页面跳转
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Map<String, Object> map){
		map.put("account", new Account());
		return "account/create";
	}
	
	/**
	 * 注册信息提交
	 * @param account
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody String save(@Valid Account account){
		Account account2 = accountService.findOneByUsername(account.getUsername());
		if(account2 == null){
			String validatecode = ValidateCode.getCode();
			account.setValidatecode(validatecode);
			accountService.save(account);
			SendEmail.MailValidate(account);
			return "success";
		}else {
			return "error";
		}
		
	}
	
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public @ResponseBody String register(@RequestParam("username") String username,
			@RequestParam("password") String password){
		String result = "error";
		Account account = accountService.findOneByUsername(username);
		if(account != null){
			String p = account.getPassword();
			if(account.getPassword().equals(Md5.convertMD5(Md5.convertMD5(p)))){
				if(account.getStatus() == 1){
					result = "success";
				}else{
					Date currentTime = new Date();
					Date lastRegisterTime = DateUtils.addDays(account.getCreattime(), 1);
	                if(currentTime.before(lastRegisterTime)){
	                	result = "unactivation";
					}else{
						accountService.delete(account);
					}
				}
				
			}
		}
		return result;
	}
	
	/*@RequestMapping(value="activation",method=RequestMethod.POST)
	public @ResponseBody String ActivationPost(@RequestParam("username") String username,
			@RequestParam("activation") String activation){
		String result = "";
		Account account = accountService.findOneByUsername(username);
		if(account.getStatus() == 0){
			if(activation.equals(account.getValidatecode())){
				account.setStatus(1);
				accountService.save(account);
				result = "success";
			}else {
				result = "error";
			}
		}else{
			result = "activationable";
		}
		return result;
	}*/
	
	/**
	 * 邮箱验证
	 * @param username
	 * @param validateCode
	 * @return
	 */
	@RequestMapping(value="activation",method=RequestMethod.GET)
	public String ActivationGet(@RequestParam("username") String username,
			@RequestParam("validateCode") String validateCode){
		String result = "audio/list";
		Account account = accountService.findOneByUsername(username);
		Date currentTime = new Date();
		Date lastRegisterTime = DateUtils.addDays(account.getCreattime(), 1);
        if(currentTime.before(lastRegisterTime)){
			if(account.getValidatecode().equals(validateCode)){
				account.setStatus(1);
				accountService.save(account);
			}else {
				result = "error";
			}
		}else{
			accountService.delete(account);
			result = "error";
		}
		return result;
	}
	
	/**
	 * 用户名重复性校验
	 * @param username
	 * @param card
	 * @param email
	 * @return
	 */
	@RequestMapping(value="existValidate",method=RequestMethod.POST)
	public @ResponseBody String existValidate(
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="email",required=false) String email,
			@RequestParam(value="card",required=false) String card){
		Account account = null;
		if(username != null){
			account = accountService.findOneByUsername(username);
		}
		if(card != null){
			account = accountService.findOneByCard(card);
		}
		if(email != null){
			account = accountService.findOneByEmail(email);
		}
		if(account == null){
			return "success";
		}else {
			return "error";
		}
	}
	
	@RequestMapping(value="success")
	public String success(){
		return "redirect:/audio/list";
	}
}
