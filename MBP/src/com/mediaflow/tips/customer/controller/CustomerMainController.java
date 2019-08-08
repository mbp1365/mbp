package com.mediaflow.tips.customer.controller;

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
@RequestMapping("/customermenu")
public class CustomerMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerMainController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired CodeService codeService;

	/**
	 * customer 화면
	 */
	@RequestMapping(value = {"/customer"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public String customer(Locale locale, Model model, HttpServletResponse response, HttpSession session) {

		if (session.getAttribute("user_id") == null) {
//			ModelAndView mav = new ModelAndView();
//	        mav.setViewName("user/login");
	        model.addAttribute("msg", "logout");
	        return "user/login";
		}
		List<Code> codeList = new ArrayList<Code>();
		Code code = new Code();
		
		try {
			code.setCodeGroupId(Constants.EMAIL_ADDRESS_CD);
			codeList = codeService.getList(code);
		} catch (Exception e) {
			logger.error("코드 조회 오류 : {}", e.getMessage());
		}

		logger.info("userId: "+session.getAttribute("user_id"));
		logger.info("userName: "+session.getAttribute("user_name"));
		model.addAttribute("userName", session.getAttribute("user_name"));
		model.addAttribute("codeList", codeList);
		
		return "customer/customer";
	}

	/**
	 * brand 화면
	 */
	@RequestMapping(value = {"/brand"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public String brand(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("user_id") == null) {
	        model.addAttribute("msg", "logout");
	        return "user/login";
		}
		
		List<Code> codeList = new ArrayList<Code>();
		Code code = new Code();
		
		try {
			code.setCodeGroupId(Constants.EMAIL_ADDRESS_CD);
			codeList = codeService.getList(code);
		} catch (Exception e) {
			logger.error("코드 조회 오류 : {}", e.getMessage());
		}

		logger.info("userId: "+session.getAttribute("user_id"));
		logger.info("userName: "+session.getAttribute("user_name"));
		model.addAttribute("userName", session.getAttribute("user_name"));
		model.addAttribute("codeList", codeList);
		
		return "customer/brand";
	}
	
	/**
	 * contract 화면
	 */
	@RequestMapping(value = {"/contract"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView contract(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		if (session.getAttribute("user_id") == null) {
//			ModelAndView mav = new ModelAndView();
	        modelAndView.setViewName("user/login");
			modelAndView.addObject("msg", "logout");
	        return modelAndView;
		}
		
		logger.info("userId: "+session.getAttribute("user_id"));
		logger.info("userName: "+session.getAttribute("user_name"));
		model.addAttribute("userName", session.getAttribute("user_name"));
		modelAndView.setViewName("customer/contract");
		return modelAndView;
	}
	
	

}
