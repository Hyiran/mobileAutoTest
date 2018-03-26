package com.bmtc.device.service.impl;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bmtc.common.exception.BDException;
import com.bmtc.device.config.AppiumConfig;
import com.bmtc.device.domain.Appium;
import com.bmtc.device.service.AppiumService;
import com.bmtc.device.utils.AppiumThreadUtils;
import com.bmtc.device.utils.AvailablePortFinder;
import com.bmtc.device.utils.ExecuteCmdUtils;

/**
 * @author: Jason.ma
 * @date: 2018年1月2日上午10:19:14
 *
 */
@Service
public class AppiumServiceImpl implements AppiumService {
	private static final Logger logger = LoggerFactory.getLogger(AppiumServiceImpl.class);
	
	@Autowired
	private AppiumConfig appiumConfig;
	private static final long START_TIMEOUT_MILLISECONDS = 30000;
	private static final String STATUS_PATH = "/wd/hub/status";
	
	@Override
	public Appium init(String log){
		int appiumPort = AvailablePortFinder.getNextAvailable();
		int chromeDriverPort = AvailablePortFinder.getNextAvailable();
		int bootstrapPort = AvailablePortFinder.getNextAvailable();
		int systemPort = AvailablePortFinder.getNextAvailable();
		int wdaLocalPort = AvailablePortFinder.getNextAvailable();
		
		String host = appiumConfig.getHost();
		Appium appium = new Appium();
		appium.setHost(host);
		appium.setPort(appiumPort);
		appium.setChromeDriverPort(chromeDriverPort);
		appium.setBootstrapPort(bootstrapPort);
		appium.setSystemPort(systemPort);
		appium.setWdaLocalPort(wdaLocalPort);
		appium.setAppiumLog(log);
		
		return appium;
	}
	
	@Override
	public void startAppium(Appium appiumParam) {
		String host = appiumParam.getHost();
		int port = appiumParam.getPort();
		List<String> cmds = buildAppiumCmds(appiumParam);
		AppiumThreadUtils appiumThreadUtils = new AppiumThreadUtils(cmds);
		appiumThreadUtils.start();
		
		long start = System.currentTimeMillis();
		boolean state = isRunning(host, port);
		while (!state) {
			long end = System.currentTimeMillis();
			if ((end - start) > START_TIMEOUT_MILLISECONDS) {
				stopAppium(port);
				throw new BDException("Appium服务器在 "+ START_TIMEOUT_MILLISECONDS + " seconds"+"内启动失败");
			}
			state = isRunning(host, port);
		}
		logger.debug("Appium服务启动成功");
	
	}

	@Override
	public void stopAppium(int port) {
		String pid = "";
		String appiumPidSh = appiumConfig.getAppiumPid().replace("PORT", Integer.toString(port));
		String sys = System.getProperty("os.name");
		if (sys.toLowerCase().startsWith("windows")) {
			List<String> pidInfos = ExecuteCmdUtils.execute(appiumPidSh);
			for (String pidInfo : pidInfos) {
				if (pidInfo.contains("LISTENING")) {
					pid = pidInfo.split("LISTENING")[1].replace(" ", "").trim();
					break;
				}
			}
		}else {
			List<String> pidInfos = ExecuteCmdUtils.runShell(appiumPidSh);
			if (pidInfos != null && pidInfos.size() > 0) {
				pid = pidInfos.get(0);
			}
		}
		logger.debug("Appium服务pid为{}", pid);
		if (!pid.equals("")) {
			String shell = appiumConfig.getStopAppium().replace("PID", pid);
			ExecuteCmdUtils.execute(shell);
			logger.debug("Appium服务停止成功{}", shell);
		}else{
			logger.debug("Appium停止异常，未找到Appium {} 服务pid", port);
		}
	}
	
	/**
	 * 检查端口是否启动
	 * @param port
	 * @return 启动:true 未启动：false
	 */
	
	private boolean isRunning(String host, int port) {
		try {
			URI uri = new URIBuilder().setScheme("http").setHost(host)
					.setPort(port).setPath(STATUS_PATH).build();
			HttpGet httpget = new HttpGet(uri);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(1000).setConnectionRequestTimeout(1000)
					.setSocketTimeout(1000).build();
			httpget.setConfig(requestConfig);

			CloseableHttpClient client = HttpClients.createDefault();
			HttpResponse response = client.execute(httpget);
			String strResult = EntityUtils.toString(response.getEntity());
			int status = (int) JSONObject.parseObject(strResult).get("status");
			return status == 0;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * appium启动参数准备
	 * @param log
	 * @return appium启动参数
	 */
	private List<String> buildAppiumCmds(Appium appiumParam) {
		
		List<String> cmds = new LinkedList<String>();
		String node  = appiumConfig.getStartNode();
		String appium = appiumConfig.getStartAppium();
		
		cmds.add(node);
		cmds.add(appium);
		cmds.add(String.format("--address=%s", appiumParam.getHost()));
		cmds.add(String.format("--port=%d", appiumParam.getPort()));
		cmds.add(String.format("--bootstrap-port=%d", appiumParam.getBootstrapPort()));
		cmds.add(String.format("--chromedriver-port=%d", appiumParam.getChromeDriverPort()));
		cmds.add(String.format("--webdriveragent-port=%d", appiumParam.getWdaLocalPort()));
		cmds.add(String.format("--log=%s", appiumParam.getAppiumLog()+"/appium.txt"));
		cmds.add("--session-override");
		cmds.add("--log-timestamp");
		cmds.add("--local-timezone");
		
		return cmds;
	}
}
