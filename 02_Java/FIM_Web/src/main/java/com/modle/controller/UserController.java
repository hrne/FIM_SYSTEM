package com.modle.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.modle.entity.User;
import com.modle.service.UserService;
import com.modle.validator.UserFormValidator;

@Controller
public class UserController {

	@Autowired
	UserFormValidator userFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@Autowired
	private UserService userService;
	
	// list users
	//@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "users/list";
	}
	
	// show user
	//@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found!");
		}
		return "users/show";
	}
	
	// show add user form
	//@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		User user = createModelDefaultValues();
	//	User user = new User();
		model.addAttribute("userForm", user);
		
		createFormOptions(model);
		return "users/userform";
	}

	// show update user form
	//@RequestMapping(value = "/user/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);

		createFormOptions(model);
		return "users/userform";
	}

	// save(insert) or update user
	//@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			createFormOptions(model);
			return "users/userform";
		} else {
			userService.saveOrUpdate(user);

			redirectAttributes.addFlashAttribute("css", "success");
			if (user.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
			}
			return "redirect:/user/" + user.getId();
		}
	}

	// delete user
	//@RequestMapping(value = "/user/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		userService.delete(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");
		return "redirect:/users";
	}

	private void createFormOptions(Model model) {
		List<String> javaMvcList = new ArrayList<String>();
		javaMvcList.add("Spring MVC");
		javaMvcList.add("JSF");
		javaMvcList.add("Google Web Toolkit");
		javaMvcList.add("Vert");
		javaMvcList.add("Dropwizard");
		javaMvcList.add("Struts");
		model.addAttribute("javaMvcList", javaMvcList);

		Map<String, String> skill = new LinkedHashMap<String, String>();
		skill.put("HTML", "HTML");
		skill.put("CSS", "CSS");
		skill.put("JavaScript", "JavaScript");
		skill.put("JQuery", "JQuery");
		skill.put("Bootstrap", "Bootstrap");
		model.addAttribute("webSkillList", skill);

		List<Integer> heightList = new ArrayList<Integer>();
		heightList.add(150);
		heightList.add(160);
		heightList.add(170);
		heightList.add(180);
		heightList.add(190);
		model.addAttribute("heightList", heightList);

		Map<String, String> country = new LinkedHashMap<String, String>();
		country.put("TW", "Taiwan");
		country.put("CN", "China");
		country.put("US", "United Stated");		
		model.addAttribute("countryList", country);
	}
	
	private User createModelDefaultValues() {
		User user = new User();		
		// set default value
		user.setName("Jim");
		user.setEmail("test@gmail.com");
		user.setAddress("台灣");
		// user.setPassword("test@1111");
		// user.setConfirmPassword("test@1111");
		user.setNewsletter(true);
		user.setSex("M");
		user.setFramework(new ArrayList<String>(Arrays.asList("Spring MVC", "Struts")));
		user.setSkill(new ArrayList<String>(Arrays.asList("HTML", "CSS", "JavaScript")));
		user.setCountry("TW");
		user.setHeight(170);
		return user;
	}
}