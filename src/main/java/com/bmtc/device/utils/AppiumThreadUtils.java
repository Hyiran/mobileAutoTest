package com.bmtc.device.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *@author: Jason.ma
 *@date: 2018年3月12日下午4:27:59
 *
 */
public class AppiumThreadUtils extends Thread{
	private static final Logger logger = LoggerFactory.getLogger(AppiumThreadUtils.class);
	private List<String> cmds;
	
	public AppiumThreadUtils(List<String> cmds) {
		this.cmds = cmds;
	}
	
	@Override
	public void run() {
		try {
			logger.debug("启动Appium服务 {}", this.cmds.toString());
			ProcessBuilder pb = new ProcessBuilder(cmds).redirectErrorStream(true);
			Process process = pb.start();
			StreamWatch inputStream = new StreamWatch(process.getInputStream());
			inputStream.setName("Appium");
			inputStream.start();
			int exitvalue = process.waitFor();
			logger.debug("退出Appium服务， {}", exitvalue);
		} catch (Exception e) {
			logger.warn("启动Appium服务异常  {}", e);
		}
	}

}

