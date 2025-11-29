package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.UserInfDto;
import com.demo.form.AdminForm;
import com.demo.service.AdminService;

/**
 * 管理者用のユーザー管理画面のコントローラー
 * @author ht166
 *
 */
@Controller
@RequestMapping(value ="/multiTenantDemo/admin")
public class AdminController {
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	/** 管理者権限で */
	private final AdminService adminService;
	
	/**
	 * AdminControllerのコンストラクタ
	 * @param adminService
	 */
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/users")
	public String users(Model model) {
		log.info("ユーザー一覧画面へ遷移 開始");
		
	    // ユーザー一覧などを取得して model にセット
		List<UserInfDto> users = adminService.getAllUsers();
	    model.addAttribute("users", users);
	    
	    return "admin/users"; // admin/users.jsp を表示
	}
	
	@PostMapping("/editUser")
	public String editUser(@ModelAttribute("adminForm")AdminForm adminForm,Model model) {
		
		int result = adminService.updateUserInfo(adminForm);
		
		model.addAttribute("msg", result+"件のユーザー情報を更新しました");
		
		return "admin/editUser";
	}
	
//	TODO 未完成
	@PostMapping("/createUser")
	public String createUser(Model model) {
		return "admin/user";
	}
	
//	TODO 未完成
	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") String targetId,Model model) {
		
		adminService.deleteUser(targetId);
		model.addAttribute("msg", "id:"+targetId+"のユーザー情報を削除しました");

		// 更新後のユーザー一覧などを取得して model にセット
		List<UserInfDto> users = adminService.getAllUsers();
		model.addAttribute("users", users);
		
		return "admin/users";
	}
	
	/**
	 * 管理者用のユーザー管理画面フォーム初期化メソッド
	 * @return 初期化されたユーザー管理画面フォーム
	 */
	@ModelAttribute("adminForm")
	protected AdminForm setAdminForm() {
		return new AdminForm();
	}

}
