package com.mediaflow.tips.common.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 계약서 첨부 파일 정보 Entity.
 * @author redskin
 *
 */
public class ContractFile implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5907097194104913050L;
	/**
	 *
	 *cortfile_id, cort_id, session_id, category_id, child_path, file_name, file_id, file_ext, reg_dt 
  `del_dt` datetime DEFAULT NULL COMMENT '삭제일시',
	 */

	private int cortFileId;   // '고객사ID',
	private String cortId;   // '계약서ID',
	private String sessionId;   // '고객사ID',
	private String category;   // '브랜드ID',
	private String childFolderPath; //  '계약서명'
	private String fileName; //  '원파일명'
	private String id;   //'변경된파일명'
	private String ext;   //'반송일자'
	private String regDt;   // '등록일시'
	private String download;  //다운로드 URL
	public int getCortFileId() {
		return cortFileId;
	}
	public void setCortFileId(int cortFileId) {
		this.cortFileId = cortFileId;
	}
	public String getCortId() {
		return cortId;
	}
	public void setCortId(String cortId) {
		this.cortId = cortId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getChildFolderPath() {
		return childFolderPath;
	}
	public void setChildFolderPath(String childFolderPath) {
		this.childFolderPath = childFolderPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	@Override
	public String toString() {
		return "ContractFile [cortFileId=" + cortFileId + ", cortId=" + cortId + ", sessionId=" + sessionId
				+ ", category=" + category + ", childFolderPath=" + childFolderPath + ", fileName=" + fileName + ", id="
				+ id + ", ext=" + ext + ", regDt=" + regDt + ", download=" + download + "]";
	}
	
	
	

	
}
