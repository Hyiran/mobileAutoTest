package com.bmtc.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class GetSystemProperties {
	public static InetAddress getInetAddress() {
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 获取本机IP地址
	 * @param netAddress
	 * @return
	 */
	public static String getHostIp(InetAddress netAddress) {
		if(null == netAddress) {
			return null;
		} 
		return netAddress.getHostAddress().toString();
	}
	
	/**
	 * 获取本机计算机名称
	 * @param netAddress
	 * @return
	 */
	public static String getHostName(InetAddress netAddress) {
		if(null == netAddress) {
			return null;
		} 
		return netAddress.getHostName().toString();
	}
	
	/**
	 * 获取java虚拟机和系统信息
	 * @param 
	 * @return
	 */
	public static Properties getProperties() {
		return System.getProperties();
	}
}
