package com.bmtc.device.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@author: Jason.ma
 *@date: 2018年3月9日上午10:26:02
 *
 */


@Component
@ConfigurationProperties(prefix="android")
public class AndroidDeviceConfig {
	
	//设备唯一标识
	private String deviceUdid;
	//设备版本
	private String deviceVersion;
	//设备分辨率
	private String deviceResolution;
	//设备型号
	private String deviceModel;
	//设备厂商
	private String deviceBrand;
	//设备状态
	private String deviceStatus;
	//初始化设备
	private String devicesInit;
	
	public String getDeviceUdid() {
		return deviceUdid;
	}
	public void setDeviceUdid(String deviceUdid) {
		this.deviceUdid = deviceUdid;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
	public String getDeviceResolution() {
		return deviceResolution;
	}
	public void setDeviceResolution(String deviceResolution) {
		this.deviceResolution = deviceResolution;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceBrand() {
		return deviceBrand;
	}
	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	
	public String getDevicesInit() {
		return devicesInit;
	}
	public void setDevicesInit(String devicesInit) {
		this.devicesInit = devicesInit;
	}
	
}

