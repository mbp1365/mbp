package com.mediaflow.tips.photo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/photo")
public class PhotoMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoMainController.class);
	
	@Autowired MessageSource messageSource;

	/**
	 * photo_assign 화면
	 */
	@RequestMapping(value = {"/photo_assign"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public ModelAndView photo_assign(Locale locale, Model model, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/photo/photo_assign");
		return modelAndView;
	}

	/**
	 * brand 화면
	 */
	@RequestMapping(value = {"/brand"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView brand(Locale locale, Model model, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("customer/brand");
		return modelAndView;
	}
	
	/**
	 * contract 화면
	 */
	@RequestMapping(value = {"/contract"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView contract(Locale locale, Model model, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("customer/contract");
		return modelAndView;
	}
	
	

}
