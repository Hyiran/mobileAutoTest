package com.bmtc.device.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmtc.device.config.AndroidDeviceConfig;
import com.bmtc.device.config.IOSDeviceConfig;
import com.bmtc.device.domain.Device;
import com.bmtc.device.service.DevicesService;
import com.bmtc.device.utils.ExecuteCmdUtils;

@Service
public class DevicesServiceImpl implements DevicesService {
	private static final Logger logger = LoggerFactory.getLogger(DevicesServiceImpl.class);
	
	@Autowired
	private AndroidDeviceConfig androidConfig;
	@Autowired
	private IOSDeviceConfig iosConfig;
	
	@Override
	public List<Device> getAllAndroidInfo() {
		List<Device> deviceInfoList = new ArrayList<Device>();
		List<String> udidList = getAndroidDevicesUDID();

		for (String udid : udidList) {
			String deviceName = getAndroidDeviceName(udid);
			String platformVerison = getAndroidPlatformVersion(udid);
			String resolution = getAndroidResolution(udid);
			String status = getAndroidStatus(udid);
			Device device = new Device();
			
			device.setUdid(udid);
			device.setName(deviceName);
			device.setResolution(resolution);
			device.setVerison(platformVerison);
			device.setStatus(status);
			device.setPlatformName("Android");
			deviceInfoList.add(device);
		}

		return deviceInfoList;
	}
	
	@Override
	public List<Device> getAllIOSInfo() {
		List<Device> deviceInfoList = new ArrayList<Device>();
		List<String> udidList = getIOSDevicesUDID();
		
		for (String udid : udidList) {
			String deviceName = getIOSDeviceName(udid);
			String platformVerison = getIOSPlatformVersion(udid);
			String status = getIOSStatus(udid);
			
			Device device = new Device();
			
			device.setUdid(udid);
			device.setName(deviceName);
			device.setResolution("");
			device.setVerison(platformVerison);
			device.setStatus(status);
			device.setPlatformName("IOS");
			deviceInfoList.add(device);
		}

		return deviceInfoList;
	}
	
	@Override
	public Device getAndroidInfoByUdid(String udid){
		String deviceName = getAndroidDeviceName(udid);
		String platformVerison = getAndroidPlatformVersion(udid);
		String resolution = getAndroidResolution(udid);
		String status = getAndroidStatus(udid);
		Device device = new Device();
		
		device.setUdid(udid);
		device.setName(deviceName);
		device.setResolution(resolution);
		device.setVerison(platformVerison);
		device.setStatus(status);
		device.setPlatformName("Android");
		
		return device;
	}
	
	@Override
	public Device getIOSInfoByUdid(String udid){
		String deviceName = getIOSDeviceName(udid);
		String platformVersion = getIOSPlatformVersion(udid);
		// String resolution = getIOSResolution(udid);
		String status = getIOSStatus(udid);

		Device device = new Device();
		device.setUdid(udid);
		device.setName(deviceName);
		// ios分辨率暂无法获取
		device.setResolution("");
		device.setVerison(platformVersion);
		device.setStatus(status);
		device.setPlatformName("IOS");
		
		return device;
	}
	
	/**
	 * 获取所有设备udid（执行设备）
	 * 
	 * @return 设备udid列表
	 */
	public List<String> getAndroidDevicesUDID() {
		List<String> udidList = new ArrayList<String>();
		String shell = androidConfig.getDeviceUdid();
		StringBuffer udidInfo = ExecuteCmdUtils.executeCmd(shell);
		String[] udidInfoList = udidInfo.toString().split("\r\n");

		for (String str : udidInfoList) {
			if (!str.startsWith("List") && str.endsWith("device")) {
				udidList.add(str.replace("device", "").trim());
			}
		}

		return udidList;
	}

	/**
	 * 获取设备android版本号
	 * 
	 * @param udid
	 * @return
	 */
	public String getAndroidPlatformVersion(String udid) {
		String platformVersion = "";
		String temp = androidConfig.getDeviceVersion();
		String shell = temp.replace("udid", udid);
		StringBuffer platformVersionInfo = ExecuteCmdUtils.executeCmd(shell);
		platformVersion = platformVersionInfo.toString().replace("\r\n", "");
		return platformVersion;
	}

	/**
	 * 获取设备分辨率
	 * 
	 * @param udid
	 * @return
	 */
	public String getAndroidResolution(String udid) {
		String deviceResolution="";
		String temp = androidConfig.getDeviceResolution();
		String shell = temp.replace("udid", udid);
		
		StringBuffer deviceResolutionInfo = ExecuteCmdUtils.executeCmd(shell);
		deviceResolution = deviceResolutionInfo.toString().
				replace("Physical size:", "").replace("\r\n", "").trim();
		return deviceResolution;
	}

	/**
	 * 获取设备名称
	 * 
	 * @param udid
	 * @return
	 */
	public String getAndroidDeviceName(String udid) {
		String deviceName = "";
		//型号
		String modelTemp = androidConfig.getDeviceModel();
		String modelSh = modelTemp.replace("udid", udid);
		
		//品牌
		String brandTemp = androidConfig.getDeviceBrand();
		String brandSh = brandTemp.replace("udid", udid);
		
		String model = ExecuteCmdUtils.executeCmd(modelSh).toString();
		String brand = ExecuteCmdUtils.executeCmd(brandSh).toString();
		deviceName = (brand + " " + model).replace("\r\n", "").trim();
		
		return deviceName;
	}
	
	/**
	 * 获取设备状态
	 * 
	 * @param udid
	 * @return 1:运行中，0： 空闲
	 */
	public String getAndroidStatus(String udid) {
		String status = "0";
		String temp = androidConfig.getDeviceStatus();
		String shell = temp.replace("udid", udid);
		
		StringBuffer androidInfo = ExecuteCmdUtils.executeCmd(shell);

		if (androidInfo !=null && androidInfo.toString().contains("appium")) {
			status = "1";
			return status;
		}
		
		return status;
	}
	/**
	 * IOS获取所有设备udid（执行设备）
	 * @return
	 */
	public List<String> getIOSDevicesUDID(){
		String shell = iosConfig.getDeviceUdid();
		
		List<String> udidList = ExecuteCmdUtils.runShell(shell);
		return udidList;
	}
	
	/**
	 * 获取设备IOS版本号
	 * @param udid
	 * @return
	 */
	public String getIOSPlatformVersion(String udid){
		
		String temp = iosConfig.getDeviceVersion();
		String shell = temp.replace("udid", udid);
		
		StringBuffer verisonInfo = ExecuteCmdUtils.executeCmd(shell);
		String verison = verisonInfo.toString().replace("\r\n", "").trim();
		return verison;
	}
	
	/**
	 * IOS获取设备分辨率,未实现
	 * 
	 * @param udid
	 * @return
	 */
	public String getIOSResolution(String udid) {
		
		return null;
	}

	/**
	 * IOS获取设备名称
	 * 
	 * @param udid
	 * @return
	 */
	public String getIOSDeviceName(String udid) {
		String temp = iosConfig.getDeviceProductType();
		String shell = temp.replace("udid", udid);
		
		StringBuffer deviceNameInfo = ExecuteCmdUtils.executeCmd(shell);
		String deviceName = deviceNameInfo.toString().replace("\r\n", "").trim();
		return deviceName;
	}
	
	/**
	 * IOS获取设备状态
	 * 
	 * @param udid
	 * @return 1:运行中，0： 空闲
	 */
	public String getIOSStatus(String udid) {
		String status = "0";
		String temp = iosConfig.getDeviceStatus();
		String shell = temp.replace("udid", udid);
		
		StringBuffer iosInfo = ExecuteCmdUtils.executeCmd(shell);
		if (iosInfo !=null && iosInfo.toString().contains("iproxy")) {
			status = "1";
			return status;
		}
		
		return status;
	}
	
	@Override
	public void androidInit(String udid){
		String uiautomator2Pk = "io.appium.uiautomator2.server";
		String tempSh = androidConfig.getDevicesInit();
		String uiautomator = tempSh.replace("udid", udid);
		String uiautomator2 = tempSh.replace("udid", udid).replace("io.appium.uiautomator", uiautomator2Pk);
		
		logger.debug("初始化设备状态开始...");
		ExecuteCmdUtils.executeCmd(uiautomator);
		logger.debug("强制退出uiautomator服务{}", uiautomator);
		ExecuteCmdUtils.executeCmd(uiautomator2);
		logger.debug("强制退出uiautomator2服务{}", uiautomator2);
		logger.debug("初始化设备状态完成...");
	}
}
