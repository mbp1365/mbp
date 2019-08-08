package com.mediaflow.tips.common.domain;

import java.io.Serializable;
import java.util.Arrays;

public class ProductTemp extends CommBean implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5410635505881116057L;

	//
	// prod_id, brand_id, rfid_tag, major_ctgy_cd, sub_ctgy_cd, prod_no, prod_nm, cut_cnt, job_type_cd, reg_id, reg_dt, upt_id, upt_dt, in_chrg_id, in_date, prod_state_cd, bad_cause, steam_yn, size_desc, job_state_cd
	// prod_state_cd, bad_cause, steam_yn, size_desc, job_state_cd
	private String prodTmpId;   //'ID'
	private String upFileId;    // 업로드된 파일 아이디.
	private String rfidTag; //  'rfid_tag'
	private String majorCtgyCd;   //'카테고리대'
	private String majorCtgyNm;   //'카테고리대 명칭'
	private String subCtgyCd;   //'카테고리소'
	private String subCtgyNm;   //'카테고리소 명칭'
	private String prodNo;  // 'prod_no'
	private String prodNm;  // 'prod_nm'
	private int cutCnt;  //  '촬영컷 수'
	private String jobTypeCd;   // 작업분류코드 
	private String jobTypeNm;   // 작업분류코드명 
	private String regId;   // '등록자ID'
	private String regDt;   // '등록일시'
	
	
	private String brandId;
	private String custId;

	
	
	
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getProdTmpId() {
		return prodTmpId;
	}
	public void setProdTmpId(String prodTmpId) {
		this.prodTmpId = prodTmpId;
	}
	public String getUpFileId() {
		return upFileId;
	}
	public void setUpFileId(String upFileId) {
		this.upFileId = upFileId;
	}
	public String getMajorCtgyNm() {
		return majorCtgyNm;
	}
	public void setMajorCtgyNm(String majorCtgyNm) {
		this.majorCtgyNm = majorCtgyNm;
	}
	public String getSubCtgyNm() {
		return subCtgyNm;
	}
	public void setSubCtgyNm(String subCtgyNm) {
		this.subCtgyNm = subCtgyNm;
	}
	public String getJobTypeNm() {
		return jobTypeNm;
	}
	public void setJobTypeNm(String jobTypeNm) {
		this.jobTypeNm = jobTypeNm;
	}
	
	public String getRfidTag() {
		return rfidTag;
	}
	public void setRfidTag(String rfidTag) {
		this.rfidTag = rfidTag;
	}
	public String getMajorCtgyCd() {
		return majorCtgyCd;
	}
	public void setMajorCtgyCd(String majorCtgyCd) {
		this.majorCtgyCd = majorCtgyCd;
	}
	public String getSubCtgyCd() {
		return subCtgyCd;
	}
	public void setSubCtgyCd(String subCtgyCd) {
		this.subCtgyCd = subCtgyCd;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public int getCutCnt() {
		return cutCnt;
	}
	public void setCutCnt(int cutCnt) {
		this.cutCnt = cutCnt;
	}
	public String getJobTypeCd() {
		return jobTypeCd;
	}
	public void setJobTypeCd(String jobTypeCd) {
		this.jobTypeCd = jobTypeCd;
	}
	
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	@Override
	public String toString() {
		return "ProductTemp [prodTmpId=" + prodTmpId + ", upFileId=" + upFileId + ", rfidTag=" + rfidTag
				+ ", majorCtgyCd=" + majorCtgyCd + ", majorCtgyNm=" + majorCtgyNm + ", subCtgyCd=" + subCtgyCd
				+ ", subCtgyNm=" + subCtgyNm + ", prodNo=" + prodNo + ", prodNm=" + prodNm + ", cutCnt=" + cutCnt
				+ ", jobTypeCd=" + jobTypeCd + ", jobTypeNm=" + jobTypeNm + ", regId=" + regId + ", regDt=" + regDt
				+ ", brandId=" + brandId + ", custId=" + custId + "]";
	}
	

	
	
	
	
}
