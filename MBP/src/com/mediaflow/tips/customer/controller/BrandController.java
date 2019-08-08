package com.mediaflow.tips.customer.controller;

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
import com.mediaflow.tips.common.domain.Brand;
import com.mediaflow.tips.common.domain.Customer;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.common.service.CodeService;
import com.mediaflow.tips.customer.controller.model.BrandList;
import com.mediaflow.tips.customer.controller.model.CustomerList;
import com.mediaflow.tips.customer.service.BrandService;
import com.mediaflow.tips.customer.service.CustomerService;
import com.mediaflow.tips.utils.OpenStringUtils;

@Controller
@RequestMapping(value = {"/brand", "/brand"})
public class BrandController {
	
	private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired BrandService brandService;
	
	/**
	 * 고객사 이메일 정보 중복 체크
	 */
	@RequestMapping(value = {"/checkEmailDupl"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String checkEmailDupl(@ModelAttribute Brand brand, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("brand: " + brand.toString());
		logger.info("brandService: " + brandService.toString());
		

		int checkCount = brandService.countEmail(brand);
		returnMap.put("success", "success");
		returnMap.put("checkCount", checkCount);
		logger.info("returnMap: "+returnMap);

//		List<brand> returnList = brandService.list(brand);
//
//		returnMap.put("success", "success");
//		returnMap.put("list", returnList);

		logger.info(" returnMap : " + returnMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	/**
	 * Brand 리스트 요청
	 */
	@RequestMapping(value = {"/brandList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String brandList(@ModelAttribute Brand brand, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("brand: " + brand.toString());
		

		logger.info("Constants.MGRID_PAGE_LIST_SIZE: " + Constants.MGRID_PAGE_LIST_SIZE);
		
		// 전체 갯수 확인.
		int totalCount = brandService.count(brand);
		//int totalCount = 100;
		brand.setStartIndex(brand.getqCurrPage()*Constants.MGRID_PAGE_LIST_SIZE);
		brand.setPageListSize(Constants.MGRID_PAGE_LIST_SIZE);
		logger.info("totalCount: " + totalCount);
		// 한화면에 표시되는 리스트 사이즈로 총 페이지 수 연산.
		double totalPageTemp = (double) totalCount / Constants.MGRID_PAGE_LIST_SIZE;
		int totalPage = (int) Math.ceil(totalPageTemp);
		
		// 조회 처리
		List<Brand> returnList = brandService.list(brand);

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
	 * Brand 리스트 요청
	 */
	@RequestMapping(value = {"/combobrandList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String combobrandList(@ModelAttribute Brand brand, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

			
		// 조회 처리
		List<Brand> returnList = brandService.comboList(brand);

		returnMap.put("success", "success");
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
	@RequestMapping(value = {"/addBrand"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String addBrand(@ModelAttribute Brand brand, Model model, HttpServletResponse response, HttpSession session) {
		

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("brand: " + brand);
		brand.setRegId("good");

		if (brand.getUpdateYn().equals("N")) {
			brandService.add(brand);
			returnMap.put("success", "success");
		}else {
			brandService.mod(brand);
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
	@RequestMapping(value = {"/saveBrand"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String add(@ModelAttribute Brand brand, Model model, HttpServletResponse response, HttpSession session) {

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("brand: " + brand);
		brand.setRegId("good");

		if (brand.getUpdateYn().equals("N")) {
			brandService.add(brand);
			returnMap.put("success", "success");
		}else {
			brandService.mod(brand);
			returnMap.put("success", "success");
			
		}
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}

	

	/**
	 * Group Code 정보 수정 
	 */
	@RequestMapping(value = {"/mod"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String mod(@ModelAttribute Brand brand, Locale locale, Model model, HttpServletResponse response) {
		String success = ReturnCode.RETURN_SUCCESS;

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		
		try {
			brandService.mod(brand);
		} catch (Exception e) {
			logger.error("카테고리 정보 수정 : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}]  정보 수정 성공.", brand.getCustId());
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
	@RequestMapping(value = {"/delBrandList"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String delCustList(@ModelAttribute BrandList brandList, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("brandList: " + brandList);
		logger.info("brandList.getBrandList(): " + brandList.getBrandList());

		for (int ii = 0; ii < brandList.getBrandList().size(); ii++) {
			Brand brand = brandList.getBrandList().get(ii);
			brand.setDelId("GOOD");
			brandService.del(brand);
		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	
	

}
