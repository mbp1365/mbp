package com.mediaflow.tips.common.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 고객사 관리 정보 Entity.
 * @author redskin
 *
 */
public class Rfid implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8249982683808681369L;
	
	private int rfidSeqId;   //'고객ID'
	private String rfidId; //  '고객명'
	private String regId;   // '등록자ID'
	private String regDt;   // '등록일시'
	private String uptId;   // '수정자ID'
	private String uptDt;   // 수정일시
	private String delId;   // '삭제자ID'
	private String delDt;   // 삭제일시

	private String updateYn;
	

	private int qCurrPage = 0;
	private int startIndex = 0;
	private int pageListSize = 0;
	public int getRfidSeqId() {
		return rfidSeqId;
	}
	public void setRfidSeqId(int rfidSeqId) {
		this.rfidSeqId = rfidSeqId;
	}
	public String getRfidId() {
		return rfidId;
	}
	public void setRfidId(String rfidId) {
		this.rfidId = rfidId;
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
	@Override
	public String toString() {
		return "Rfid [rfidSeqId=" + rfidSeqId + ", rfidId=" + rfidId + ", regId=" + regId + ", regDt=" + regDt
				+ ", uptId=" + uptId + ", uptDt=" + uptDt + ", delId=" + delId + ", delDt=" + delDt + ", updateYn="
				+ updateYn + "]";
	}
  
	
	
	
}
