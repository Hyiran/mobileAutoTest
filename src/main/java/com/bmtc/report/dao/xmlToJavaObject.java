package com.bmtc.report.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.bmtc.report.domain.Test;



public class xmlToJavaObject {
	
	private static final Logger logger = LoggerFactory
			.getLogger(xmlToJavaObject.class);
	
	/**
	 * 将XML文件转换为String
	 * @param inputFilePath
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String xmlToJsonString(String inputFilePath) throws IOException, JSONException {
		logger.info("xmlToJavaObject.xmlToJsonString() start");
		//读取xml文件
		File file = new File(inputFilePath);
		//读入文件流
		InputStream in = null;
		try {

			in = new FileInputStream(file);
		} catch (Exception e) {
		}
		//将文件流转换为字符串类型
		String xml = IOUtils.toString(in);
		//将xml转换为json 类型
		Object xmlJSONObj = XML.toJSONObject(xml);
		logger.info("xmlToJavaObject.xmlToJsonString() end");
		return xmlJSONObj.toString();
	}

	
					
	
	/**
	 * 将JsonString转换为javaBean
	 * @param inputFilePath
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static List<Test> stringToJava(String inputFilePath) throws IOException, JSONException {
		logger.info("xmlToJavaObject.stringToJava() start");
		String string = xmlToJsonString(inputFilePath);
		JSONObject parseObject = new JSONObject(string);
		JSONObject robot = parseObject.getJSONObject("robot");
		JSONObject suite = robot.getJSONObject("suite");
		Object testobj = null;
		if (suite.has("suite")) {
			while (suite.has("suite")) {
				suite =suite.getJSONObject("suite");
				if (!suite.has("suite")) {
					testobj =suite.getJSONObject("test");
				}
			}
		}else{
			testobj =suite.getJSONObject("test");
		}
		
		List<Test> tests = new ArrayList<Test>();
		if (testobj instanceof JSONArray) {
			JSONArray testArray = (JSONArray) testobj;
			for (int i = 0; i < testArray.length(); i++) {
				Test test = JSON.parseObject(testArray.get(i).toString(), Test.class);
				tests.add(test);
			}
		} else if (testobj instanceof JSONObject) {
			Test test = JSON.parseObject(testobj.toString(), Test.class);
			tests.add(test);
		}
		logger.info("xmlToJavaObject.stringToJava() end");
		return tests;
	}

}
