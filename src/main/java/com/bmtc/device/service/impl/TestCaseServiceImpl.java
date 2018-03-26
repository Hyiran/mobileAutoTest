package com.bmtc.device.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmtc.common.exception.BDException;
import com.bmtc.common.utils.FileUtil;
import com.bmtc.device.config.AppiumConfig;
import com.bmtc.device.domain.Appium;
import com.bmtc.device.domain.Device;
import com.bmtc.device.domain.ExecuteParam;
import com.bmtc.device.domain.Robot;
import com.bmtc.device.domain.TestCase;
import com.bmtc.device.domain.TestCaseTable;
import com.bmtc.device.service.AppiumService;
import com.bmtc.device.service.DevicesService;
import com.bmtc.device.service.ExecuteDetailService;
import com.bmtc.device.service.TestCaseService;
import com.bmtc.device.utils.RobotUtils;
import com.bmtc.device.utils.StreamWatch;
import com.bmtc.script.domain.Script;
import com.bmtc.svn.service.UpdateLocalCodeBySvnRepoService;
import com.bmtc.task.domain.ExecuteDetail;
import com.bmtc.task.domain.ProductSvn;

/**
 * @author: Jason.ma
 * @date: 2018年1月3日下午4:59:44
 *
 */
@Service
public class TestCaseServiceImpl implements TestCaseService {
	private static final Logger logger = LoggerFactory.getLogger(TestCaseServiceImpl.class);
	
	@Autowired
	private AppiumConfig appiumConfig;
	@Autowired
	private AppiumService appiumService;
	@Autowired
	private DevicesService devicesService;
	@Autowired
	private ExecuteDetailService executeDetailService;
	@Autowired
	private UpdateLocalCodeBySvnRepoService updateLocalCodeBySvnRepoService;

	@Override
	public boolean runTestSuite(ExecuteParam executeParam) {
		boolean flag = false;

		//1.测试脚本本地路径准备
		String token = executeParam.getToken();
		Device device = executeParam.getDevice();
		List<Script> scripts = executeParam.getScripts();
		ProductSvn productSvn = executeParam.getProductSvn();
		
		String workSpaceRoot = appiumConfig.getWorkspace();
		String scriptPath = workSpaceRoot + "/" + productSvn.getProductName() + "/" + productSvn.getSvnBatchName() + "/";
		
		List<String> suiteNames = new ArrayList<String>();
		
		for (Script script : scripts) {
			if (script.getScriptName() != "") {
				suiteNames.add(script.getScriptName());
			}
		}
		
		//2.创建rf、appium log文件
		String log = createLog(productSvn.getProductName(), device.getUdid());
		String rfLog = log + "/log.html";
		String appiumLog = log + "/appium.txt";
		String testReport = log + "/report.html";
		
		ExecuteDetail executeDetail = new ExecuteDetail();
		executeDetail.setToken(token);
		executeDetail.setDeviceType("Android");
		executeDetail.setTestReportPath(testReport);
		executeDetail.setRfLogPath(rfLog);
		executeDetail.setAppiumLogPath(appiumLog);
		executeDetail.setStatus("1");
		executeDetailService.save(executeDetail);
		
		//3.启动appium服务
		Appium appium = appiumService.init(log);
		appiumService.startAppium(appium);
		
		String host = appium.getHost();
		int port = appium.getPort();
		int systemPort = appium.getSystemPort();
		int wdaLocalPort = appium.getWdaLocalPort();
		
		//4.rf启动初始化
		String platformName = device.getPlatformName();
		String udid = device.getUdid();
		String version = device.getVerison();
		
		Robot robot = new Robot();
		robot.setLog(log);
		robot.setUrl(host, port);
		robot.setUdid(udid);
		robot.setVerison(version);
		
		if ("android".equals(platformName.toLowerCase())) {
			robot.setSystemPort(systemPort);
		}
		if ("ios".equals(platformName.toLowerCase())) {
			robot.setWdaLocalPort(wdaLocalPort);
		}
		robot.setPlatformName(platformName);
		robot.setSuiteNames(suiteNames);
		robot.setScriptPath(scriptPath);
		
		//5.启动robot framework执行脚本
		RobotUtils robotUtils = new RobotUtils(appiumConfig.getStartRF());
		List<String> rfShell = robotUtils.buildRobotParam(robot);
		
		logger.debug("开始执行测试用例 {}", rfShell);
		
		try {
			flag = executeScript(rfShell);
		} catch (IOException | InterruptedException e) {
			logger.warn("运行测试用例异常  {}", e);
		}finally {
			//6.测试结束更新状态，设备初始化
			executeDetail.setStatus("0");
			executeDetailService.update(executeDetail);
			initDeviceEnv(port, udid, platformName);
			//7.回调bmtc平台通知测试结果
		}
		
		return flag;
	}
	
	@Override
	public List<TestCase> getTestCase(List<String> pathList) {
		List<TestCase> testSuiteList = new ArrayList<TestCase>();
		for (String path : pathList) {
			File file = new File(path);
			if (!file.isDirectory() && !file.isFile()) {
				logger.warn("找不到文件夹或文件 {}", path);
				throw new BDException("找不到文件夹或文件" + path);
			}

			if (file.isDirectory()) {
				FileUtil fileNameFileter = new FileUtil();
				List<String> testSuitePathList = fileNameFileter.listPath(file);
				for (String testSuitePath : testSuitePathList) {
					TestCase testCase = new TestCase();
					List<TestCaseTable> testCaseList = parseTestSuite(testSuitePath);
					if (!testCaseList.isEmpty()) {
						testCase.setCaseName(testCaseList);
						testCase.setTestSuite(testSuitePath);
						testSuiteList.add(testCase);
					}
				}

			} else {
				TestCase testCase = new TestCase();
				List<TestCaseTable> testCaseList = parseTestSuite(path);
				if (!testCaseList.isEmpty()) {
					testCase.setCaseName(testCaseList);
					testCase.setTestSuite(path);
					testSuiteList.add(testCase);
				}
			}
		}

		return testSuiteList;
	}

	@Override
	public List<TestCaseTable> getTestCaseName(String testSuite) {
		List<TestCaseTable> caseNames = new ArrayList<TestCaseTable>();
		if (testSuite.endsWith(".txt")) {
			caseNames = parseTestSuite(testSuite);
			return caseNames;
		}
		return caseNames;
	}
	
	@Override
	public List<TestCase> getAllTestCase(String testSuite) {
		List<TestCase> testSuiteList = new ArrayList<TestCase>();
		File file = new File(testSuite);
		if (!file.isDirectory() && !file.isFile()) {
			logger.warn("找不到文件夹或文件 {}", testSuite);
			throw new BDException("找不到文件夹或文件" + testSuite);
		}
		FileUtil fileNameFileter = new FileUtil();
		List<String> testSuitePathList = fileNameFileter.listPath(file);

		for (String testSuitePath : testSuitePathList) {
			TestCase testCase = new TestCase();
			List<TestCaseTable> testCaseList = parseTestSuite(testSuitePath);
			if (0 != testCaseList.size()) {
				testCase.setCaseName(testCaseList);
				testCase.setTestSuite(testSuitePath);
				testSuiteList.add(testCase);
			}
		}
		return testSuiteList;
	}
	
	/**
	 * 解析rf测试套中 测试用例名称
	 * @param suite
	 * @return List<TestCaseTable> [casename]
	 */
	
	private List<TestCaseTable> parseTestSuite(String suite) {
		List<TestCaseTable> caseInfo = new ArrayList<TestCaseTable>();

		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = null;
			FileInputStream fis = null;
			InputStreamReader isr = null;

			fis = new FileInputStream(suite);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().equals("...")) {
					sb.append(line).append("\r\n");
				}
			}
			br.close();
			fis.close();
			isr.close();
		} catch (IOException e) {
			throw new BDException("读取测试套文件错误" + e);
		}

		if (sb.toString().contains("*** Test Cases ***")) {
			//截取testcase部分的string
			String testcaseDetail = sb.toString().split("[*][*][*] Test Cases [*][*][*]")[1];
			String[] detail = testcaseDetail.split("\r\n");
			//遍历获取caseName及其对应的caseNum
			for (int scriptNum = 0; scriptNum < detail.length; scriptNum++) {
				if (detail[scriptNum].contains("*** Keywords ***")) {
					break;
				}
				//非空开头都是caseName
				if (!detail[scriptNum].startsWith(" ") && !detail[scriptNum].equals("")) {
					TestCaseTable testCaseTable = new TestCaseTable();
					testCaseTable.setCaseName(detail[scriptNum]);
					int docNum = 1;
					if ((scriptNum + docNum) <= detail.length) {
						//caseName下一行若包含[Documentation]，从改行开始即为caseNum
						if (detail[scriptNum + docNum].contains("[Documentation]")) {
							List<String> testCaseNums = new ArrayList<String>();
							//一个caseName可能关联多个caseNum，循环遍历下一行是否为caseNum，以连续的“...”判别是否为caseNum
							String firstLineDoc = detail[scriptNum + docNum].replace("[Documentation]", "").replace("\\", "").trim();
							if (!firstLineDoc.equals("")) {
								testCaseNums.add(firstLineDoc);
							}
							docNum++;
							if ((scriptNum + docNum) <= detail.length) {
								if (detail[scriptNum + docNum].contains("...")) {
									testCaseNums.add(detail[scriptNum + docNum].replace("...", "").trim());
									docNum++;
									while (detail[scriptNum + docNum].contains("...")) {
										testCaseNums.add(detail[scriptNum + docNum].replace("...", "").trim());
										docNum++;
										if ((scriptNum + docNum) > (detail.length - 1)) {
											break;
										}
									}
								} 
							} 
							testCaseTable.setTestCaseNum(testCaseNums);
							scriptNum += docNum;
						}
					} 
					caseInfo.add(testCaseTable);
				}
			}
		}
		return caseInfo;
	}

	/**
	 * 创建log根路径
	 * @param udid
	 * @param product
	 * @return 
	 */
	private String createLog(String product, String udid) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTimeStamp = sdf.format(d);
		String rootPath = appiumConfig.getAutotestLog();
		String log = rootPath + "/" + product + "/" + currentTimeStamp + "-" + udid;
		File file = new File(log);

		if (!file.exists()) {
			file.mkdirs();
		}

		return log;
	}
	
	private boolean executeScript(List<String> shell) throws IOException, InterruptedException{
		boolean result = false;
		ProcessBuilder pb = new ProcessBuilder(shell) .redirectErrorStream(true);
		Process process = pb.start();
		StreamWatch inputStream = new StreamWatch(process.getInputStream());
		inputStream.setName("RFlog");
		inputStream.start();
		int status = process.waitFor();
		if (status == 0) {
			result = true;
		} else {
			result = false;
		}

		logger.info("测试用例执行完成，测试结果为 {}", result);
		process.destroy();
		return result;
	}
	
	private void initDeviceEnv(int appiumPort, String deviceUid, String devicePlatform){
		appiumService.stopAppium(appiumPort);
		if ("android".equals(devicePlatform.toLowerCase())) {
			devicesService.androidInit(deviceUid);
		}
	}
}
