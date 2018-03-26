package com.bmtc.device.service;

import com.bmtc.device.domain.Appium;

public interface AppiumService {
	
	public Appium init(String log);
	/**
	 * appium服务启动
	 * @param logDir appium服务，日志根路径
	 * @return appium启动参数
	 * 
	 */
	public void startAppium(Appium appiumParam);
	/**
	 * appium服务关闭
	 * @param appiumPort appium启动端口
	 */
	public void stopAppium(int appiumPort); 
}
