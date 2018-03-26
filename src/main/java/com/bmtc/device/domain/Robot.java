package com.bmtc.device.domain;

import java.io.Serializable;
import java.util.List;


/**
 *@author: Jason.ma
 *@date: 2018年1月18日上午11:14:01
 *
 */
public class Robot implements Serializable{
	
	private static final long serialVersionUID = 8849744292040280450L;
	//appium服务uri
	private String url;
	//设备类型
	private String platformName;
	//测试设备udid
	private String udid;
	//测试设备版本
	private String verison;
	//Android:uiautomator-server端口
	private int systemPort;
	//IOS: webdriverAgent 端口 
	private int wdaLocalPort;
	//测试用例名称
	private List<String> caseNames;
	//测试套名称
	private List<String> suiteNames;
	//测试标签名称
	private List<String> tagNames;
	//测试脚本路径
	private String scriptPath;
	//rf log路径
	private String log;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String host, int port) {
		this.url = "http://" + host + ":" + port + "/wd/hub";
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getVerison() {
		return verison;
	}
	public void setVerison(String verison) {
		this.verison = verison;
	}
	public int getSystemPort() {
		return systemPort;
	}
	public void setSystemPort(int systemPort) {
		this.systemPort = systemPort;
	}
	public int getWdaLocalPort() {
		return wdaLocalPort;
	}
	public void setWdaLocalPort(int wdaLocalPort) {
		this.wdaLocalPort = wdaLocalPort;
	}
	public List<String> getCaseNames() {
		return caseNames;
	}
	public void setCaseNames(List<String> caseNames) {
		this.caseNames = caseNames;
	}
	public List<String> getSuiteNames() {
		return suiteNames;
	}
	public void setSuiteNames(List<String> suiteNames) {
		this.suiteNames = suiteNames;
	}
	public List<String> getTagNames() {
		return tagNames;
	}
	public void setTagNames(List<String> tagNames) {
		this.tagNames = tagNames;
	}
	public String getScriptPath() {
		return scriptPath;
	}
	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	@Override
	public String toString() {
		return "Robot [url=" + url + ", platformName=" + platformName
				+ ", udid=" + udid + ", verison=" + verison + ", systemPort="
				+ systemPort + ", wdaLocalPort=" + wdaLocalPort
				+ ", caseNames=" + caseNames + ", suiteNames=" + suiteNames
				+ ", tagNames=" + tagNames + ", scriptPath=" + scriptPath
				+ ", log=" + log + "]";
	}
}

