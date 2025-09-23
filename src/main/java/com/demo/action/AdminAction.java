package com.demo.action;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.UserInfDto;
import com.demo.service.AdminService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping(value ="/admin")
public class AdminAction {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Resource
	private AdminService adminService;
	
	private List<UserInfDto> users;
	
	@GetMapping("/users")
	public String users(Model model) {
		logger.info("ユーザー一覧画面へ遷移");
		
	    // ユーザー一覧などを取得して model にセット
	    users = adminService.getAllUsers();
	    model.addAttribute("users", users);
	    
	    return "admin/users"; // admin/users.jsp を表示
	}
	
//	TODO 未完成
	@GetMapping("/editUser")
	public String editUser(Model model) {
		return "admin/editUser";
	}
	
//	TODO 未完成
	@GetMapping("/createUser")
	public String createUser(Model model) {
		return "admin/user";
	}
	
//	TODO 未完成
	@PostMapping("/delete")
	public String deleteUser(Model model) {
		return "forward:/admin/users";
	}

}
