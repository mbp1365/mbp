package com.mediaflow.tips.customer.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.mediaflow.tips.common.Constants;
import com.mediaflow.tips.common.ReturnCode;
import com.mediaflow.tips.common.domain.Contract;
import com.mediaflow.tips.common.domain.ContractFile;
import com.mediaflow.tips.common.domain.Customer;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.common.service.CodeService;
import com.mediaflow.tips.customer.controller.model.ContractFileList;
import com.mediaflow.tips.customer.controller.model.ContractList;
import com.mediaflow.tips.customer.controller.model.CustomerList;
import com.mediaflow.tips.customer.service.ContractFileService;
import com.mediaflow.tips.customer.service.ContractService;
import com.mediaflow.tips.customer.service.CustomerService;
import com.mediaflow.tips.utils.OpenStringUtils;

@Controller
@RequestMapping(value = {"/contractfile", "/contractfile"})
public class ContractFileController {
	private static String uploadingdir = "D:\\temp\\tipsfiles\\";
	private static final Logger logger = LoggerFactory.getLogger(ContractFileController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired ContractFileService contractFileService;
	
	
	/**
	 * Contract 리스트 요청
	 */
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String contractFileList(@ModelAttribute ContractFile contractFile, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("contractFile: " + contractFile.toString());
		

		
		// 조회 처리
		List<ContractFile> returnList = contractFileService.list(contractFile);

		for (int ii = 0; ii < returnList.size(); ii++) {
			ContractFile temp = returnList.get(ii);
			//"download":"/tips/ax5uploader/download?sessionId=test1&id=6a449c67-3183-489c-83e6-82a27f2fb5b2"
			String download = "/tips/ax5uploader/download?sessionId="+temp.getSessionId()+"&id="+temp.getId();
			temp.setDownload(download);
		}
		returnMap.put("success", "success");
		returnMap.put("list", returnList);

		logger.info(" returnList.size : " + returnList.size());
		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	
	/**
	 * 고객정보 리스트 요청
	 */
	@RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String addContract(@ModelAttribute ContractFileList contractFileList, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		String sessionUserId = (String) session.getAttribute("user_id");
		logger.info("session user_id: " + sessionUserId);
		logger.info("########## contractFileList.getCortId: " + contractFileList.getCortId());
		ContractFile contractFileDel = new ContractFile();
		contractFileDel.setCortId(contractFileList.getCortId());
		contractFileService.del(contractFileDel);
		
		if (contractFileList.getContractFileList() != null) {
			for (int ii = 0; ii < contractFileList.getContractFileList().size(); ii++) {
				ContractFile contractFile = contractFileList.getContractFileList().get(ii);
				logger.info("contractFile: " + contractFile.toString());
				contractFileService.add(contractFile);
			}
		}
		returnMap.put("success", "success");
			
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
		
	
	
	/**
	 * 삭제 처리 완전 삭제
	 * 2018.01.09
	 * author : hokyung.lee
	 */						 
	@RequestMapping(value = {"/del"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String delContractList(@ModelAttribute ContractFileList contractFileList, Model model, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("user_id");
		
		if (sessionId == null) {
			
		}
		
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("contractFileList: " + contractFileList);
		logger.info("contractFileList.getContractList(): " + contractFileList.getContractFileList());

		for (int ii = 0; ii < contractFileList.getContractFileList().size(); ii++) {
			ContractFile contractFile = contractFileList.getContractFileList().get(ii);
			contractFileService.del(contractFile);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	/**
	 * 삭제 처리 완전 삭제
	 * 2018.01.09
	 * author : hokyung.lee
	 */						 
	@RequestMapping(value = {"/delfile"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String delByCortId(@ModelAttribute ContractFile contractFile, Model model, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("user_id");
		
		if (sessionId == null) {
			
		}
				
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		contractFileService.delfile(contractFile);
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	

}
