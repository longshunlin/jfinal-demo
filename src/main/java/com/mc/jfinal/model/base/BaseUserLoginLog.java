package com.mc.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUserLoginLog<M extends BaseUserLoginLog<M>> extends Model<M> implements IBean {

	public M setLogId(java.lang.Long logId) {
		set("logId", logId);
		return (M)this;
	}
	
	public java.lang.Long getLogId() {
		return getLong("logId");
	}

	public M setUserId(java.lang.Integer userId) {
		set("userId", userId);
		return (M)this;
	}
	
	public java.lang.Integer getUserId() {
		return getInt("userId");
	}

	public M setLoginIP(java.lang.String loginIP) {
		set("loginIP", loginIP);
		return (M)this;
	}
	
	public java.lang.String getLoginIP() {
		return getStr("loginIP");
	}

	public M setLoginTime(java.lang.String loginTime) {
		set("loginTime", loginTime);
		return (M)this;
	}
	
	public java.lang.String getLoginTime() {
		return getStr("loginTime");
	}

}
