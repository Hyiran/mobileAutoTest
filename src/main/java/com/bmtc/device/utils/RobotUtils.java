package com.bmtc.device.utils;

import java.util.LinkedList;
import java.util.List;



import com.bmtc.common.exception.BDException;
import com.bmtc.device.domain.Robot;

/**
 * @author: Jason.ma
 * @date: 2018年1月18日上午11:11:06
 *
 */
public class RobotUtils {

	//rf bin路径
	private String pybot;
	public RobotUtils(String pybot){
		this.pybot = pybot;
	}
	
	public List<String> buildRobotParam(Robot robot) {
		List<String> cmds = new LinkedList<String>();

		String url = robot.getUrl();
		String platformName = robot.getPlatformName();
		String udid = robot.getUdid();
		String version = robot.getVerison();
		int systemPort = robot.getSystemPort();
		int wdaLocalPort = robot.getWdaLocalPort();
		List<String> caseNames = robot.getCaseNames();
		List<String> suiteNames = robot.getSuiteNames();
		List<String> tags = robot.getTagNames();
		String scriptPath = robot.getScriptPath();
		String log = robot.getLog();
		
		String sys = System.getProperty("os.name");
		if (sys.toLowerCase().startsWith("win")) {
			cmds.add("cmd");
			cmds.add("/c");
		}
		cmds.add(this.pybot);
		
		if (log != null && log != "") {
			cmds.add("-d");
			cmds.add(log);
		}

		if (url !=null && url != "") {
			cmds.add("-v");
			cmds.add("url:"+url);
		}

		if (udid !=null && udid !="") {
			cmds.add("-v");
			cmds.add("udid:"+udid);
		}

		if (version!=null && version !="") {
			cmds.add("-v");
			cmds.add("platformVersion:"+version);
		}

		if ("android".equals(platformName.toLowerCase())) {
			if (systemPort != -1) {
				cmds.add("-v");
				cmds.add("systemPort:"+systemPort);
			}
		}
		if ("ios".equals(platformName.toLowerCase())) {
			if (wdaLocalPort != -1) {
				cmds.add("-v");
				cmds.add("wdaLocalPort:"+ wdaLocalPort);
			}
		}
		

		if (caseNames != null) {
			for (String caseName : caseNames) {
				cmds.add("-t");
				cmds.add(caseName);
			}
		}

		if (suiteNames != null) {
			for (String suiteName : suiteNames) {
				cmds.add("-s");
				cmds.add(suiteName);
			}
		}
		
		if (tags != null) {
			for (String tag : tags) {
				cmds.add("-i");
				cmds.add(tag);
			}
		}

		if (scriptPath == "") {
			throw new BDException("testSuite 不能为空");
		}
		
		cmds.add(scriptPath);
		
		return cmds;
	}
	
	public List<String> buildParamForIOS(Robot robot) {
		List<String> cmds = new LinkedList<String>();

		String log = robot.getLog();
		String url = robot.getUrl();
		String udid = robot.getUdid();
		String version = robot.getVerison();
		int wdaLocalPort = robot.getWdaLocalPort();
		List<String> caseNames = robot.getCaseNames();
		List<String> suiteNames = robot.getSuiteNames();
		List<String> tags = robot.getTagNames();
		String scriptPath = robot.getScriptPath();
		
		cmds.add("cmd");
		cmds.add("/c");
		cmds.add(this.pybot);
		
		if (log != null && log != "") {
			cmds.add("-d");
			cmds.add(log);
		}

		if (url !=null && url != "") {
			cmds.add("-v");
			cmds.add("url:"+url);
		}

		if (udid !=null && udid !="") {
			cmds.add("-v");
			cmds.add("udid:"+udid);
		}

		if (version!=null && version !="") {
			cmds.add("-v");
			cmds.add("platformVersion:"+ version);
		}

		if (wdaLocalPort != -1) {
			cmds.add("-v");
			cmds.add("wdaLocalPort:"+ wdaLocalPort);
		}

		if (caseNames != null) {
			for (String caseName : caseNames) {
				cmds.add("-t");
				cmds.add(caseName);
			}
		}

		if (suiteNames != null) {
			for (String suiteName : suiteNames) {
				cmds.add("-s");
				cmds.add(suiteName);
			}
		}
		
		if (tags != null) {
			for (String tag : tags) {
				cmds.add("-i");
				cmds.add(tag);
			}
		}

		if (scriptPath == "") {
			throw new BDException("testSuite 不能为空");
		}
		
		cmds.add(scriptPath);

		return cmds;
	}
}
