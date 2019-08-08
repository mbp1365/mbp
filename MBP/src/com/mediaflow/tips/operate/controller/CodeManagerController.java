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
import com.mediaflow.tips.common.domain.CodeGroup;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.operate.controller.model.CategoryList;
import com.mediaflow.tips.operate.controller.model.CodeGroupList;
import com.mediaflow.tips.operate.controller.model.CodeList;
import com.mediaflow.tips.operate.controller.model.UserList;
import com.mediaflow.tips.operate.service.CategoryService;
import com.mediaflow.tips.operate.service.CodeManagerService;
import com.mediaflow.tips.operate.service.UserManagerService;
import com.mediaflow.tips.utils.OpenStringUtils;

@Controller
@RequestMapping(value = {"", "/operate/codemgr"})
public class CodeManagerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeManagerController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired CodeManagerService codeManagerService;

	/**
	 * 그룹코드 리스트 요청
	 */
	@RequestMapping(value = {"/codeGroupList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String codeGroupList(@ModelAttribute CodeGroup codeGroup, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("codeGroup: " + codeGroup.toString());

		List<CodeGroup> returnList = codeManagerService.codeGroupList(codeGroup);

		returnMap.put("success", "success");
		returnMap.put("list", returnList);

		logger.info(" returnList.size : " + returnList.size());
		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	/**
	 * 코드 리스트 요청
	 */
	@RequestMapping(value = {"/codeList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String codeList(@ModelAttribute Code code, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("code: " + code.toString());

		List<Code> returnList = codeManagerService.codeList(code);

		returnMap.put("success", "success");
		returnMap.put("list", returnList);

		logger.info(" returnList.size : " + returnList.size());
		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}

	/**
	 * 그룹코드 중복여부 판별
	 */
	@RequestMapping(value = {"/checkCodeGroupDupl"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String checkCodeGroupDupl(@ModelAttribute CodeGroup codeGroup, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("codeGroup: " + codeGroup.toString());

		CodeGroup checkedCodeGroup = codeManagerService.getCodeGroup(codeGroup);

		returnMap.put("success", "success");
		returnMap.put("checkedCodeGroup", checkedCodeGroup);

		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}

	/**
	 * 코드 중복여부 판별
	 */
	@RequestMapping(value = {"/checkCodeDupl"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String checkCodeDupl(@ModelAttribute Code code, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("code: " + code.toString());

		Code checkedCode = codeManagerService.getCode(code);

		returnMap.put("success", "success");
		returnMap.put("checkedCode", checkedCode);

		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	
	/**
	 * 코드그룹 저장처리 
	 * 2018.01.12
	 * author : hokyung.lee
	 */
	@RequestMapping(value = {"/addCodeGroup"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String addGroupCode(@ModelAttribute CodeGroup codeGroup, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("groupCode: " + codeGroup);

		if (codeGroup.getUpdateYn().equals("N")) {
			codeManagerService.addCodeGroup(codeGroup);
			returnMap.put("success", "success");
		}else {
			codeManagerService.modCodeGroup(codeGroup);
			returnMap.put("success", "success");
			
		}
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}

	/**
	 * 코드그룹 저장처리 
	 * 2018.01.12
	 * author : hokyung.lee
	 */
	@RequestMapping(value = {"/addCode"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String addCode(@ModelAttribute Code code, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("code: " + code);

		if (code.getUpdateYn().equals("N")) {
			codeManagerService.addCode(code);
			returnMap.put("success", "success");
		}else {
			codeManagerService.modCode(code);
			returnMap.put("success", "success");
			
		}
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}

	/**
	 * Group Code 정보 수정 
	 */
	@RequestMapping(value = {"/modCodeGroup"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String modcodeGroup(@ModelAttribute CodeGroup codeGroup, Locale locale, Model model, HttpServletResponse response) {
		String success = ReturnCode.RETURN_SUCCESS;

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		
		try {
			codeManagerService.modCodeGroup(codeGroup);
		} catch (Exception e) {
			logger.error("카테고리 정보 수정 : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}]  정보 수정 성공.", codeGroup.getCodeGroupId());
		}
		
		returnMap.put("success", success);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	/**
	 * 삭제 처리 useYn = 'N'으로 세팅 
	 * 2018.01.09
	 * author : hokyung.lee
	 */						 
	@RequestMapping(value = {"/delCodeGroupList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String delCodeGroupList(@ModelAttribute CodeGroupList codeGroupList, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("codeGroupList: " + codeGroupList);
		logger.info("codeGroupList.getCodeGroupList(): " + codeGroupList.getCodeGroupList());

		for (int ii = 0; ii < codeGroupList.getCodeGroupList().size(); ii++) {
			CodeGroup codeGroupTemp = codeGroupList.getCodeGroupList().get(ii);
			codeGroupTemp.setUseYn("N");
			codeManagerService.modCodeGroup(codeGroupTemp);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	/**
	 * 삭제 처리 useYn = 'N'으로 세팅 
	 * 2018.01.09
	 * author : hokyung.lee
	 */						 
	@RequestMapping(value = {"/delCodeList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String delCodeList(@ModelAttribute CodeList codeList, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("codeList: " + codeList);
		logger.info("codeList.getCodeList(): " + codeList.getCodeList());

		for (int ii = 0; ii < codeList.getCodeList().size(); ii++) {
			Code codeTemp = codeList.getCodeList().get(ii);
			codeTemp.setUseYn("N");
			logger.info("codeTemp: " + codeTemp);
			codeManagerService.modCode(codeTemp);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	

}
