package com.mc.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysDict<M extends BaseSysDict<M>> extends Model<M> implements IBean {

	public M setDictKey(java.lang.String dictKey) {
		set("dictKey", dictKey);
		return (M)this;
	}
	
	public java.lang.String getDictKey() {
		return getStr("dictKey");
	}

	public M setDictValue(java.lang.String dictValue) {
		set("dictValue", dictValue);
		return (M)this;
	}
	
	public java.lang.String getDictValue() {
		return getStr("dictValue");
	}

}
