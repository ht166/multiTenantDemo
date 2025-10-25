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

import com.demo.dto.UserInfDto;
import com.demo.form.LoginForm;
import com.demo.service.LoginService;


@RequestMapping(value = "/login")
@Controller
public class LoginAction {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	/**
	 * �Z�b�V�����X�R�[�v�� ���[�U�[���DTO
	 */
	@Autowired
	private UserInfDto sessionUser; 
	
	/**
	 * ���O�C���T�[�r�X
	 */
	@Autowired
	private LoginService loginSerivce;
	
	/*
	 * ���O�C�����JSP��
	 */
	String loginJSPName = "login/login";

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		logger.info("���O�C��������ʂ֑J��");
		return loginJSPName;
	}

	@PostMapping("/auth")
	public String auth(@ModelAttribute("loginForm") @Valid LoginForm form, BindingResult bindingResult, Model model) {
		String userId = form.getUserId();
		String password = form.getPassword();
		logger.info("���O�C���F�؏����J�n ");
		logger.info("userId={}", userId);
		logger.info("password={}", password);
	
		if (bindingResult.hasErrors()) {
			logger.info("�o���f�[�V�����G���[");
			return loginJSPName; // �G���[���͍ēx�t�H�[���\��
		}
		
		if (loginSerivce.countByIdAndPassword(userId, password)) {
			logger.info("���O�C������");
			UserInfDto loginUser = loginSerivce.getUserInf(userId);
			logger.info("���O�C�����[�U�[���Z�b�V�������[�U�[�Ƃ��Ċi�[");
			BeanUtils.copyProperties(loginUser, sessionUser);
			
			logger.info("welcome�֑J��");
			return "redirect:/welcome/index";
		} else {
			model.addAttribute("message", "���[�U�[ID�܂��̓p�X���[�h���Ԉ���Ă��܂�");
			logger.info("���O�C�����s�@��ʂ��ēǂݍ���");
			return loginJSPName;
		}
	}
	
    @GetMapping("/logout")
    public String logout() {
        // �Z�b�V�����X�R�[�v�� DTO �����Z�b�g
        sessionUser.setId(null);
        sessionUser.setUserName(null);
        return "redirect:/login/index";
    }
    
}
