package com.mediaflow.tips.operate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.google.gson.Gson;
import com.mediaflow.tips.common.Constants;
import com.mediaflow.tips.common.ReturnCode;
import com.mediaflow.tips.common.domain.Category;
import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.operate.controller.model.CategoryList;
import com.mediaflow.tips.operate.controller.model.UserList;
import com.mediaflow.tips.operate.service.CategoryService;
import com.mediaflow.tips.operate.service.CodeManagerService;
import com.mediaflow.tips.operate.service.UserManagerService;
import com.mediaflow.tips.utils.OpenStringUtils;

@Controller
@RequestMapping(value = {"", "/operate/category"})
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired CategoryService categoryService;

	/**
	 * 로그인 화면
	 */
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String list(@ModelAttribute Category cate, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("cate: " + cate.toString());

		List<Category> returnList = categoryService.list(cate);

		returnMap.put("success", "success");
		returnMap.put("list", returnList);

		logger.info(" returnList.size : " + returnList.size());
		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

	/**
	 * 저장처리 
	 * 2018.01.12
	 * author : hokyung.lee
	 */
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String saveappr(@ModelAttribute Category category, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("category: " + category);


		Category temp = categoryService.get(category);
		if (temp != null) {
			returnMap.put("success", "fail");
			returnMap.put("message", "duplicate id");
		}else {
			categoryService.add(category);
			returnMap.put("success", "success");
		}
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

	/**
	 * 사용자 정보 수정 
	 */
	@RequestMapping(value = {"/mod"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String modUserInfo(@ModelAttribute Category category, Locale locale, Model model, HttpServletResponse response) {
		String success = ReturnCode.RETURN_SUCCESS;

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		
		try {
			categoryService.mod(category);
		} catch (Exception e) {
			logger.error("카테고리 정보 수정 : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}] 카테고리 정보 수정 성공.", category.getCateId());
		}
		
		returnMap.put("success", success);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	/**
	 * 승인처리 
	 * 2018.01.09
	 * author : hokyung.lee
	 */
	@RequestMapping(value = {"/del"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String del(@ModelAttribute CategoryList categoryList, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("categoryList: " + categoryList);

		for (int ii = 0; ii < categoryList.getCategoryList().size(); ii++) {
			Category categoryTemp = categoryList.getCategoryList().get(ii);
			categoryTemp.setUseYn("N");
			categoryService.del(categoryTemp);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

}
