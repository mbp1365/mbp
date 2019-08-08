package com.mediaflow.tips.warehousing.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mediaflow.tips.common.Constants;
import com.mediaflow.tips.common.ReturnCode;
import com.mediaflow.tips.common.domain.Rfid;
import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.Customer;
import com.mediaflow.tips.common.domain.Product;
import com.mediaflow.tips.common.domain.Rfid;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.operate.controller.model.RfidList;
import com.mediaflow.tips.operate.controller.model.UserList;
import com.mediaflow.tips.operate.service.RfidService;
import com.mediaflow.tips.operate.service.CodeManagerService;
import com.mediaflow.tips.operate.service.UserManagerService;
import com.mediaflow.tips.utils.OpenStringUtils;
import com.mediaflow.tips.warehousing.service.IncomeService;

@Controller
@RequestMapping(value = {"", "/warehousing/income"})
public class IncomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(IncomeController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired IncomeService incomeService;

	@Value("${ax5uploader.repository.path}")
    private String filePath;
	/**
	 * 리스트 화면
	 */
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String list(@ModelAttribute Product product, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("product: " + product.toString());

		product.setqStartIndex(product.getqCurrPage()*Constants.SGRID_PAGE_LIST_SIZE);
		product.setqPageListSize(Constants.SGRID_PAGE_LIST_SIZE);
		
		logger.info("Constants.SGRID_PAGE_LIST_SIZE: " + Constants.SGRID_PAGE_LIST_SIZE);
		int totalCount = incomeService.count(product);
		//int totalCount = 100;
		product.setStartIndex(product.getqCurrPage()*Constants.SGRID_PAGE_LIST_SIZE);
		product.setPageListSize(Constants.SGRID_PAGE_LIST_SIZE);
		logger.info("totalCount: " + totalCount);
		double totalPageTemp = (double) totalCount / Constants.SGRID_PAGE_LIST_SIZE;
		int totalPage = (int) Math.ceil(totalPageTemp);
		List<Product> returnList = incomeService.list(product);

		returnMap.put("success", "success");
		returnMap.put("totalCount", totalCount);
		returnMap.put("totalPage", totalPage);
		returnMap.put("pageListSize", Constants.SGRID_PAGE_LIST_SIZE);
		returnMap.put("list", returnList);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	
	
	/**
	 * ProudctTemp 조회 화면
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = {"/popIncomeDetail.do"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView popExcelProduct(@RequestParam HashMap<String, String> map, Locale locale, Model model, HttpServletResponse response, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/warehousing/pop_income_detail");

		//List<ProductTemp> returnList = productTempService.list(productTemp);
		logger.info("## map: "+map);
		//ObjectMapper mapper = new ObjectMapper();// jackson lib for converting to json
        //String prodTmpList = mapper.writeValueAsString(returnList);// json string
		String prodId = map.get("prodId");
        System.out.println("======================== prodId: "+prodId);
		model.addAttribute("prodId", prodId);
		
		return modelAndView;
	}

	/**
	 * 저장처리 
	 * 2018.01.12
	 * author : hokyung.lee
	 */
	@RequestMapping(value = {"/add"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String saveappr(@ModelAttribute Product product, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("product: " + product);
		
		product.setRegId((String) session.getAttribute("user_id"));
		
		Product temp = incomeService.get(product);
		logger.info("PRODUCT get: " + temp);
		
		logger.info("product.getUpdateYn(): " + product.getUpdateYn());
		if (product.getUpdateYn().equals("N")) {
			incomeService.add(product);
		}else if (product.getUpdateYn().equals("Y")) {
			incomeService.mod(product);
		}
		returnMap.put("success", "success");
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

	/**
	 *  정보 수정 
	 */
	@RequestMapping(value = {"/mod"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String modUserInfo(@ModelAttribute Product product, Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		String success = ReturnCode.RETURN_SUCCESS;

		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		
		try {
			product.setUptId((String) session.getAttribute("user_id"));
			incomeService.mod(product);
		} catch (Exception e) {
			logger.error("카테고리 정보 수정 : DB삽입 에러 : {}", e.getMessage());
			success = ReturnCode.RETURN_FAIL;
		}
		
		model.addAttribute("success", success);
		
		if (StringUtils.equals(success, ReturnCode.RETURN_SUCCESS)) {
			logger.info("[{}] 제품 정보 수정 성공.", product.getProdId());
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

//		for (int ii = 0; ii < rfidList.getRfidList().size(); ii++) {
//			Product product = rfidList.getRfidList().get(ii);
//			product.setDelId((String) session.getAttribute("user_id"));
//			productService.mod(rfid);
//		}
		returnMap.put("success", "success");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

	@RequestMapping(value = "/uploadProdFile", method = RequestMethod.POST)
	@ResponseBody
	public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IllegalStateException, IOException {
	    //modelMap.addAttribute("file", file);
//	    return "fileUploadView";
		logger.info("uploadProdFile filePath: "+filePath);
		
		File filePath2 = new File(filePath + File.separator + "product_tmp" +File.separator + OpenStringUtils.getCurrentDay());
        if (!filePath2.exists()) {
        	filePath2.mkdirs();
        } 
        
        
        
		logger.info("uploadProdFile: "+file.getOriginalFilename());
		
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String newFileName = OpenStringUtils.getRandomUUID()+"."+extension;

		logger.info("newFileName: "+newFileName);

		File newFile = new File(filePath2+File.separator+newFileName);
		file.transferTo(newFile);
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		returnMap.put("upFileId", newFileName);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
}
