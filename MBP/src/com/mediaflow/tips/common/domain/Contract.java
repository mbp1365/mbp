package com.mediaflow.tips.common.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 계약서 관리 정보 Entity.
 * @author redskin
 *
 */
public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4288559959254626071L;
	/**
	 *
	 *`cort_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '계약서ID',
  `brand_id` int(11) NOT NULL COMMENT '브랜드ID',
  `cort_nm` varchar(40) NOT NULL COMMENT '계약서명',
  `ori_file_nm` varchar(60) DEFAULT NULL COMMENT '원파일명',
  `chg_file_nm` varchar(60) DEFAULT NULL COMMENT '변경된파일명',
  `retn_date` datetime DEFAULT NULL COMMENT '반송일자',
  `reg_id` varchar(10) NOT NULL COMMENT '등록자ID',
  `reg_dt` datetime NOT NULL COMMENT '등록일시',
  `upt_id` varchar(10) DEFAULT NULL COMMENT '수정자ID',
  `upt_dt` datetime DEFAULT NULL COMMENT '수정일시',
  `del_id` varchar(10) DEFAULT NULL COMMENT '삭제자ID',
  `del_dt` datetime DEFAULT NULL COMMENT '삭제일시',
	 */

	private String cortId;   // '계약서ID',
	private String custId;   // '고객사ID',
	private String brandId;   // '브랜드ID',
	private String custNm;   // '고객사ID',
	private String brandNm;   // '브랜드ID',
	private String cortNm; //  '계약서명'
	private String oriFileNm; //  '원파일명'
	private String chgFileNm;   //'변경된파일명'
	private String retnDate;   //'반송일자'
	private String cortReq;   // 요구사항.
	private String regId;   // '등록자ID'
	private String regDt;   // '등록일시'
	private String uptId;   // '수정자ID'
	private String uptDt;   // 수정일시
	private String delId;   // '삭제자ID'
	private String delDt;   // 삭제일시
	private String cortFileCnt;
	
	private String updateYn;
	

	private int qCurrPage = 0;
	private int startIndex = 0;
	private int pageListSize = 0;


	public String getCortId() {
		return cortId;
	}
	public void setCortId(String cortId) {
		this.cortId = cortId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
	
	public String getCortReq() {
		return cortReq;
	}
	public void setCortReq(String cortReq) {
		this.cortReq = cortReq;
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
	public String getCortNm() {
		return cortNm;
	}
	public void setCortNm(String cortNm) {
		this.cortNm = cortNm;
	}
	public String getOriFileNm() {
		return oriFileNm;
	}
	public void setOriFileNm(String oriFileNm) {
		this.oriFileNm = oriFileNm;
	}
	public String getChgFileNm() {
		return chgFileNm;
	}
	public void setChgFileNm(String chgFileNm) {
		this.chgFileNm = chgFileNm;
	}
	public String getRetnDate() {
		return retnDate;
	}
	public void setRetnDate(String retnDate) {
		this.retnDate = retnDate;
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
	public String getDelId() {
		return delId;
	}
	public void setDelId(String delId) {
		this.delId = delId;
	}
	public String getDelDt() {
		return delDt;
	}
	public void setDelDt(String delDt) {
		this.delDt = delDt;
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
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getPageListSize() {
		return pageListSize;
	}
	public void setPageListSize(int pageListSize) {
		this.pageListSize = pageListSize;
	}
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	public String getCortFileCnt() {
		return cortFileCnt;
	}
	public void setCortFileCnt(String cortFileCnt) {
		this.cortFileCnt = cortFileCnt;
	}
	@Override
	public String toString() {
		return "Contract [cortId=" + cortId + ", custId=" + custId + ", brandId=" + brandId + ", custNm=" + custNm
				+ ", brandNm=" + brandNm + ", cortNm=" + cortNm + ", oriFileNm=" + oriFileNm + ", chgFileNm="
				+ chgFileNm + ", retnDate=" + retnDate + ", cortReq=" + cortReq + ", regId=" + regId + ", regDt="
				+ regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + ", delId=" + delId + ", delDt=" + delDt
				+ ", updateYn=" + updateYn + ", qCurrPage=" + qCurrPage + ", startIndex=" + startIndex
				+ ", pageListSize=" + pageListSize + "]";
	}
	
	
}
