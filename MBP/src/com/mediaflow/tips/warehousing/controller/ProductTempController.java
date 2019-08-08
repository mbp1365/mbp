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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.mediaflow.tips.common.domain.Product;
import com.mediaflow.tips.common.domain.ProductTemp;
import com.mediaflow.tips.utils.OpenStringUtils;
import com.mediaflow.tips.warehousing.service.ProductService;
import com.mediaflow.tips.warehousing.service.ProductTempService;

@Controller
@RequestMapping(value = {"", "/warehousing/producttmp"})
public class ProductTempController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductTempController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired ProductTempService productTempService;
	@Autowired ProductService productService;

	@Value("${ax5uploader.repository.path}")
    private String filePath;
	

	/**
	 * ProudctTemp 조회 화면
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = {"/popExcelProd.do"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView popExcelProduct(@RequestParam HashMap<String, String> map, Locale locale, Model model, HttpServletResponse response, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/warehousing/pop_excel_product");

		//List<ProductTemp> returnList = productTempService.list(productTemp);
		logger.info("## map: "+map);
		//ObjectMapper mapper = new ObjectMapper();// jackson lib for converting to json
        //String prodTmpList = mapper.writeValueAsString(returnList);// json string
		String fileId = map.get("upFileId");
        System.out.println("======================== fileId: "+map.get("upFileId"));
		model.addAttribute("fileId", fileId);
		
		return modelAndView;
	}
	
	/**
	 * 리스트 화면
	 */
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String list(@ModelAttribute ProductTemp productTemp, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("product: " + productTemp.toString());

		List<ProductTemp> returnList = productTempService.list(productTemp);

		returnMap.put("success", "success");
		//returnMap.put("pageListSize", Constants.GRID_PAGE_LIST_SIZE);
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
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST, produces = "text/html; charset=utf8")
	@ResponseBody
	public String saveappr(@ModelAttribute ProductTemp productTemp, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("productTemp: " + productTemp);
		

		List<ProductTemp> prodTempList = productTempService.list(productTemp);
		
		for (int ii = 0; ii < prodTempList.size(); ii++) {
			ProductTemp temp = prodTempList.get(ii);
			Product prod = new Product();
			prod.setBrandId(productTemp.getBrandId());
			prod.setCustId(productTemp.getCustId());
			prod.setRfidTag(temp.getRfidTag());
			prod.setCutCnt(temp.getCutCnt());
			prod.setJobTypeCd(temp.getJobTypeCd());
			prod.setMajorCtgyCd(temp.getMajorCtgyCd());
			prod.setSubCtgyCd(temp.getSubCtgyCd());
			prod.setProdNo(temp.getProdNo());
			prod.setProdNm(temp.getProdNm());
			prod.setRegId((String) session.getAttribute("user_id"));
			productService.add(prod);
		}
		returnMap.put("success", "success");
		
		
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
	public String del(@ModelAttribute ProductTemp productTemp, Model model, HttpServletResponse response, HttpSession session) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록

		logger.info("productTemp: " + productTemp);
		
		productTemp.setRegId((String) session.getAttribute("user_id"));
				
		productTempService.del(productTemp);
		returnMap.put("success", "success");
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
	

	@RequestMapping(value = "/uploadProdFile", method = RequestMethod.POST)
	@ResponseBody
	public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IllegalStateException, IOException, InvalidFormatException {
	    //modelMap.addAttribute("file", file);
//	    return "fileUploadView";
		logger.info("uploadProdFile filePath: "+filePath);
		
		File filePath2 = new File(filePath + File.separator + "product_tmp" +File.separator + OpenStringUtils.getCurrentDay());
        if (!filePath2.exists()) {
        	filePath2.mkdirs();
        } 
        
        
        
		logger.info("uploadProdFile: "+file.getOriginalFilename());
		
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String newFileId = OpenStringUtils.getRandomUUID();
		String newFileName = newFileId+"."+extension;

		logger.info("newFileName: "+newFileName);

		String newFileFullPath = filePath2+File.separator+newFileName; 
		File newFile = new File(newFileFullPath);
		file.transferTo(newFile);
		
		List<ProductTemp> tempList = productTempService.loadXlsx(newFileName, newFileFullPath);
		for (int ii = 0; ii < tempList.size(); ii++) {
			ProductTemp productTemp = tempList.get(ii);
			productTempService.add(productTemp);
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>(); // 커스텀 타입 목록
		returnMap.put("upFileId", newFileName);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(returnMap);
		return jsonStr;
	}
}
