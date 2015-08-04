package com.newlecture.web.controller.joinus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/joinus/*")
public class JoinusController {
	
	@RequestMapping("login")
	public String login(){
		
		return "joinus.login";
	}

}
