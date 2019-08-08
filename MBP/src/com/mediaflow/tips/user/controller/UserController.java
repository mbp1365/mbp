package com.mediaflow.tips.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mediaflow.tips.common.Constants;
import com.mediaflow.tips.common.ReturnCode;
import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.common.service.CodeService;
import com.mediaflow.tips.user.service.UserService;

@Controller
@RequestMapping(value = {"", "/"})
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired UserService userService;
	@Autowired CodeService codeService;

	/**
	 * 로그인 화면
	 */
	@RequestMapping(value = {"/login.do"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public String login(Locale locale, Model model, HttpServletResponse response) {
		return "user/login";
	}
	
	/**
	 * 로그인 
	 */
	@RequestMapping(value = {"/logout.do"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public ModelAndView  logout(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response, HttpSession session) {

		session.setAttribute("user_id", null);

		ModelAndView mav = new ModelAndView();
        mav.setViewName("user/login");
        mav.addObject("msg", "logout");
        
		return mav;
	}
	/**
	 * 로그인 
	 */
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	public ModelAndView  login(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		
		logger.info("User id : {}", user.getUserId());
		logger.info("User pwd : {}", user.getPwd());
		
		String success = ReturnCode.RETURN_SUCCESS;
		
		// 먼저 같은 이름과 이메일 주소로 가입된것을 걸러내야됨.
		User paramUser = new User();
		paramUser.setUserId(user.getUserId());
		paramUser.setPwd(user.getPwd());
		
		ModelAndView mav = new ModelAndView();
		try {
			paramUser = userService.get(paramUser);
		} catch (Exception e) {
			logger.error("로그인 DB 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}

		logger.info("paramUser : {}", paramUser);
		
		if (paramUser != null) {
			//model.addAttribute("success", success);	
			mav.setViewName("/main/main");
            mav.addObject("msg", "success");
			session.setAttribute("user_id", paramUser.getUserId());
			session.setAttribute("user_name", paramUser.getName());
			logger.info("user.getName() : {}", paramUser.getName());
			model.addAttribute("userName", paramUser.getName());
			if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
				logger.info("[{}] 로그인 성공.", user.getUserId());
			}
			
		}else {
			model.addAttribute("success", ReturnCode.RETURN_FAIL);
			logger.info("[{}] 로그인 실패.", user.getUserId());

            mav.setViewName("user/login");
            mav.addObject("msg", "failure");
		}
		
		return mav;
	}
	
	/**
	 * 사용자 가입 화면 
	 */
	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public String signup(Locale locale, Model model, HttpServletResponse response) {
		
		List<Code> codeList = new ArrayList<Code>();
		Code code = new Code();
		
		try {
			code.setCodeGroupId(Constants.EMAIL_ADDRESS_CD);
			codeList = codeService.getList(code);
		} catch (Exception e) {
			logger.error("코드 조회 오류 : {}", e.getMessage());
		}
		
		model.addAttribute("codeList", codeList);
		
		return "user/signup";
	}
	
	/**
	 * 아이디 중복 체크
	 */
	@ResponseBody
	@RequestMapping(value = {"/check"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	public String check(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response) {
		
		// 중복확인
		boolean flag = true;
		
		try {
			if(userService.count(user) > 0) flag = false;
		} catch (Exception e) {
			logger.error("아이디 중복확인 : DB조회 오류 : {}", e.getMessage());
		}
		
		return flag+"";
	}
	
	/**
	 * 사용자 가입 
	 */
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	public String sign(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response) {
		String success = ReturnCode.RETURN_SUCCESS;
		
		// 먼저 같은 이름과 이메일 주소로 가입된것을 걸러내야됨.
		User paramUser = new User();
		paramUser.setEmailId(user.getEmailId());
		paramUser.setEmailAddrNm(user.getEmailAddrNm());
		
		try {
			paramUser = userService.get(paramUser);
		} catch (Exception e) {
			logger.error("회원가입 (이메일 중복체크) : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}

		if (paramUser == null) {
			try {
				userService.add(user);
			} catch (Exception e) {
				logger.error("회원가입 : DB삽입 에러 : {}", e.getMessage());
				success = ReturnCode.RETURN_FAIL;
			}
		} else {
			// 이미 이메일로 가입된 회원이 있다면
			success = ReturnCode.RETURN_FAIL;
			model.addAttribute("user", paramUser);
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}] 회원가입 성공.", user.getUserId());
		}
		
		return "/user/ajax/signup";
	}
	
	/**
	 * 아이디 찾기 화면
	 */
	@RequestMapping(value = {"/find/id"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public String id(Locale locale, Model model, HttpServletResponse response) {
		return "user/id";
	}
	
	/**
	 * 비밀번호 찾기 화면
	 */
	@RequestMapping(value = {"/find/pwd"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public String pwd(Locale locale, Model model, HttpServletResponse response) {
		return "user/pwd";
	}
	
	/**
	 * 비밀번호 찾기
	 */
	@RequestMapping(value = {"/find/pwd"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	public String pwd(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response) {
		String success = ReturnCode.RETURN_SUCCESS;
		
		try {
			user = userService.get(user);
		} catch (Exception e) {
			logger.error("비밀번호 찾기 : DB조회 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		return "/user/ajax/pwd";
	}

}
