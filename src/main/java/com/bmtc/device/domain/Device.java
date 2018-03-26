package com.bmtc.device.domain;

import java.io.Serializable;

public class Device implements Serializable{
	private static final long serialVersionUID = -1L;
	private String udid;
	private String verison;
	private String name;
	private String resolution;
	private String status;
	private String platformName;
	private String ip;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "Device [udid=" + udid + ", verison=" + verison + ", name="
				+ name + ", resolution=" + resolution + ", status=" + status
				+ ", platformName=" + platformName + ", ip=" + ip + "]";
	}
}
