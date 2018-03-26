package com.bmtc.device.domain;

import java.util.List;

import com.bmtc.script.domain.Script;
import com.bmtc.task.domain.ProductSvn;

/**
 *@author: Jason.ma
 *@date: 2018年3月13日上午9:01:55
 *
 */
public class ExecuteParam {
	//token
	private String token;
	//设备信息
	private Device device;
	//测试脚本信息
	private List<Script> scripts;
	//脚本库信息
	private ProductSvn productSvn;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public List<Script> getScripts() {
		return scripts;
	}
	public void setScripts(List<Script> scripts) {
		this.scripts = scripts;
	}
	public ProductSvn getProductSvn() {
		return productSvn;
	}
	public void setProductSvn(ProductSvn productSvn) {
		this.productSvn = productSvn;
	}
	
	@Override
	public String toString() {
		return "ExecuteParam [token=" + token + ", device=" + device
				+ ", scripts=" + scripts + ", productSvn=" + productSvn + "]";
	}
}

