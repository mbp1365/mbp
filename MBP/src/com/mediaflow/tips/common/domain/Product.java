package com.mediaflow.tips.common.domain;

import java.io.Serializable;
import java.util.Arrays;

public class Product extends CommBean implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7092462684565430081L;

	//
	// prod_id, brand_id, rfid_tag, major_ctgy_cd, sub_ctgy_cd, prod_no, prod_nm, cut_cnt, job_type_cd, reg_id, reg_dt, upt_id, upt_dt, in_chrg_id, in_date, prod_state_cd, bad_cause, steam_yn, size_desc, job_state_cd
	// prod_state_cd, bad_cause, steam_yn, size_desc, job_state_cd
	private String prodId;   //'ID'
	private String custId;   //'고객ID'
	private String custNm;   //'고객사명'
	private String brandId;   //'브랜드ID'
	private String brandNm;   //'브랜드명'
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
	private String inChrgId;   //'입고담당자ID'
	private String inChrgNm;
	private String inDate;   //'입고일시'
	private String prodStateCd;   //'제품상태코드'
	private String prodStateNm;   //'제품상태명'
	

	private String badCause;   //'불량사유'
	private String steamYn;   //'스팀여부'
	private String sizeDesc;   //'실측사이즈측정'
	private String jobStateCd;   //'작업상태코드'
	private String regId;   // '등록자ID'
	private String regDt;   // '등록일시'
	private String uptId;   // '수정자ID'
	private String uptDt;   // 수정일시
	
	private String updateYn;
	

	private int qCurrPage = 0;
	private int qStartIndex = 0;

	private int qPageListSize = 0;
	
	public int getqPageListSize() {
		return qPageListSize;
	}
	public void setqPageListSize(int qPageListSize) {
		this.qPageListSize = qPageListSize;
	}
	public int getqStartIndex() {
		return qStartIndex;
	}
	public void setqStartIndex(int qStartIndex) {
		this.qStartIndex = qStartIndex;
	}
	public String getProdStateNm() {
		return prodStateNm;
	}
	public void setProdStateNm(String prodStateNm) {
		this.prodStateNm = prodStateNm;
	}
	public String getInChrgNm() {
		return inChrgNm;
	}
	public void setInChrgNm(String inChrgNm) {
		this.inChrgNm = inChrgNm;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getBrandNm() {
		return brandNm;
	}
	public void setBrandNm(String brandNm) {
		this.brandNm = brandNm;
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
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
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
	public String getInChrgId() {
		return inChrgId;
	}
	public void setInChrgId(String inChrgId) {
		this.inChrgId = inChrgId;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getProdStateCd() {
		return prodStateCd;
	}
	public void setProdStateCd(String prodStateCd) {
		this.prodStateCd = prodStateCd;
	}
	public String getBadCause() {
		return badCause;
	}
	public void setBadCause(String badCause) {
		this.badCause = badCause;
	}
	public String getSteamYn() {
		return steamYn;
	}
	public void setSteamYn(String steamYn) {
		this.steamYn = steamYn;
	}
	public String getSizeDesc() {
		return sizeDesc;
	}
	public void setSizeDesc(String sizeDesc) {
		this.sizeDesc = sizeDesc;
	}
	public String getJobStateCd() {
		return jobStateCd;
	}
	public void setJobStateCd(String jobStateCd) {
		this.jobStateCd = jobStateCd;
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
	public String getUptId() {
		return uptId;
	}
	public void setUptId(String uptId) {
		this.uptId = uptId;
	}
	public String getUptDt() {
		return uptDt;
	}
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}
	public String getUpdateYn() {
		return updateYn;
	}
	public void setUpdateYn(String updateYn) {
		this.updateYn = updateYn;
	}
	public int getqCurrPage() {
		return qCurrPage;
	}
	public void setqCurrPage(int qCurrPage) {
		this.qCurrPage = qCurrPage;
	}
	
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", brandId=" + brandId + ", rfidTag=" + rfidTag + ", majorCtgyCd="
				+ majorCtgyCd + ", subCtgyCd=" + subCtgyCd + ", prodNo=" + prodNo + ", prodNm=" + prodNm + ", cutCnt="
				+ cutCnt + ", jobTypeCd=" + jobTypeCd + ", inChrgId=" + inChrgId + ", inDate=" + inDate
				+ ", prodStateCd=" + prodStateCd + ", badCause=" + badCause + ", steamYn=" + steamYn + ", sizeDesc="
				+ sizeDesc + ", jobStateCd=" + jobStateCd + ", regId=" + regId + ", regDt=" + regDt + ", uptId=" + uptId
				+ ", uptDt=" + uptDt + ", updateYn=" + updateYn + ", qCurrPage=" + qCurrPage + "]";
	}
	
	
	
}
