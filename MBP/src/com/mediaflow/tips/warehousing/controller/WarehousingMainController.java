package com.mediaflow.tips.warehousing.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
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
import com.mediaflow.tips.common.domain.Category;
import com.mediaflow.tips.common.domain.Code;
import com.mediaflow.tips.common.domain.User;
import com.mediaflow.tips.common.service.CodeService;
import com.mediaflow.tips.operate.service.CategoryService;
import com.mediaflow.tips.utils.JsonUtil;

@Controller
@RequestMapping("/warehousing")
public class WarehousingMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(WarehousingMainController.class);
	
	@Autowired MessageSource messageSource;
	@Autowired CodeService codeService;
	@Autowired CategoryService categoryService;

	/**
	 * product 화면
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = {"/product"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	public ModelAndView product(Locale locale, Model model, HttpServletResponse response, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		
		if (session.getAttribute("user_id") == null) {
			modelAndView.setViewName("user/login");
			modelAndView.addObject("msg", "logout");
	        return modelAndView;
		}
		
		List<Code> codeList = new ArrayList<Code>();
		Code code = new Code();
		
		try {
			code.setCodeGroupId(Constants.JOB_TYPE_CD);
			codeList = codeService.getList(code);
		} catch (Exception e) {
			logger.error("코드 조회 오류 : {}", e.getMessage());
		}

		List<Category> cateMasterList = new ArrayList<Category>();
		Category cate = new Category();
		cate.setCateParentId("0");		
		cateMasterList = categoryService.catelist(cate);
		
		Map cateMap = new HashMap();
		for (int ii = 0; ii < cateMasterList.size(); ii++) {
			Category temp = cateMasterList.get(ii);
			Category paramCate = new Category();
			paramCate.setCateParentId(temp.getCateId());
			List<Category> childCates = categoryService.catelist(paramCate);
			cateMap.put(temp.getCateId(), childCates);			
		}
		
		ObjectMapper mapper = new ObjectMapper();// jackson lib for converting to json
        String empMapString = mapper.writeValueAsString(cateMap);// json string
        
		JSONObject jsonCateMap = JsonUtil.getJsonStringFromMap(cateMap);
		
		
		model.addAttribute("userName", session.getAttribute("user_name"));
		model.addAttribute("jobTypeCdList", codeList);
		model.addAttribute("cateMasterList", cateMasterList);
		model.addAttribute("cateMap", empMapString);
		
		modelAndView.setViewName("/warehousing/product");
		return modelAndView;
	}

	/**
	 * warehousing 화면
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = {"/income"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
	@ResponseBody
	public ModelAndView warehousing(Locale locale, Model model, HttpServletResponse response, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
ModelAndView modelAndView = new ModelAndView();
		
		if (session.getAttribute("user_id") == null) {
			modelAndView.setViewName("user/login");
			modelAndView.addObject("msg", "logout");
	        return modelAndView;
		}
		
		List<Code> codeList = new ArrayList<Code>();
		Code code = new Code();
		
		try {
			code.setCodeGroupId(Constants.JOB_TYPE_CD);
			codeList = codeService.getList(code);
		} catch (Exception e) {
			logger.error("코드 조회 오류 : {}", e.getMessage());
		}

		List<Category> cateMasterList = new ArrayList<Category>();
		Category cate = new Category();
		cate.setCateParentId("0");		
		cateMasterList = categoryService.catelist(cate);
		
		Map cateMap = new HashMap();
		for (int ii = 0; ii < cateMasterList.size(); ii++) {
			Category temp = cateMasterList.get(ii);
			Category paramCate = new Category();
			paramCate.setCateParentId(temp.getCateId());
			List<Category> childCates = categoryService.catelist(paramCate);
			cateMap.put(temp.getCateId(), childCates);			
		}
		
		ObjectMapper mapper = new ObjectMapper();// jackson lib for converting to json
        String empMapString = mapper.writeValueAsString(cateMap);// json string
        
		JSONObject jsonCateMap = JsonUtil.getJsonStringFromMap(cateMap);
		
		
		model.addAttribute("userName", session.getAttribute("user_name"));
		model.addAttribute("jobTypeCdList", codeList);
		model.addAttribute("cateMasterList", cateMasterList);
		model.addAttribute("cateMap", empMapString);
		
		modelAndView.setViewName("/warehousing/income");
//		return modelAndView;
//		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("/warehousing/warehousing");
		return modelAndView;
	}
	/**
	 * warehousing 화면
	 */
//	@RequestMapping(value = {"/popExcelProd.do"}, method = RequestMethod.GET, produces = "text/html; charset=utf8")
//	@ResponseBody
//	public ModelAndView popExcelProduct(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("/warehousing/pop_excel_product");
//		return modelAndView;
//	}
}
