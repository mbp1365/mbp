package com.mediaflow.tips.common.domain;

import java.io.Serializable;

public class Category implements Serializable {
	

	/**
	 * 
	 * 
			c.cate_id
			, c.cate_parent_id 
			, cp.cate_name cate_parent_name
			, c.cate_name
			, c.remark
			, c.use_yn
			
	 */
	private static final long serialVersionUID = 7155079328182593387L;
	
	/**카테고리 상위 아이디 */
	private String cateParentId;
	private String cateParentName;
	/** 카테고리 아이디 */
	private String cateId;
	/** 카테고리 그룹명 */
	private String cateName;		
	/** 비고 */
	private String remark;
	/** 사용여부 */
	private String useYn;
	public String getCateParentId() {
		return cateParentId;
	}
	public void setCateParentId(String cateParentId) {
		this.cateParentId = cateParentId;
	}
	public String getCateParentName() {
		return cateParentName;
	}
	public void setCateParentName(String cateParentName) {
		this.cateParentName = cateParentName;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	@Override
	public String toString() {
		return "Category [cateParentId=" + cateParentId + ", cateParentName=" + cateParentName + ", cateId=" + cateId
				+ ", cateName=" + cateName + ", remark=" + remark + ", useYn=" + useYn + "]";
	}
	
	

}
