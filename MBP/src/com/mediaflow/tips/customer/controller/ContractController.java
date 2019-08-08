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
import com.mediaflow.tips.customer.controller.model.ContractList;
import com.mediaflow.tips.customer.controller.model.CustomerList;
import com.mediaflow.tips.customer.service.ContractFileService;
import com.mediaflow.tips.customer.service.ContractService;
import com.mediaflow.tips.customer.service.CustomerService;
import com.mediaflow.tips.utils.OpenStringUtils;

@Controller
@RequestMapping(value = {"/contract", "/contract"})
public class ContractController {
	private static String uploadingdir = "D:\\temp\\tipsfiles\\";
	private static final Logger logger = LoggerFactory.getLogger(ContractController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired ContractService contractService;
	@Autowired ContractFileService contractFileService;
	
	/**
	 * 고객사 이메일 정보 중복 체크
	 */
	@RequestMapping(value = {"/checkContractNameDupl"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String checkContractNameDupl(@ModelAttribute Contract contract, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("contract: " + contract.toString());
		logger.info("contractService: " + contractService.toString());
		

		int checkCount = contractService.countByName(contract);
		returnMap.put("success", "success");
		returnMap.put("checkCount", checkCount);
		logger.info("returnMap: "+returnMap);

//		List<contract> returnList = contractService.list(contract);
//
//		returnMap.put("success", "success");
//		returnMap.put("list", returnList);

		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	/**
	 * Contract 리스트 요청
	 */
	@RequestMapping(value = {"/contractList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String contractList(@ModelAttribute Contract contract, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("contract: " + contract.toString());
		

		logger.info("Constants.MGRID_PAGE_LIST_SIZE: " + Constants.MGRID_PAGE_LIST_SIZE);
		
		// 전체 갯수 확인.
		int totalCount = contractService.count(contract);
		//int totalCount = 100;
		contract.setStartIndex(contract.getqCurrPage()*Constants.MGRID_PAGE_LIST_SIZE);
		contract.setPageListSize(Constants.MGRID_PAGE_LIST_SIZE);
		logger.info("totalCount: " + totalCount);
		// 한화면에 표시되는 리스트 사이즈로 총 페이지 수 연산.
		double totalPageTemp = (double) totalCount / Constants.MGRID_PAGE_LIST_SIZE;
		int totalPage = (int) Math.ceil(totalPageTemp);
		
		// 조회 처리
		List<Contract> returnList = contractService.list(contract);
		
		returnMap.put("success", "success");
		returnMap.put("totalCount", totalCount);
		returnMap.put("totalPage", totalPage);
		returnMap.put("pageListSize", Constants.MGRID_PAGE_LIST_SIZE);
		returnMap.put("list", returnList);

		logger.info("returnMap: "+returnMap);

//		List<Customer> returnList = customerService.list(customer);
//
//		returnMap.put("success", "success");
//		returnMap.put("list", returnList);

		logger.info(" returnList.size : " + returnList.size());
		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	
	/**
	 * 고객정보 리스트 요청
	 */
	@RequestMapping(value = {"/addContract"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String addContract(@ModelAttribute Contract contract, Model model, HttpServletResponse response, HttpSession session) {
		

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("contract: " + contract);
		contract.setRegId("good");

		String sessionUserId = (String) session.getAttribute("user_id");
		logger.info("session user_id: " + sessionUserId);
		
		if (contract.getUpdateYn().equals("N")) {
			contract.setRegId(sessionUserId);
			contractService.add(contract);
			logger.info("After inserting contract cortId: "+contract.getCortId());
			returnMap.put("success", "success");
			returnMap.put("cortId", contract.getCortId());
		}else {
			contract.setUptId(sessionUserId);
			contractService.mod(contract);
			returnMap.put("success", "success");
			
		}
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
		
	/**
	 * Contract 정보 수정 
	 */
	@RequestMapping(value = {"/mod"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String mod(@ModelAttribute Contract contract, Locale locale, Model model, HttpServletResponse response) {
		String success = ReturnCode.RETURN_SUCCESS;

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		
		try {
			if (contract.getRegDt().trim().equals("")) {
				contract.setRegDt(null);
			}
			contractService.mod(contract);
		} catch (Exception e) {
			logger.error("카테고리 정보 수정 : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}]  정보 수정 성공.", contract.getCortId());
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
	@RequestMapping(value = {"/delContractList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String delContractList(@ModelAttribute ContractList contractList, Model model, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("user_id");
		
		if (sessionId == null) {
			
		}
		
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("contractList: " + contractList);
		logger.info("contractList.getContractList(): " + contractList.getContractList());

		for (int ii = 0; ii < contractList.getContractList().size(); ii++) {
			Contract contract = contractList.getContractList().get(ii);
			contract.setDelId(sessionId);
			contractService.del(contract);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	

}
