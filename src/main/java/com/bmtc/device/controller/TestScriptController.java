package com.bmtc.device.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmatesoft.svn.core.SVNException;

import com.bmtc.device.config.AppiumConfig;
import com.bmtc.device.domain.Response;
import com.bmtc.svn.service.UpdateLocalCodeBySvnRepoService;
import com.bmtc.task.domain.ProductSvn;

/**
 *@author: Jason.ma
 *@date: 2018年3月13日下午5:04:12
 *
 */

@RequestMapping("/bmtc")
@Controller
public class TestScriptController {
	private static final Logger logger = LoggerFactory.getLogger(TestScriptController.class);
	
	@Autowired
	private AppiumConfig appiumConfig;
	
	@Autowired
	private UpdateLocalCodeBySvnRepoService updateLocalCodeBySvnRepoService;
	
	@RequestMapping(value="/update/script", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> update(@RequestBody ProductSvn productSvn){
		Response<Object> res = new Response<Object>();
		
		long scriptVersion = checkOutScript(productSvn);
		
		res.setCode("0000");
		res.setData(scriptVersion);
		res.setMsg("测试脚本更新成功");
		
		return  res;
	}
	
	private long checkOutScript(ProductSvn productSvn){
		long scriptVersion = 0;
		
		String workSpaceRoot = appiumConfig.getWorkspace();
		String realScriptLocalpath = workSpaceRoot + "/" + productSvn.getProductName() + "/" + productSvn.getSvnBatchName() + "/";
		
		String url = productSvn.getRepository() + "/" + productSvn.getSvnBatchName();
		String userName =  productSvn.getUsername();
		String password =  productSvn.getPassword();
		File file = new File(realScriptLocalpath);
		
		if (!file.exists()) {
			file.mkdirs();
		}
		
		try {
			scriptVersion = updateLocalCodeBySvnRepoService.updateLocalCodeBySvnRepo(url, userName, password, file, null);
			logger.info("svn脚本更新成功，脚本版本为 {},脚本本地路径为{}", scriptVersion, realScriptLocalpath);
		} catch (SVNException e1) {
			logger.error("svn下载脚本异常，请检查svn参数是否正确{} {}", productSvn, e1);
		}
		
		return scriptVersion;
	}
}

