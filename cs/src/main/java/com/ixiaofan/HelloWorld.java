package com.ixiaofan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	
	@Autowired
	private UserService userService;
	
	/*public HelloWorld(){
		System.out.println("hello world constructor..");
	}*/
	
	@RequestMapping("helloworld")
	public String Hello(){
/*		System.out.println("..................");
		System.out.println(userService);
*/		return "login";
	}
}
