package com.bmtc.device.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@author: Jason.ma
 *@date: 2018年3月9日上午10:56:22
 *
 */

@Component
@ConfigurationProperties(prefix="appium")
public class AppiumConfig {
	//本机IP地址
	private String host;
	//测试脚本本地地址
	private String workspace;
	//appium启动命令
	private String startAppium;
	//appium停止命令
	private String stopAppium;
	//appium进程
	private String appiumPid;
	//node启动命令
	private String startNode;
	//自动化测试log路径
	private String autotestLog;
	//robot framework 启动命令
	private String startRF;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	public String getStartAppium() {
		return startAppium;
	}
	public void setStartAppium(String startAppium) {
		this.startAppium = startAppium;
	}
	public String getStopAppium() {
		return stopAppium;
	}
	public void setStopAppium(String stopAppium) {
		this.stopAppium = stopAppium;
	}
	public String getAppiumPid() {
		return appiumPid;
	}
	public void setAppiumPid(String appiumPid) {
		this.appiumPid = appiumPid;
	}
	public String getStartNode() {
		return startNode;
	}
	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}
	public String getAutotestLog() {
		return autotestLog;
	}
	public void setAutotestLog(String autotestLog) {
		this.autotestLog = autotestLog;
	}
	public String getStartRF() {
		return startRF;
	}
	public void setStartRF(String startRF) {
		this.startRF = startRF;
	}
	
	@Override
	public String toString() {
		return "AppiumConfig [host=" + host + ", workspace=" + workspace
				+ ", startAppium=" + startAppium + ", stopAppium=" + stopAppium
				+ ", appiumPid=" + appiumPid + ", startNode=" + startNode
				+ ", autotestLog=" + autotestLog + ", startRF=" + startRF + "]";
	}
	
}

