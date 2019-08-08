package com.mediaflow.tips.warehousing.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaflow.tips.common.domain.ProductTemp;
import com.mediaflow.tips.warehousing.mapper.ProductTempMapper;

import au.com.bytecode.opencsv.CSVReader;

@Service("productTempService")
public class ProductTempServiceImpl implements ProductTempService {

	@Autowired
	ProductTempMapper productTempMapper;

	@Override
	public int count(ProductTemp productTemp) {
		return productTempMapper.count(productTemp);
	}

	@Override
	public List<ProductTemp> list(ProductTemp productTemp) {
		return productTempMapper.list(productTemp);
	}

	@Override
	public ProductTemp get(ProductTemp productTemp) {
		return productTempMapper.get(productTemp);
	}

	@Override
	public int add(ProductTemp productTemp) {
		return productTempMapper.add(productTemp);
	}
	
	@Override
	public int del(ProductTemp productTemp) {
		return productTempMapper.del(productTemp);
	}

	private Pattern rxquote = Pattern.compile("\"");

	private String encodeValue(String value) {
		boolean needQuotes = false;
		if (value.indexOf(',') != -1 || value.indexOf('"') != -1 || value.indexOf('\n') != -1
				|| value.indexOf('\r') != -1)
			needQuotes = true;
		Matcher m = rxquote.matcher(value);
		if (m.find())
			needQuotes = true;
		value = m.replaceAll("\"\"");
		if (needQuotes)
			return "\"" + value + "\"";
		else
			return value;
	}

	public List<ProductTemp> loadXlsx(String fileId, String fullFilePath) throws InvalidFormatException, IOException {
		Workbook wb = new XSSFWorkbook(new File(fullFilePath));
		int sheetNo = 0;
		FormulaEvaluator fe = null;
		// if ( index < args.length ) {
		fe = wb.getCreationHelper().createFormulaEvaluator();
		// }
		List<ProductTemp> returnList = new ArrayList<ProductTemp>();
		
		DataFormatter formatter = new DataFormatter();
		// PrintStream out = new PrintStream(new FileOutputStream("e:\\test1.csv"),
		// true, "UTF-8");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos, true, "utf-8");

		byte[] bom = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
		out.write(bom);
		{
			Sheet sheet = wb.getSheetAt(sheetNo);
			for (int r = 0, rn = sheet.getLastRowNum(); r <= rn; r++) {
				Row row = sheet.getRow(r);
				if (row == null) {
					out.println(',');
					continue;
				}
				boolean firstCell = true;
				for (int c = 0, cn = row.getLastCellNum(); c < cn; c++) {
					Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					if (!firstCell)
						out.print(',');
					if (cell != null) {
						if (fe != null)
							cell = fe.evaluateInCell(cell);
						String value = formatter.formatCellValue(cell);
						if (cell.getCellTypeEnum() == CellType.FORMULA) {
							value = "=" + value;
						}
						out.print(encodeValue(value));
					}
					firstCell = false;
				}
				out.println();
			}
		}
		out.close();
		List<String[]> data = new ArrayList<String[]>();
		try {
			// CSVReader reader = new CSVReader(new FileReader("e:\\test.csv"));
			Reader reader2 = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()));
			CSVReader reader = new CSVReader(reader2);
			// UTF-8
			// CSVReader reader = new CSVReader(new InputStreamReader(new
			// FileInputStream(filename), "UTF-8"), ",", '"', 1);
			String[] s;
			while ((s = reader.readNext()) != null) {
				data.add(s);
			}

			System.out.println(data);
			for (int ii = 0; ii < data.size(); ii++) {
				// 0번째 헤더는 데이터에서 제회 
				if (ii == 0) {
					continue;
				}
				System.out.println(Arrays.toString(data.get(ii)));
				ProductTemp productTmp = new ProductTemp();
				productTmp.setUpFileId(fileId);
				String[] prodTmpArr = data.get(ii);
				String tempStr = "";
				for (int jj = 0; jj < data.get(ii).length; jj++) {
					
					//System.out.println(jj + " " + prodTmpArr[jj]);
					tempStr = tempStr + prodTmpArr[jj];
					if (jj == 0) {
						
						productTmp.setRfidTag(prodTmpArr[jj]);
					}else if (jj == 1) {
						String tempCd = prodTmpArr[jj];
						if (tempCd.equalsIgnoreCase("")) {
							break;
						}
						String code = tempCd.substring(tempCd.indexOf("(")+1, tempCd.indexOf(")"));
						productTmp.setMajorCtgyCd(code);						
					}else if (jj == 2) {
						String tempCd = prodTmpArr[jj];
						String code = tempCd.substring(tempCd.indexOf("(")+1, tempCd.indexOf(")"));
						productTmp.setSubCtgyCd(code);						
					}else if (jj == 3) {
						productTmp.setProdNo(prodTmpArr[jj]);						
					}else if (jj == 4) {
						productTmp.setProdNm(prodTmpArr[jj]);						
					}else if (jj == 5) {
						int cutCnt = 0;
						String cutCntStr = prodTmpArr[jj];
						if (cutCntStr != null) {
							if (cutCntStr.equals("")) {
								cutCntStr = "0";
							}
							cutCnt = Integer.parseInt(cutCntStr);
						}
						productTmp.setCutCnt(cutCnt);						
					}else if (jj == 6) {
						String tempCd = prodTmpArr[jj];
						String code = tempCd.substring(tempCd.indexOf("(")+1, tempCd.indexOf(")"));
						productTmp.setJobTypeCd(code);						
					}					
				}
				
				if (tempStr.trim().equals("")) {
					break;
				}else {
					returnList.add(productTmp);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnList;

	}
	
	/**
	 * `rfid_tag` varchar(24) NOT NULL COMMENT 'RFID태그',
  `major_ctgy_cd` char(4) NOT NULL COMMENT '대분류코드',
  `sub_ctgy_cd` char(4) NOT NULL COMMENT '소분류코드',
  `prod_no` varchar(20) NOT NULL COMMENT '제품번호',
  `prod_nm` varchar(20) NOT NULL COMMENT '제품명',
  `cut_cnt` int(11) NOT NULL COMMENT '촬영컷개수',
  `job_type_cd` char(2) NOT NULL COMMENT '작업분류코드',
	 */
}
