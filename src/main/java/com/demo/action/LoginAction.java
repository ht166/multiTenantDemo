package com.demo.action;

import java.lang.invoke.MethodHandles;

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


@RequestMapping(value = "/login")
@Controller
public class LoginAction {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	/**
	 * セッションスコープの ユーザー情報DTO
	 */
	@Autowired
	private UserInfDto sessionUser; 
	
	/**
	 * ログインサービス
	 */
	@Autowired
	private LoginService loginSerivce;
	
	/*
	 * ログイン画面JSP名
	 */
	String loginJSPName = "login/login";

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		logger.info("ログイン処理画面へ遷移");
		return loginJSPName;
	}

	@PostMapping("/auth")
	public String auth(@ModelAttribute("loginForm") @Valid LoginForm form, BindingResult bindingResult, Model model) {
		
		try {
			logger.info("明示的にデータソースをuserに設定");
			DataSourceContextHolder.setIfChanged("user");

		String userId = form.getUserId();
		String password = form.getPassword();
		logger.info("ログイン認証処理開始 ");
		logger.info("userId={}", userId);
		logger.info("password={}", password);
	
		if (bindingResult.hasErrors()) {
			logger.info("バリデーションエラー");
			return loginJSPName; // エラー時は再度フォーム表示
		}
		
		if (loginSerivce.countByIdAndPassword(userId, password)) {
			logger.info("ログイン成功");
			UserInfDto loginUser = loginSerivce.getUserInf(userId);
			logger.info("ログインユーザーをセッションユーザーとして格納");
			BeanUtils.copyProperties(loginUser, sessionUser);
			
			logger.info("welcomeへ遷移");
			return "redirect:/welcome/index";
		} else {
			model.addAttribute("message", "ユーザーIDまたはパスワードが間違っています");
			logger.info("ログイン失敗　画面を再読み込み");
			return loginJSPName;
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
