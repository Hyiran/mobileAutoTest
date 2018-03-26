package com.bmtc.system.domain;

import java.io.Serializable;

/**
 * 执行机实体类
 */
public class Actuator implements Serializable{

	private static final long serialVersionUID = 1L;
	// 主键id
	private Long id;
	// 执行机ip
	private String ip;
	// 执行机获取执行设备接口
	private String deviceUri;
	// 执行机执行接口
	private String activeUri;
	// 执行机下载脚本接口
	private String downLoadUri;
	/**
	 * 构造
	 */
	public Actuator() {
		super();
	}
	
	public Actuator(String ip) {
		super();
		this.ip = ip;
	}

	/**
	 * set&get
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDeviceUri() {
		return deviceUri;
	}
	public void setDeviceUri(String deviceUri) {
		this.deviceUri = deviceUri;
	}
	public String getActiveUri() {
		return activeUri;
	}
	public void setActiveUri(String activeUri) {
		this.activeUri = activeUri;
	}
	public String getDownLoadUri() {
		return downLoadUri;
	}

	public void setDownLoadUri(String downLoadUri) {
		this.downLoadUri = downLoadUri;
	}
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Actuator [id=" + id + ", ip=" + ip + ", deviceUri=" + deviceUri
				+ ", activeUri=" + activeUri + ", downLoadUri=" + downLoadUri
				+ "]";
	}

}
