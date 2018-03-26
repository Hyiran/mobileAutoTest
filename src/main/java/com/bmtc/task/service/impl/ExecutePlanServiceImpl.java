package com.bmtc.task.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bmtc.common.config.BMTCConfig;
import com.bmtc.common.utils.Query;
import com.bmtc.common.utils.R;
import com.bmtc.common.utils.ShiroUtils;
import com.bmtc.device.domain.Device;
import com.bmtc.device.domain.ExecuteParam;
import com.bmtc.device.domain.TestCase;
import com.bmtc.device.utils.HttpRequestUtils;
import com.bmtc.script.dao.ScriptDao;
import com.bmtc.script.domain.Script;
import com.bmtc.svn.domain.SvnCreateBranchInfo;
import com.bmtc.svn.domain.SvnUserRepoInfo;
import com.bmtc.svn.service.SvnAdminService;
import com.bmtc.system.dao.ActuatorDao;
import com.bmtc.system.dao.BatchDao;
import com.bmtc.system.dao.DeptDao;
import com.bmtc.system.domain.Actuator;
import com.bmtc.system.domain.BatchDO;
import com.bmtc.system.domain.DeptDO;
import com.bmtc.system.service.UserService;
import com.bmtc.task.dao.BMTCTaskDao;
import com.bmtc.task.dao.ExecutePlanDao;
import com.bmtc.task.dao.ExecutePlanScriptDao;
import com.bmtc.task.domain.BMTCTask;
import com.bmtc.task.domain.ExecutePlan;
import com.bmtc.task.domain.ExecutePlanScriptDO;
import com.bmtc.task.domain.ManualCaseDO;
import com.bmtc.task.domain.ProductSvn;
import com.bmtc.task.service.BMTCTaskService;
import com.bmtc.task.service.ExecutePlanService;
import com.bmtc.task.service.ManualCaseService;
import com.bmtc.task.utils.TestCaseUtils;
import com.bmtc.wsdlATP.GetDataByATP;
import com.bmtc.wsdlATP.BMTC.ArrayOfString;
import com.bmtc.wsdlATP.BMTC.BMTCSoap;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * 测试任务执行计划的service接口实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class ExecutePlanServiceImpl implements ExecutePlanService{

	private static Logger logger = Logger.getLogger(ExecutePlanServiceImpl.class);
	
	@Autowired
	ExecutePlanDao executePlanMapper;
	@Autowired
	BMTCConfig bmtcConfig;
	@Autowired
	ExecutePlanScriptDao executePlanScriptMapper;
	@Autowired
	BMTCTaskDao bmtcTaskMapper;
	@Autowired
	BatchDao barchMapper;
	@Autowired
	BMTCTaskService bmtcTaskService;
	@Autowired
	DeptDao deptMapper;
	@Autowired
	UserService userService;
	@Autowired
	ScriptDao scriptMapper;
	@Autowired
	ActuatorDao actuatorMapper;
	@Autowired
	GetDataByATP getDataByATP;
	@Autowired
	ManualCaseService manualCaseService;
	@Autowired
	SvnAdminService svnAdminService;
	
	/**
	 * 查询执行计划列表数据
	 * @param query
	 * @return List<ExecutePlan>
	 */
	@Override
	public List<ExecutePlan> list(Query query) {
		logger.info("ExecutePlanServiceImpl.list() start");
		// 获得该用户所属的产品ID
		List<BMTCTask> tasks = bmtcTaskService.list(new HashMap<String, Object>());
		List<ExecutePlan> executePlans = new ArrayList<ExecutePlan>();
		for (BMTCTask bmtcTask : tasks) {
			List<ExecutePlan> list = executePlanMapper.getExecutePlanByTaskId(bmtcTask.getTaskId());
			executePlans.addAll(list);
		}
		List<ExecutePlan> results = new ArrayList<ExecutePlan>();
		// 遍历，添加deptName、batchName信息
		for (ExecutePlan executePlan : executePlans) {
			if(executePlan.getIsDeleted() == 0) {
				continue;
			}
			BMTCTask bmtcTask = bmtcTaskMapper.get(executePlan.getTaskId());
			executePlan.setBatchId(bmtcTask.getBatchId());
			executePlan.setBatchName(bmtcTask.getBatchName());
			executePlan.setDeptId(bmtcTask.getDeptId());
			executePlan.setDeptName(bmtcTask.getDeptName());
			results.add(executePlan);
		}
		logger.info("ExecutePlanServiceImpl.list() end");
		return results;
	}
	/**
	 * 查询执行计划总记录数
	 * @param query
	 * @return int
	 */
	@Override
	public int count(Query query) {
		logger.info("ExecutePlanServiceImpl.count() start");
		query.put("isDeleted", 1l);
		int count = executePlanMapper.count(query);
		logger.info("ExecutePlanServiceImpl.count() end");
		return count;
	}
	/**
	 * 通过id查询执行计划
	 * @param id
	 * @return ExecutePlan
	 */
	@Override
	public ExecutePlan get(Long id) {
		logger.info("ExecutePlanServiceImpl.get() start");
		ExecutePlan executePlan = executePlanMapper.get(id);
		if(executePlan.getIsDeleted() == 0) {
			return null;
		}
		BMTCTask bmtcTask = bmtcTaskService.get(executePlan.getTaskId());
		executePlan.setDeptId(bmtcTask.getDeptId());
		executePlan.setDeptName(bmtcTask.getDeptName());
		executePlan.setBatchId(bmtcTask.getBatchId());
		executePlan.setBatchName(bmtcTask.getBatchName());
		executePlan.setSvnPath(bmtcTask.getSvnPath());
		logger.info("ExecutePlanServiceImpl.get() end");
		return executePlan;
	}
	/**
	 * 保存执行计划
	 * @param ExecutePlan executePlan
	 * @return int
	 */
	@Override
	public R save(ExecutePlan executePlan) {
		logger.info("ExecutePlanServiceImpl.save() start");
		executePlan.setIsDeleted(1l);
		executePlanMapper.save(executePlan); 
		/**
		 * 维护测试任务与脚本的关联关系
		 */
		// 获得关联测试套路径集
		String testSuiteCaseNames = executePlan.getTestSuiteCaseNames();
		// 检查caseNum
//		List<String> result = checkCaseNum(executePlan.getTaskId(),testSuiteCaseNames);
		// 判断结果
//		if(result.size() != 0) {
//			// code=2,存在没有caseNum的案例
//			return R.error(2,JSON.toJSONString(result.toString()));
//		}
		// 如果未关联脚本，直接返回
		if (testSuiteCaseNames == null) {
			logger.info("ExecutePlanServiceImpl.save() end");
			return R.ok("保存成功，但未关联脚本");
		}
		List<TestCase> testCases = TestCaseUtils.getTestCases(testSuiteCaseNames);
		// TODO
		System.out.println(testCases.toString());
		System.out.println(JSON.toJSON(testCases));
		// 遍历testCases,维护测试任务和脚本的关联关系
		for (TestCase testCase : testCases) {
			// 获取每个测试套的id
			Script script = scriptMapper.getScriptByTestSuitPath(testCase
					.getTestSuite());
			// 判断script是否为null
			if(script == null) {
				return R.error("未找到该路径("+testCase
						.getTestSuite()+")下的脚本，请先到“脚本管理”界面，同步所选产品下的脚本！");
			}
			ExecutePlanScriptDO executePlanScriptDO = new ExecutePlanScriptDO(executePlan.getId(), script.getScriptId(), JSON.toJSONString(testCase.getCaseName()));
			// 保存信息到中间表
			executePlanScriptMapper.save(executePlanScriptDO);
		}
		logger.info("ExecutePlanServiceImpl.save() end");
		return R.ok("保存成功");
	}
	/**
	 * 检查caseNum
	 * @param taskId
	 * @param testSuiteCaseNames
	 * @return
	 */
	private List<String> checkCaseNum(Long taskId, String testSuiteCaseNames) {
		// 创建存储结果的集合
		List<String> results = new ArrayList<String>();
		BMTCTask bmtcTask = bmtcTaskMapper.get(taskId);
		// 分割前端提交的路径集
		String[] paths = testSuiteCaseNames.split(",");
		// 遍历
		List<String> caseNums = new ArrayList<String>();
		List<String> caseNames = new ArrayList<String>();
		for (String path : paths) {
			path = path.replace("\\", "/");
			// 判断路径是否包含“.txt”
			if(!path.contains(".txt")){//如果不包含，跳过此次循环
				continue;
			}
			// 判断路径是否以“.txt”结尾
			if(path.endsWith(".txt")) {//如果以“.txt”结尾，跳过此次循环
				continue;
			}
			String substring = path.substring(0, path.indexOf(".txt"));
			String p = path.replace(substring+".txt/", "");
			if(p.contains("/")) {// caseNum
				caseNums.add(path);
			} else {// caseName
				caseNames.add(path);
			}
		}
		// 判断caseName下有没有caseNum
		for (String caseName : caseNames) {
			boolean flag = false;
			for (String caseNum : caseNums) {
				if(caseNum.contains(caseName)) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				results.add(caseName);
			}
		}
		if(results.size() != 0) {// 存在案例没有caseNum
			return results;
		}
		// 判断casenum是否存在
		for (String path : caseNums) {
			String substring = path.substring(0, path.indexOf(".txt"));
			String p = path.replace(substring+".txt/", "");
			String caseNum = p.split("/")[1];
			ManualCaseDO manualCaseDO = manualCaseService.hasCaseNum(taskId, caseNum);
			if(manualCaseDO == null) {
				results.add(path.replace(bmtcConfig.getLocalPath()+"/"+bmtcTask.getDeptName()+"/", ""));
			}
		}
		return results;
	}
	/**
	 * 执行计划修改
	 * @param ExecutePlan executePlan
	 * @return R
	 */
	@Override
	public R update(ExecutePlan executePlan) {
		logger.info("ExecutePlanServiceImpl.update() start");
		if("0".equals(executePlan.getTriggerMode())){
			executePlan.setCondition("");
		}
		executePlanMapper.update(executePlan);
		/**
		 * 维护测试任务与脚本的关联关系
		 */
		// 获得关联测试套路径集
		String testSuiteCaseNames = executePlan.getTestSuiteCaseNames();
//		// 检查caseNum
//		List<String> result = checkCaseNum(executePlan.getTaskId(),testSuiteCaseNames);
//		// 判断结果
//		if(result.size() != 0) {
//			// code=2,存在没有caseNum的案例
//			return R.error(2,JSON.toJSONString(result.toString()));
//		}
		// 首先删除修改之前的中间表关联关系
		executePlanScriptMapper.remove(executePlan.getId());
		// 如果未关联脚本，直接返回
		if (testSuiteCaseNames == null) {
			logger.info("ExecutePlanServiceImpl.update() end");
			return R.ok("修改成功，但未关联脚本");
		}
		List<TestCase> testCases = TestCaseUtils.getTestCases(testSuiteCaseNames);
		// 如果关联的均为无效测试套，直接返回
		if (testCases == null) {
			logger.info("ExecutePlanServiceImpl.update() end");
			return R.ok("修改成功，但未关联脚本");
		}
		// 遍历testCases,维护测试任务和脚本的关联关系
		for (TestCase testCase : testCases) {
			// 获取每个测试套的id
			Script script = scriptMapper.getScriptByTestSuitPath(testCase
					.getTestSuite());
			ExecutePlanScriptDO executePlanScriptDO = new ExecutePlanScriptDO(executePlan.getId(), script.getScriptId(), JSON.toJSONString(testCase.getCaseName()));
			// 保存信息到中间表
			executePlanScriptMapper.save(executePlanScriptDO);
		}
		logger.info("ExecutePlanServiceImpl.update() end");
		return R.ok("修改成功");
	}
	/**
	 * 执行计划批量删除
	 * @param Long[] ids
	 * @return int
	 */
	@Override
	public int batchremove(Long[] ids) {
		// 删除执行计划与脚本的关联关系
		Long isDeleted = 0l;
		try {
			for (Long id : ids) {
//				executePlanScriptMapper.remove(id);
				executePlanMapper.remove(isDeleted, id);
			}
			return 1;// 成功
		} catch (Exception e) {
			return 0;// 失败
		}
		
	}
	/**
	 * 执行计划删除
	 * @param Long id
	 * @return int
	 */
	@Override
	public int remove(Long id) {
		// 删除执行计划
		Long isDeleted = 0l;
		int remove = executePlanMapper.remove(isDeleted,id);
		// 删除执行计划和脚本的关联关系
//		executePlanScriptMapper.remove(id);
		return remove;
	}
	
	/**
	 * 执行计划执行测试任务(本地)
	 * @param Long id
	 * @return String
	 */
	@Override
	public String active(Long id) {
		logger.info("ExecutePlanServiceImpl.active() start");
		// 通过id查询执行计划
		ExecutePlan executePlan = executePlanMapper.get(id);
		// 获得执行计划的设备类型
		String deviceType = executePlan.getDeviceType();
		// 执行计划关联的测试任务
		Long taskId = executePlan.getTaskId();
		// 获得测试任务对象
		BMTCTask bmtcTask = bmtcTaskService.get(taskId);
		// 获得所属的产品对象
		DeptDO deptDO = deptMapper.get(bmtcTask.getDeptId());
		// 获得所属的批次对象
		BatchDO batchDO = barchMapper.get(bmtcTask.getBatchId());
		// 获取执行任务关联的脚本集
		List<Script> scripts = executePlanScriptMapper.getScript(id);
		//判断脚本是否为空
		if(scripts == null || scripts.size() == 0) {
			return "该执行计划未关联自动化脚本";
		}
		// 替换参数中的本地元素
		for (Script script : scripts) {
			String scriptName = script.getScriptName().replace(".txt", "");
			script.setScriptName(scriptName);
			String testSuitPath = script.getTestSuitPath().replace(bmtcConfig.getLocalPath()+"/"+deptDO.getName(), deptDO.getSvnName());
			script.setTestSuitPath(testSuitPath);
		}
		// 创建ProductSvn
		ProductSvn productSvn = new ProductSvn();
		SvnUserRepoInfo svnUserRepoInfo = svnAdminService.getSvnUserRepoInfoIdAndBatchId(deptDO.getDeptId(), batchDO.getBatchId());
		String svnUserName = svnUserRepoInfo.getSvnUserName();
		String svnPassword = svnUserRepoInfo.getSvnPassword();
		productSvn.setUsername(svnUserName);
		productSvn.setPassword(svnPassword);
		productSvn.setProductName(deptDO.getName());
		productSvn.setProductId(deptDO.getDeptId());
		productSvn.setSvnBatchName(batchDO.getBatchSvnPath());
		productSvn.setRepository(deptDO.getSvnName());
		// 获得所有的执行机ip
		List<Actuator> actuators = actuatorMapper.list(new HashMap<String, Object>());
		// 判断执行机集合是否为空
		if (actuators == null || actuators.size() == 0) {
			return "当前无可用执行机，请添加执行机信息";
		}
		// 创建List集合存储返回的设备信息
		List<Device> devices = new ArrayList<Device>();
		String executeAdd = "";
		// 遍历，分别获得执行机上的设备信息
		for (Actuator actuator : actuators) {
			// 获取执行机ip
			String ip = actuator.getIp();
			String deviceUri = actuator.getDeviceUri();
			String activeUri = actuator.getActiveUri();
			String downLoadUri = actuator.getDownLoadUri();
			// 判断ip是否为空
			if (ip == null || "".equals(ip)) {
				continue;
			}
			// 判断deviceUri是否为空
			if (deviceUri == null || "".equals(deviceUri)) {
				continue;
			}
			// 判断deviceUri是否为空
			if (downLoadUri == null || "".equals(downLoadUri)) {
				continue;
			}
			// 拼接url
			String deviceInfoAdd = "http://" + ip + actuator.getDeviceUri();
			// 判断deviceUri是否为空
			if (activeUri == null || "".equals(activeUri)) {
				continue;
			}
			// 拼接执行接口地址
			executeAdd = "http://" + ip + actuator.getActiveUri();
			JSONObject httpget;
			try {
				// 获取设备信息:"http://22.11.27.29/bmtc/devices/info"
				httpget = HttpRequestUtils.httpPost(deviceInfoAdd, deviceType, false);
				logger.info(ip+":获取执行设备成功.=========={"+httpget+"}=========");
			} catch (Exception e) {
				logger.info(ip+":获取执行设备失败.=========={}=========");
				continue;
			}
			// 判断
			if(httpget == null) {
				return "获取设备信息异常！";
			}
			// 获取其中的data信息
			String data = httpget.getString("data");
			Gson gson = new Gson();
			// 将data使用gson转化为Device对象
			@SuppressWarnings("serial")
			List<Device> list = gson.fromJson(data, new TypeToken<List<Device>>(){}.getType());
			// ip绑定设备,并存储到集合
			for (Device device : list) {
				// 判断设备状态，是否繁忙
				if("1".equals(device.getStatus())){// 繁忙
					continue;
				}
				device.setIp(ip);
				devices.add(device);
				// 下载执行机代码
				String downLoadPath = "http://" + ip + actuator.getDownLoadUri();
				try {
					JSONObject httpPost = HttpRequestUtils.httpPost(downLoadPath, JSON.toJSONString(productSvn), false);
					logger.info(device.getName()+":下载脚本成功.=========={"+httpPost+"}=========");
				} catch (Exception e) {
					return device.getName()+"执行机下载代码异常";
				}
				
			}
		}
		// 判断devices是否为空
		if(devices.size() == 0){
			logger.info("ExecutePlanServiceImpl.active() end");
			// 获取的设备信息为空，即未检测到可执行设备
			return "未检测到可执行设备！";
		}
		// 如果不为空，遍历设备信息
		for (Device device : devices) {
			device.setPlatformName(deviceType);
			ExecuteParam executeParam = new ExecuteParam();
			executeParam.setDevice(device);
			executeParam.setProductSvn(productSvn);
			executeParam.setScripts(scripts);
			executeParam.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
			logger.info("*****************"+JSON.toJSONString(executeParam).toString()+"*****************");
			try{
				// 此值原为token,现有所更改，后维护
				JSONObject httpPost = HttpRequestUtils.httpPost(executeAdd, JSON.toJSONString(executeParam).toString(), false);
				logger.info(device.getIp()+"执行机上的"+device.getIp()+"执行机"+":获取执行设备成功.=========={"+httpPost+"}=========");
			} catch (Exception e) {
				logger.info(device.getIp()+"执行机上的"+device.getIp()+"执行机"+":获取执行设备失败");
				continue;
			}
		}
		// 修改执行计划状态为执行中,0，空闲；1，执行中；
		executePlan.setStatus(1l);
		executePlanMapper.update(executePlan);
		logger.info("ExecutePlanServiceImpl.active() end");
		return "开始执行！";
	}
	/**
	 * 发执行计划给ATP
	 * @param Long id
	 * @return String
	 */
	@Override
	public String sendToATP(Long id) {
		logger.info("ExecutePlanServiceImpl.sendToATP() start");
		// int userID
		Long userId = ShiroUtils.getUserId();
		int userID = Integer.valueOf(userId.toString());
		// 通过id查询执行计划
		ExecutePlan executePlan = executePlanMapper.get(id);
		// 执行计划关联的测试任务
		Long taskId = executePlan.getTaskId();
		// 获得测试任务对象
		BMTCTask bmtcTask = bmtcTaskService.get(taskId);
		// 获得所属的产品对象
		DeptDO deptDO = deptMapper.get(bmtcTask.getDeptId());
		// string productName
		String productName = deptDO.getName();
		// int productID
		Long deptId = deptDO.getDeptId();
		int productID = Integer.valueOf(deptId.toString());
		// 获得所属的批次对象
		BatchDO batchDO = barchMapper.get(bmtcTask.getBatchId());
		// string repository
		SvnCreateBranchInfo info = svnAdminService.getSvnBranchInfoIdAndBatchId(deptDO.getDeptId(), batchDO.getBatchId());
		String repository = info.getNewBranch().replace("\\", "/");
		String batchSVNName = repository.replace(info.getSvnRepoUrl().replace("\\", "/")+"/", "");
		// int batchID
		Long batchId = batchDO.getBatchId();
		int batchID = Integer.valueOf(batchId.toString());
		// 获得svn的用户名和密码
		SvnUserRepoInfo svnUserRepoInfo = svnAdminService.getSvnUserRepoInfoIdAndBatchId(deptDO.getDeptId(), batchDO.getBatchId());
		String svnUserName = svnUserRepoInfo.getSvnUserName();
		String svnPassword = svnUserRepoInfo.getSvnPassword();
		// 获取执行任务关联的脚本集
		List<Script> scripts = executePlanScriptMapper.getScript(id);
		// testCase
		List<TestCase> testCases = TestCaseUtils.getTestCaseBySuitPath(scripts);
		List<String> list = new ArrayList<String>();
		for (TestCase testCase : testCases) {
			String testSuite = testCase.getTestSuite();
			String testS = testSuite.replace(bmtcConfig.getLocalPath()+"/"+deptDO.getName()+"/"+batchSVNName,repository);
			testCase.setTestSuite(testS);
			String testCaseString = JSON.toJSONString(testCase);
			list.add(testCaseString);
		}
		BMTCSoap soap;
		String saveATPCases = "";
		try {
			ArrayOfString arrayOfString = new ArrayOfString();
			arrayOfString.setString(list);
			logger.info("**********上送的脚本信息："+list+"***********");
			soap = getDataByATP.getData();
			saveATPCases = soap.saveATPCases(batchID, productID, arrayOfString, userID, "", productName, repository, svnUserName, svnPassword);
			logger.info("**********上送结果result:"+saveATPCases+"***********");
		} catch (MalformedURLException e) {
			logger.error("**********上送失败***********");
			e.printStackTrace();
		}
		logger.info("ExecutePlanServiceImpl.sendToATP() end");
		return saveATPCases;
	}
}