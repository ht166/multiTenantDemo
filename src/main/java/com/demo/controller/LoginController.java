package com.demo.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.config.datasource.DataSourceContextHolder;
import com.demo.dto.UserInfDto;
import com.demo.form.LoginForm;
import com.demo.service.LoginService;


@RequestMapping(value = "/multiTenantDemo/login")
@Controller
public class LoginController {
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	/*
	 * ログイン画面JSP名
	 */
	private static final String LOGIN_JSP_NAME = "login/login";
	
	/**
	 * ログイン処理の業務ロジックサービス
	 */
	@Autowired
	private final LoginService loginSerivce;
	/**
	 * セッションスコープの ユーザー情報DTO
	 */
	@Autowired
	private UserInfDto sessionUser; 
	

	/**
	 * LoginControllerのコンストラクタ
	 * @param loginSerivce
	 */
	public LoginController(LoginService loginSerivce) {
		this.loginSerivce = loginSerivce;
	}

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		log.info("ログイン処理画面へ遷移");
		return LOGIN_JSP_NAME;
	}

	@PostMapping("/auth")
	public String auth(@ModelAttribute("loginForm") @Valid LoginForm form, BindingResult bindingResult, Model model) {
		
		try {
			log.info("明示的にデータソースをuserに設定");
			DataSourceContextHolder.setIfChanged("user");

		String userId = form.getUserId();
		String password = form.getPassword();
		log.info("ログイン認証処理開始 ");
		log.info("userId={}", userId);
		log.info("password={}", password);
	
		if (bindingResult.hasErrors()) {
			log.info("バリデーションエラー");
			return LOGIN_JSP_NAME; // エラー時は再度フォーム表示
		}
		
		if (loginSerivce.countByIdAndPassword(userId, password)) {
			log.info("ログイン成功");
			UserInfDto loginUser = loginSerivce.getUserInf(userId);
			log.info("ログインユーザーをセッションユーザーとして格納");
			BeanUtils.copyProperties(loginUser, sessionUser);
			
			log.info("welcomeへ遷移");
			return "redirect:/multiTenantDemo/welcome/index";
		} else {
			model.addAttribute("message", "ユーザーIDまたはパスワードが間違っています");
			log.info("ログイン失敗　画面を再読み込み");
			return LOGIN_JSP_NAME;
		}
		
		}finally {
			DataSourceContextHolder.clear();
		}
	}
	
    @GetMapping("/logout")
    public String logout() {
        // セッションスコープの DTO をリセット
        sessionUser.setId(null);
        sessionUser.setUserName(null);
        return "redirect:/login/index";
    }
    
}
