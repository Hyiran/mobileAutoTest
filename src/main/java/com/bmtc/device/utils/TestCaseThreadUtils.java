package com.bmtc.device.utils;

import com.bmtc.device.domain.ExecuteParam;
import com.bmtc.device.service.TestCaseService;

/**
 *@author: Jason.ma
 *@date: 2018年3月12日下午4:13:27
 *
 */
public class TestCaseThreadUtils extends Thread{

	private TestCaseService testCaseService;
	private ExecuteParam executeParam;
	
	public TestCaseThreadUtils(TestCaseService testCaseService, ExecuteParam executeParam) {
		this.testCaseService = testCaseService;
		this.executeParam = executeParam;
	}
	
	@Override
	public void run() {
		testCaseService.runTestSuite(executeParam);
	}
}

