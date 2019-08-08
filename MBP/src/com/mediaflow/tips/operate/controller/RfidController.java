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
import com.mediaflow.tips.common.domain.Rfid;
import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.Customer;
import com.mediaflow.tips.common.domain.Rfid;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.operate.controller.model.RfidList;
import com.mediaflow.tips.operate.controller.model.UserList;
import com.mediaflow.tips.operate.service.RfidService;
import com.mediaflow.tips.operate.service.CodeManagerService;
import com.mediaflow.tips.operate.service.UserManagerService;
import com.mediaflow.tips.utils.OpenStringUtils;

@Controller
@RequestMapping(value = {"", "/operate/rfidmgr"})
public class RfidController {
	
	private static final Logger logger = LoggerFactory.getLogger(RfidController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired RfidService rfidService;

	/**
	 * 리스트 화면
	 */
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String list(@ModelAttribute Rfid rfid, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("cate: " + rfid.toString());

		logger.info("Constants.MGRID_PAGE_LIST_SIZE: " + Constants.MGRID_PAGE_LIST_SIZE);
		int totalCount = rfidService.count(rfid);
		//int totalCount = 100;
		rfid.setStartIndex(rfid.getqCurrPage()*Constants.MGRID_PAGE_LIST_SIZE);
		rfid.setPageListSize(Constants.MGRID_PAGE_LIST_SIZE);
		logger.info("totalCount: " + totalCount);
		double totalPageTemp = (double) totalCount / Constants.MGRID_PAGE_LIST_SIZE;
		int totalPage = (int) Math.ceil(totalPageTemp);
		List<Rfid> returnList = rfidService.list(rfid);

		returnMap.put("success", "success");
		returnMap.put("totalCount", totalCount);
		returnMap.put("totalPage", totalPage);
		returnMap.put("pageListSize", Constants.MGRID_PAGE_LIST_SIZE);
		returnMap.put("list", returnList);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

	/**
	 * 저장처리 
	 * 2018.01.12
	 * author : hokyung.lee
	 */
	@RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String saveappr(@ModelAttribute Rfid rfid, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("rfid: " + rfid);

		
		Rfid temp = rfidService.get(rfid);
		logger.info("rfid get: " + temp);
		if (temp != null) {
			returnMap.put("success", "fail");
			returnMap.put("message", "등록된 아이디가 있습니다. 확인바랍니다.");
		}else {
			if (rfid.getUpdateYn().equals("N")) {
				rfidService.add(rfid);
			}else if (rfid.getUpdateYn().equals("Y")) {
				rfidService.mod(rfid);
			}
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
	public String modUserInfo(@ModelAttribute Rfid rfid, Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		String success = ReturnCode.RETURN_SUCCESS;

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		
		try {
			rfid.setUptId((String) session.getAttribute("user_id"));
			rfidService.mod(rfid);
		} catch (Exception e) {
			logger.error("카테고리 정보 수정 : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}] RFID 정보 수정 성공.", rfid.getRfidId());
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
	public String del(@ModelAttribute RfidList rfidList, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("rfidList: " + rfidList.getRfidList().size());

		for (int ii = 0; ii < rfidList.getRfidList().size(); ii++) {
			Rfid rfid = rfidList.getRfidList().get(ii);
			rfid.setDelId((String) session.getAttribute("user_id"));
			rfidService.mod(rfid);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

}
