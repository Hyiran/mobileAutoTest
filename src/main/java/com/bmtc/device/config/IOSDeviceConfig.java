package com.bmtc.device.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@author: Jason.ma
 *@date: 2018年3月9日下午2:07:58
 *
 */
@Component
@ConfigurationProperties(prefix="ios")
public class IOSDeviceConfig {
	//设备唯一标识
    private String deviceUdid;
    //ios设备信息列表
    private String deviceInfo;
    //设备版本
    private String deviceVersion;
    //设备产品类型（ios设备类型唯一）
    private String deviceProductType;
    //设备状态
    private String deviceStatus;
    
	public String getDeviceUdid() {
		return deviceUdid;
	}
	public void setDeviceUdid(String deviceUdid) {
		this.deviceUdid = deviceUdid;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
	public String getDeviceProductType() {
		return deviceProductType;
	}
	public void setDeviceProductType(String deviceProductType) {
		this.deviceProductType = deviceProductType;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	
	@Override
	public String toString() {
		return "IOSDeviceConfig [deviceUdid=" + deviceUdid + ", deviceInfo="
				+ deviceInfo + ", deviceVersion=" + deviceVersion
				+ ", deviceProductType=" + deviceProductType
				+ ", deviceStatus=" + deviceStatus + "]";
	}
}

