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
import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.operate.controller.model.UserList;
import com.mediaflow.tips.operate.service.CodeManagerService;
import com.mediaflow.tips.operate.service.UserManagerService;
import com.mediaflow.tips.utils.OpenStringUtils;

@Controller
@RequestMapping(value = {"", "/operate/user"})
public class UserManagerController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserManagerController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired UserManagerService userManagerService;
	@Autowired CodeManagerService codeManagerService;

	/**
	 * 로그인 화면
	 */
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String list(@ModelAttribute User user, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("user: " + user.toString());

		user.setqStartIndex(user.getqCurrPage()*Constants.MGRID_PAGE_LIST_SIZE);
		user.setqPageListSize(Constants.MGRID_PAGE_LIST_SIZE);

		int totalCount = userManagerService.count(user);
		double totalPageTemp = (double) totalCount / Constants.MGRID_PAGE_LIST_SIZE;
		int totalPage = (int) Math.ceil(totalPageTemp);
		List<User> returnList = userManagerService.list(user);

		returnMap.put("success", "success");
		returnMap.put("totalCount", totalCount);
		returnMap.put("totalPage", totalPage);
		returnMap.put("pageListSize", Constants.MGRID_PAGE_LIST_SIZE);
		returnMap.put("list", returnList);

		logger.info(" returnList.size : " + returnList.size());
		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	

	/**
	 * 승인처리 
	 * 2018.01.09
	 * author : hokyung.lee
	 */
	@RequestMapping(value = {"/saveappr"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String saveappr(@ModelAttribute UserList userList, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("userList: " + userList);

		for (int ii = 0; ii < userList.getUserList().size(); ii++) {
			User tempUser = userList.getUserList().get(ii);
			tempUser.setApprDt(OpenStringUtils.getCurrentTimeFullDisplayHmmss());
			userManagerService.mod(tempUser);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

	/**
	 * 사용자 정보 수정 
	 */
	@RequestMapping(value = {"/moduser"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String modUserInfo(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response) {
		String success = ReturnCode.RETURN_SUCCESS;

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		
		try {
			userManagerService.mod(user);
		} catch (Exception e) {
			logger.error("회원정보 수정 : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}] 회원정보 수정 성공.", user.getUserId());
		}
		
		returnMap.put("success", success);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
//	/**
//	 * 사용자 가입 화면 
//	 */
//	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
//	public String signup(Locale locale, Model model, HttpServletResponse response) {
//		
//		List<Code> codeList = new ArrayList<Code>();
//		Code code = new Code();
//		
//		try {
//			code.setCodeGroupId(Constants.EMAIL_ADDRESS_CD);
//			codeList = codeManagerService.getList(code);
//		} catch (Exception e) {
//			logger.error("코드 조회 오류 : {}", e.getMessage());
//		}
//		
//		model.addAttribute("codeList", codeList);
//		
//		return "user/signup";
//	}
//	
//	/**
//	 * 아이디 중복 체크
//	 */
//	@ResponseBody
//	@RequestMapping(value = {"/check"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
//	public String check(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response) {
//		
//		// 중복확인
//		boolean flag = true;
//		
//		try {
//			if(userManagerService.count(user) > 0) flag = false;
//		} catch (Exception e) {
//			logger.error("아이디 중복확인 : DB조회 오류 : {}", e.getMessage());
//		}
//		
//		return flag+"";
//	}
//	
//	
//	/**
//	 * 아이디 찾기 화면
//	 */
//	@RequestMapping(value = {"/find/id"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
//	public String id(Locale locale, Model model, HttpServletResponse response) {
//		return "user/id";
//	}
//	
//	/**
//	 * 비밀번호 찾기 화면
//	 */
//	@RequestMapping(value = {"/find/pwd"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
//	public String pwd(Locale locale, Model model, HttpServletResponse response) {
//		return "user/pwd";
//	}
//	
//	/**
//	 * 비밀번호 찾기
//	 */
//	@RequestMapping(value = {"/find/pwd"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
//	public String pwd(@ModelAttribute User user, Locale locale, Model model, HttpServletResponse response) {
//		String success = ReturnCode.RETURN_SUCCESS;
//		
//		try {
//			user = userManagerService.get(user);
//		} catch (Exception e) {
//			logger.error("비밀번호 찾기 : DB조회 에러 : {}", e.getMessage());
//			success = ReturnCode.RETURN_FAIL;
//		}
//		
//		model.addAttribute("success", success);
//		return "/user/ajax/pwd";
//	}

}
