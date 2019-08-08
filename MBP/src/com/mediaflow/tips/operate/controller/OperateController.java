package com.mediaflow.tips.operate.controller;

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


@Controller
@RequestMapping("/operate")
public class OperateController {
	
	private static final Logger logger = LoggerFactory.getLogger(OperateController.class);
	
	@Autowired MessageSource messageSource;

	/**
	 * 공통코드 화면
	 */
	@RequestMapping(value = {"/code"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public ModelAndView customer(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user_id") == null) {
	        model.addAttribute("msg", "logout");
	        modelAndView.setViewName("user/login"); 
		}else {
			model.addAttribute("userName", session.getAttribute("user_name"));
			modelAndView.setViewName("operate/code");			
		}
		return modelAndView;
	}
	
	/**
	 * category 화면
	 */
	@RequestMapping(value = {"/category"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView category(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user_id") == null) {
	        model.addAttribute("msg", "logout");
	        modelAndView.setViewName("user/login"); 
		}else {
			model.addAttribute("userName", session.getAttribute("user_name"));
			modelAndView.setViewName("operate/category");			
		}
		return modelAndView;
	}

	/**
	 * RFID 화면
	 */
	@RequestMapping(value = {"/rfid"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public ModelAndView rfid(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user_id") == null) {
	        model.addAttribute("msg", "logout");
	        modelAndView.setViewName("user/login"); 
		}else {
			model.addAttribute("userName", session.getAttribute("user_name"));
			modelAndView.setViewName("operate/rfid");			
		}
		
		return modelAndView;
	}
	
	/**
	 * user 화면
	 */
	@RequestMapping(value = {"/user"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView user(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user_id") == null) {
	        model.addAttribute("msg", "logout");
	        modelAndView.setViewName("user/login"); 
		}else {
			model.addAttribute("userName", session.getAttribute("user_name"));
			modelAndView.setViewName("operate/user");			
		}
		
		return modelAndView;
	}

	/**
	 * authority 화면
	 */
	@RequestMapping(value = {"/authority"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView authority(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user_id") == null) {
	        model.addAttribute("msg", "logout");
	        modelAndView.setViewName("user/login"); 
		}else {
			model.addAttribute("userName", session.getAttribute("user_name"));
			modelAndView.setViewName("operate/authority");			
		}
		
		return modelAndView;
	}

	/**
	 * menu 화면
	 */
	@RequestMapping(value = {"/menu"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView menu(Locale locale, Model model, HttpServletResponse response, HttpSession session) {

		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user_id") == null) {
	        model.addAttribute("msg", "logout");
	        modelAndView.setViewName("user/login"); 
		}else {
			model.addAttribute("userName", session.getAttribute("user_name"));
			modelAndView.setViewName("operate/menu");			
		}
		
		return modelAndView;
	}
	
	

}
