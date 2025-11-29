package com.demo.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.UserInfDto;
/**
 * welcome画面のコントローラー
 * @author ht166
 *
 */
@Controller
@RequestMapping(value="/multiTenantDemo/welcome")
public class WelcomeController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	/**
	 * セッションスコープの ユーザー情報DTO
	 */
	@Autowired
	private UserInfDto sessionUser; 
	
	@GetMapping("/index")
	public String index(Model model){
		
//		model.addAttribute("sessionUser", sessionUser);
		
	    // menuLevel に応じてビューを切り替え
	    if(sessionUser != null && sessionUser.getLevel() == 0){
			logger.info("管理者アカウントのwelcome画面へ遷移");
	        return "welcome/admin";   // 管理者用 JSP
	    } else {
	    	logger.info("一般ユーザー用のwelcome画面へ遷移");
	        return "welcome/user";    // 一般ユーザー用 JSP
	    }
//		return "welcome/welcome";
	}
	

}
