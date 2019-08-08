package com.mediaflow.tips.operate.controller.model;

import java.io.Serializable;
import java.util.List;

import com.mediaflow.tips.common.domain.User;

public class UserList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7495301671519353427L;
	List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
