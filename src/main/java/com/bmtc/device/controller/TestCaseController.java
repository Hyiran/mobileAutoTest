package com.bmtc.device.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bmtc.device.domain.Device;
import com.bmtc.device.domain.ExecuteParam;
import com.bmtc.device.domain.Response;
import com.bmtc.device.service.ExecuteDetailService;
import com.bmtc.device.service.TestCaseService;
import com.bmtc.device.utils.TestCaseThreadUtils;
import com.bmtc.script.domain.Script;
import com.bmtc.task.domain.ProductSvn;

/**
 *@author: Jason.ma
 *@date: 2018年1月9日下午5:18:44
 *
 */
@RequestMapping("/bmtc")
@Controller
public class TestCaseController {
	private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);
	
	@Autowired
	private TestCaseService testCaseService;
	@Autowired
	private ExecuteDetailService executeDetailService;
	
	@RequestMapping(value="/run/case", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> runTestCase(@RequestBody ExecuteParam executeParam){
		
		logger.debug("执行测试用例参数为{}",executeParam);
		
		Response<Object> res = new Response<Object>();
		Device device = executeParam.getDevice();
		List<Script> scripts = executeParam.getScripts();
		ProductSvn productSvn = executeParam.getProductSvn();
		
		String token = executeParam.getToken();
		String udid = device.getUdid();
		String version = device.getVerison();
		String repos = productSvn.getRepository();
		String svnBatchName = productSvn.getSvnBatchName();
		
		if (scripts == null) {
			res.setCode("9999");
			res.setData("脚本信息不能为空");
			res.setMsg("参数错误");
			logger.debug("返回BMTC 请求参数格式错误 ：{}", res);
			return res;
		}
		
		if (token == "" || token == null) {
			res.setCode("9999");
			res.setData("token不能为空");
			res.setMsg("参数错误");
			logger.debug("返回BMTC 请求参数格式错误 ：{}", res);
			return res;
		}
		
		if (udid == "" || udid == null) {
			res.setCode("9999");
			res.setData("udid不能为空");
			res.setMsg("参数错误");
			logger.debug("返回BMTC 请求参数格式错误 ：{}", res);
			return res;
		}
		
		if (version == "" || version == null) {
			res.setCode("9999");
			res.setData("version不能为空");
			res.setMsg("参数错误");	
			logger.debug("返回BMTC 请求参数格式错误 ：{}", res);
			return res;
		}
		
		if (repos == "" || repos == null) {
			res.setCode("9999");
			res.setData("svn仓库地址不能为空");
			res.setMsg("参数错误");
			logger.debug("返回BMTC 请求参数格式错误 ：{}", res);
			return res;
		}
		
		if (svnBatchName == "" || svnBatchName == null) {
			res.setCode("9999");
			res.setData("svn产品批次不能为空");
			res.setMsg("参数错误");
			logger.debug("返回BMTC 请求参数格式错误 ：{}", res);
			return res;
		}
		
		//执行测试套
		TestCaseThreadUtils testCaseThreadUtils = new TestCaseThreadUtils(testCaseService, executeParam);
		testCaseThreadUtils.start();
		
		res.setCode("0000");
		res.setData("1");
		res.setMsg("操作成功");
		
		logger.debug("返回BMTC android调度结果 ：{}", res);
		
		return res;
	}
	
}

