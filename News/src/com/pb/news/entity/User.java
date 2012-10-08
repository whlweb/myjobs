package com.pb.news.entity;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.pb.news.constants.Constants;

public class User implements HttpSessionBindingListener{
	private int id;
	private String userName;
	private String password;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private int userType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public void valueBound(HttpSessionBindingEvent arg0) {
		//存入session时自动调用
		Constants.ONLINE_USER_COUNT ++;
    }
    public void valueUnbound(HttpSessionBindingEvent arg0) {
    	//从session中删除时自动调用
    	Constants.ONLINE_USER_COUNT --;
    }

	

}
