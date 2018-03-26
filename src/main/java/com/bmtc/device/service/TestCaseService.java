package com.bmtc.device.service;

import java.util.List;

import com.bmtc.device.domain.ExecuteParam;
import com.bmtc.device.domain.TestCase;
import com.bmtc.device.domain.TestCaseTable;

/**
 *@author: Jason.ma
 *@date: 2018年1月3日下午4:30:36
 *
 */
public interface TestCaseService {
	
	/**
	 * android 环境下bmtc平台运行测试套
	 * @param executeDetail 测试套运行参数
	 * @return 测试套运行结果，正常运行：true，运行异常：false
	 */
	public boolean runTestSuite(ExecuteParam executeParam);
	
	/**
	 * 解析脚本文件夹/文件中所有测试用例名称
	 * @param testSuite 测试脚本文件路径
	 * @return List<TestCase> 文件中所有测试用例名称和对应测试套路径的集合
	 */
	public List<TestCase> getAllTestCase(String testSuite);
	
	/**
	 * 解析脚本文件中所有测试用例名称
	 * @param testSuite 测试脚本文件路径
	 * @return list 该文件中所有测试用例名称
	 */
	public List<TestCaseTable> getTestCaseName(String testSuite);
	
	/**
	 * 解析所有脚本文件/文件夹中所有测试用例名称
	 * @param testSuite 测试脚本文件路径
	 * @return List<TestCase> 所有文件/文件夹中所有测试用例名称和对应测试套路径的集合
	 */
	public List<TestCase> getTestCase(List<String> testSuiteList);
}

