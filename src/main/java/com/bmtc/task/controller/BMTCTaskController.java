package com.bmtc.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bmtc.common.annotation.Log;
import com.bmtc.common.config.BMTCConfig;
import com.bmtc.common.controller.BaseController;
import com.bmtc.common.domain.Tree;
import com.bmtc.common.utils.PageUtils;
import com.bmtc.common.utils.Query;
import com.bmtc.common.utils.R;
import com.bmtc.script.service.ScriptService;
import com.bmtc.svn.domain.SvnCreateBranchInfo;
import com.bmtc.svn.service.SvnAdminService;
import com.bmtc.system.domain.DeptDO;
import com.bmtc.system.service.BatchService;
import com.bmtc.system.service.DeptService;
import com.bmtc.task.domain.BMTCTask;
import com.bmtc.task.service.BMTCTaskService;

/**
 * 测试任务的controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/task")
public class BMTCTaskController extends BaseController{

	private static Logger logger = Logger.getLogger(BMTCTaskController.class);
	// 返回映射路径的前缀
	private String prefix = "task";
	@Autowired
	BMTCTaskService bMTCTaskService;
	@Autowired
	ScriptService scriptService;
	@Autowired
	BatchService batchService;
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	DeptService deptService;
	@Autowired
	BMTCConfig bmtcConfig;
	@Autowired
	SvnAdminService svnAdminService;
	
	/**
	 * 前段请求访问测试任务列表页面
	 * @return String
	 */
	@RequiresPermissions("test:task:task")
	@GetMapping()
	String task() {
		logger.info("BMTCTaskController.task() start");
		logger.info("BMTCTaskController.task() end");
		return prefix + "/task";
	}
	/**
	 * 查询测试任务数据
	 * @param params
	 * @return PageUtils
	 */
	@GetMapping("/list")
	@RequiresPermissions("test:task:list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		logger.info("BMTCTaskController.list() start");
		// 分页查询参数数据
		Query query = new Query(params);
		// 查询数据库查询测试任务数据
		List<BMTCTask> taskList = bMTCTaskService.list(query);
		// 查询总记录数
		int total = bMTCTaskService.count(query);
		// 封装到返回对象
		PageUtils pageUtil = new PageUtils(taskList, total);
		
		logger.info("BMTCTaskController.list() end");
		
		// 返回数据
		return pageUtil;
	}
	/**
	 * 添加执行计划界面，前段访问测试任务数据列表页面
	 * @param 
	 * @return String
	 */
	@GetMapping("/showTaskList")
	String showTaskList() {
		logger.info("BMTCTaskController.showTaskList() start");
		// 查询数据库查询测试任务数据
		logger.info("BMTCTaskController.showTaskList() end");
		// 返回数据
		return prefix + "/showTaskList";
	}
	/**
	 * 添加执行计划界面，获得测试任务数据的Tree
	 * @param 
	 * @return Tree<BMTCTask>
	 */
	@GetMapping("/getTaskListTree")
	@ResponseBody
	Tree<BMTCTask> getTaskListTree() {
		logger.info("BMTCTaskController.getTaskListTree() start");
		// 获得测试任务数据的Tree
		Tree<BMTCTask> taskTreeList = bMTCTaskService.getTaskList();
		logger.info("BMTCTaskController.getTaskListTree() end");
		// 返回数据
		return taskTreeList;
	}
	/**
	 * 测试任务add页面跳转
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("test:task:add")
	@Log("添加测试任务")
	@GetMapping("/add")
	String add() {
		logger.info("BMTCTaskController.add() start");
		logger.info("BMTCTaskController.add() end");
		return prefix + "/add";
	}
	
	/**
	 * 保存测试任务
	 * @param BMTCTask bmtcTask
	 * @return R
	 */
	@RequiresPermissions("test:task:add")
	@Log("保存测试任务")
	@PostMapping("/save")
	@ResponseBody
	R save(@RequestParam(value = "uploadFile")MultipartFile file,BMTCTask bmtcTask) {
		logger.info("BMTCTaskController.save() start");
		logger.info("BMTCTaskController.save() end");
		return bMTCTaskService.save(bmtcTask,file);
	}
	/**
	 * 测试任务edit页面跳转
	 * @param Long taskId
	 * @return String
	 */
	@RequiresPermissions("test:task:edit")
	@GetMapping("/edit/{taskId}")
	String edit(Model model, @PathVariable("taskId") Long taskId) {
		logger.info("BMTCTaskController.edit() start");
		// 通过taskID获得测试任务对象
		BMTCTask bMTCTask = bMTCTaskService.get(taskId);
		// 通过测试任务对象中的deptId获得，测试任务所属的产品对象
		DeptDO deptDO = deptService.get(Long.valueOf(bMTCTask.getDeptId().toString()));
		SvnCreateBranchInfo info = svnAdminService.getSvnBranchInfoIdAndBatchId(Long.valueOf(deptDO.getDeptId()),null);
		// 判断url是否为空
		if(info == null) {
			return "获取所选产品对应SVN库路径失败，请重新选择！";
		}
		String newBranch = info.getNewBranch().replace("\\", "/");
		// 判断newBranch是否为空
		if(newBranch == null || "".equals(newBranch)) {
			return "此产品对应的SVN仓库路径不存在，请联系SVN管理员！";
		}
		String batchSVNName = newBranch.replace(info.getSvnRepoUrl()+"/", "");
		model.addAttribute("bMTCTask", bMTCTask);
		model.addAttribute("svnPath", deptDO.getSvnName());
		model.addAttribute("batchSvnPath", batchSVNName);
		model.addAttribute("batchId", bMTCTask.getBatchId());
		logger.info("BMTCTaskController.edit() end");
		return prefix+"/edit";
	}
	/**
	 * 测试任务修改
	 * @param BMTCTask bMTCTask
	 * @return R
	 */
	@RequiresPermissions("test:task:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(@RequestParam(value = "uploadFile")MultipartFile file,BMTCTask bMTCTask) {
		logger.info("BMTCTaskController.update() start");
		logger.info("BMTCTaskController.update() end");
		return bMTCTaskService.update(bMTCTask,file);
	}
	
	/**
	 * 测试任务批量删除
	 * @param taskIds
	 * @return R
	 */
	@RequiresPermissions("test:task:batchRemove")
	@Log("批量删除测试任务")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] taskIds) {
		logger.info("BMTCTaskController.batchRemove() start");
		int r = bMTCTaskService.batchremove(taskIds);
		if (r > 0) {
			logger.info("BMTCTaskController.batchRemove() end");
			return R.ok("删除成功！");
		} else if(r == -1) {
			logger.info("BMTCTaskController.batchRemove() end");
			return R.error("测试任务还有关联的执行计划，请先删除关联的执行计划！");
		}
		logger.info("BMTCTaskController.batchRemove() end");
		return R.error("删除失败！");
	}
	/**
	 * 测试任务删除
	 * @param taskIds
	 * @return R
	 */
	@RequiresPermissions("test:task:remove")
	@Log("删除测试任务")
	@PostMapping("/remove")
	@ResponseBody
	R remove(@RequestParam("id") Long taskId) {
		logger.info("BMTCTaskController.remove() start");
		int r = bMTCTaskService.remove(taskId);
		if (r > 0) {
			logger.info("BMTCTaskController.remove() end");
			return R.ok("删除成功！");
		} else if(r == -1) {
			logger.info("BMTCTaskController.batchRemove() end");
			return R.error("测试任务还有关联的执行计划，请先删除关联的执行计划！");
		}
		logger.error("BMTCTaskController.remove() end");
		return R.error("删除失败！");
	}
	/**
	 * 获取测试任务关联脚本Tree信息
	 * @param String svnUrl
	 * @return List<Tree<String>>
	 */
	@PostMapping("/getTreeData")
	@ResponseBody
	public List<Tree<String>> getTreeData(@RequestParam String taskId) {
		try {
			logger.info("BMTCTaskController.getTreeData() start");
			List<Tree<String>> tree = new ArrayList<Tree<String>>();
			tree = scriptService.getTreeData(taskId);
			logger.info("BMTCTaskController.getTreeData() end");
			return tree;
		} catch (Exception e) {
			logger.error("所属产品下无此批次脚本");
			return null;
		}
	}
//
//	/**
//	 * 判断前端选择的产品及分支，是否存在
//	 * @param batchName
//	 * @return
//	 */
//	@PostMapping("/checkBatchName")
//	@Log("判断批次路径是否存在")
//	@ResponseBody
//	R checkBatchName(@RequestParam String batchId,@RequestParam String deptId) {
//		logger.info("BMTCTaskController.checkBatchName() start");
//		// 判断产品名称
//		if(deptId == null || "".equals(deptId)) {
//			logger.info("BMTCTaskController.checkBatchName() end");
//			return R.error("产品不能为空!");
//		}
//		// 判断批次ID
//		if(batchId == null || "".equals(batchId)) {
//			logger.info("BMTCTaskController.checkBatchName() end");
//			return R.error("批次不能为空!");
//		}
//		// 获得产品和批次对应的svn路径
//		SvnCreateBranchInfo info = svnAdminService.getSvnBranchInfoIdAndBatchId(Long.valueOf(deptId),Long.valueOf(batchId));
//		// 判断url是否为空
//		if(info == null) {
//			return R.error("所选择的产品和批次无对应SVN库，请联系SVN管理员!");
//		}
//		String svnRepoUrl = info.getSvnRepoUrl();
//		// 判断url是否为空
//		if(svnRepoUrl == null || "".equals(svnRepoUrl)) {
//			return R.error("此产品和批次对应的SVN仓库路径不存在，请联系SVN管理员！");
//		}
//		String filePath = svnRepoUrl.replace(info.getSvnRepoUrl(), bmtcConfig.getLocalPath());
//		File file = new File(filePath);
//		boolean exists = file.exists();
//		if(exists){
//			return R.ok();
//		}
//		logger.info("BMTCTaskController.checkBatchName() end");
//		// 不存在，不通过，false
//		return R.error("所属产品或批次<b>SVN路径</b>与本地路径不匹配，请先到“脚本管理”界面同步所属产品的脚本!");
//	}
	
	/**
	 * 获取该产品对应的SVN路径
	 * @param deptId
	 * @return
	 */
	@PostMapping("/getSvnRepoPath")
	@ResponseBody
	R getSvnRepoPath(@RequestParam String deptId) {
		logger.info("BMTCTaskController.getSvnPath() start");
		try {
			SvnCreateBranchInfo info = svnAdminService.getSvnBranchInfoIdAndBatchId(Long.valueOf(deptId),null);
			if(info == null) {
				logger.info("BMTCTaskController.getSvnPath() end");
				return R.error("获取所选产品对应SVN库路径不存在，请重新选择！");
			}
			logger.info("BMTCTaskController.getSvnPath() end");
			return R.ok(info.getSvnRepoUrl());
		} catch (Exception e) {
			logger.info("BMTCTaskController.getSvnPath() end");
			return R.error("获取所选产品对应SVN库路径不存在，请重新选择！");
		}
	}
	/**
	 * 获取该批次对应的SVN分支路径
	 * @param deptId
	 * @return
	 */
	@PostMapping("/getBatchSvnPath")
	@ResponseBody
	R getBatchSvnPath(@RequestParam String batchId,@RequestParam String deptId) {
		logger.info("BMTCTaskController.getBatchSvnPath() start");
		try {
			SvnCreateBranchInfo info = svnAdminService.getSvnBranchInfoIdAndBatchId(Long.valueOf(deptId),Long.valueOf(batchId));
			String newBranch = info.getNewBranch().replace("\\", "/");
			String batchSVNName = newBranch.replace(info.getSvnRepoUrl().replace("\\", "/")+"/", "");
			logger.info("BMTCTaskController.getBatchSvnPath() end");
			return R.ok(batchSVNName);
		} catch (Exception e) {
			logger.info("BMTCTaskController.getBatchSvnPath() end");
			return R.error("此产品下没有这个批次分支，请重新选择！");
		}
	}
	/**
	 * 测试任务add页面打开选择产品页面
	 * @param 
	 * @return String
	 */
	@GetMapping("/showDepts")
	String showDepts() {
		logger.info("BMTCTaskController.showDepts() start");
		logger.info("BMTCTaskController.showDepts() end");
		return prefix + "/showDepts";
	}
	/**
	 * 测试任务add页面打开选择产品页面
	 * @param 
	 * @return String
	 */
	@GetMapping("/getDeptTree")
	@ResponseBody
	Tree<DeptDO> getDeptTree() {
		logger.info("BMTCTaskController.getDeptTree() start");
		Tree<DeptDO> tree = bMTCTaskService.getDeptTree();
		logger.info("BMTCTaskController.getDeptTree() end");
		return tree;
	}
}
