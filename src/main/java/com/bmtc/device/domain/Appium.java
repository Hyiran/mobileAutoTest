package com.bmtc.device.domain;

import java.io.Serializable;

/**
 * @author: Jason.ma
 * @date: 2018年1月3日上午11:40:51
 *
 */
public class Appium implements Serializable{
	private static final long serialVersionUID = 4828208058302969506L;
	
	//设备udid
	private String udid;
	//appium服务host
	private String host;
	//服务端口
	private int port;
	//chromedriver端口
	private int chromeDriverPort;
	//设备服务端口
	private int bootstrapPort;
	//uiautomator2 server端口
	private int systemPort;
	//webdriver agent 端口
	private int wdaLocalPort;
	//appium log 路径
	private String appiumLog;
	
	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public int getSystemPort() {
		return systemPort;
	}

	public void setSystemPort(int systemPort) {
		this.systemPort = systemPort;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getChromeDriverPort() {
		return chromeDriverPort;
	}

	public void setChromeDriverPort(int chromeDriverPort) {
		this.chromeDriverPort = chromeDriverPort;
	}

	public int getBootstrapPort() {
		return bootstrapPort;
	}

	public void setBootstrapPort(int bootstrapPort) {
		this.bootstrapPort = bootstrapPort;
	}

	public String getAppiumLog() {
		return appiumLog;
	}

	public void setAppiumLog(String appiumLog) {
		this.appiumLog = appiumLog;
	}

	
	public int getWdaLocalPort() {
		return wdaLocalPort;
	}

	public void setWdaLocalPort(int wdaLocalPort) {
		this.wdaLocalPort = wdaLocalPort;
	}

	@Override
	public String toString() {
		return "Appium [udid=" + udid + ", host=" + host + ", port=" + port
				+ ", chromeDriverPort=" + chromeDriverPort + ", bootstrapPort="
				+ bootstrapPort + ", systemPort=" + systemPort
				+ ", wdaLocalPort=" + wdaLocalPort + ", appiumLog=" + appiumLog
				+ "]";
	}

}
