package com.springmvc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.entity.SenMach;
import com.springmvc.service.SenMachService;

@Controller
@RequestMapping("/user")
public class UserServiceController {
//    @Resource
//    private UserService userService;

	@GetMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
//        int userId = Integer.parseInt(request.getParameter("id"));
//        User user = userService.selectById((long) userId);
//        model.addAttribute("user", user);
		return "showUser";
	}
	
    @Autowired
    private SenMachService senMachService;
	
	public String getAllEmployeesJSON(Model model) 
	{
		SenMach entity = new SenMach();
		entity.setIp("12421");
		entity.setMachName("test");
		senMachService.create(entity);
		
	    return "jsonTemplate";
	}
}
