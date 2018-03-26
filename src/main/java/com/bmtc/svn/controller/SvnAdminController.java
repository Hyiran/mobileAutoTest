package com.bmtc.svn.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmatesoft.svn.core.SVNAuthenticationException;
import org.tmatesoft.svn.core.SVNException;

import com.bmtc.common.annotation.Log;
import com.bmtc.common.utils.R;
import com.bmtc.svn.common.web.BaseController;
import com.bmtc.svn.domain.SvnCreateBranchInfo;
import com.bmtc.svn.domain.SvnInfo;
import com.bmtc.svn.domain.SvnUserRepoInfo;
import com.bmtc.svn.service.SvnAdminService;
import com.bmtc.svn.service.SvnUserService;

/**
 * svn管理前端接口处理
 * @author lpf7161
 *
 */
@RequestMapping("/svn/admin")
@Controller
public class SvnAdminController extends BaseController {
	private static Logger logger = Logger.getLogger(SvnAdminController.class);
	
	@Autowired 
	private SvnUserService svnUserService;
	
	@Autowired 
	private SvnAdminService svnAdminService;
	
	@RequiresPermissions("svn:svnUserRight:passTest")
	@PostMapping("/svnRightPassTest")
	@Log("测试svn联通性")
	@ResponseBody
	public R svnRightPassTest(SvnInfo svnInfo) {
		logger.info("SvnAdminController.svnRightPassTest() start");
		//校验参数
		if (StringUtils.isEmpty(svnInfo.getSvnUserName())) {
			logger.error("svnUserName is null");
			return R.error(-1, "错误：svnUserName参数为空");
		}
		if (StringUtils.isEmpty(svnInfo.getSvnRepoName())) {
			logger.error("svnRepoName is null");
			return R.error(-1, "错误：svnRepoName参数为空");
		}
		if (StringUtils.isEmpty(svnInfo.getSvnRepoUrl())) {
			logger.error("svnRepoUrl is null");
			return R.error(-1, "错误：svnRepoUrl参数为空");
		}
		if (StringUtils.isEmpty(svnInfo.getSvnPath())) {
			logger.error("svnPath is null");
			return R.error(-1, "错误：svnPath参数为空");
		}
		
		String svnUserName = svnInfo.getSvnUserName();
		String svnRepoName = svnInfo.getSvnRepoName();
		SvnUserRepoInfo svnUserRepoInfo = svnUserService.querySvnUser(svnUserName, svnRepoName);
		int res = 0;
		try {
			res = svnAdminService.svnRightPassTest(svnInfo, svnUserRepoInfo);
		} catch(SVNAuthenticationException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return R.error(-1, "svn.auth.error:认证失败(" + e.getMessage() + ")");
		} catch (SVNException e) {
			e.printStackTrace();
			logger.error(e.getMessage());		
			return R.error(-1, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());	
			return R.error(-1, e.getMessage());
		}
		logger.info("SvnAdminController.svnRightPassTest() end");
		if(res == 1) {
			return R.ok("SVN认证成功");
		}
		return R.error(-1, "SVN认证失败");
	}
	
	
	@RequiresPermissions("svn:admin:getSvnUserRepoInfo")
	@GetMapping("/getSvnUserRepoInfoIdAndBatchId")
	@Log("根据产品id和批次id查询svn用户信息")
	@ResponseBody
	public Object getSvnUserRepoInfoIdAndBatchId(@RequestParam Long deptId,
			@RequestParam Long batchId) {
		logger.info("SvnAdminController.getSvnUserRepoInfoIdAndBatchId() start");
		//校验参数
		if (deptId == null) {
			logger.error("deptId is null");
			return R.error(-1, "错误：deptId参数为空");
		}
		
/*		if (batchId == null) {
			logger.error("batchId is null");
			return R.error(-1, "错误：batchId参数为空");
		}*/

		SvnUserRepoInfo svnUserRepoInfo = svnAdminService.getSvnUserRepoInfoIdAndBatchId(deptId, batchId);

		logger.info("SvnAdminController.getSvnUserRepoInfoIdAndBatchId() end");
		if(svnUserRepoInfo == null) {
			return R.error(-1, "请检查【该产品和批次是否含有svn分支】或者【系统配置中是否svn的IP地址查询超级用户的svn用户名】或者【超级用户是否含有该产品和批次对应svn仓库的根目录读写权限】");
		}
		return svnUserRepoInfo;
	}
	
	@RequiresPermissions("svn:admin:getSvnBranchInfoIdAndBatchId")
	@GetMapping("/getSvnBranchInfoIdAndBatchId")
	@Log("根据产品id和批次id查询svn分支url")
	@ResponseBody
	public Object getSvnBranchInfoIdAndBatchId(@RequestParam Long deptId,
			@RequestParam Long batchId) {
		logger.info("SvnAdminController.getSvnBranchInfoIdAndBatchId() start");
		//校验参数
		if (deptId == null) {
			logger.error("deptId is null");
			return R.error(-1, "错误：deptId参数为空");
		}
		
/*		if (batchId == null) {
			logger.error("batchId is null");
			return R.error(-1, "错误：batchId参数为空");
		}*/

		SvnCreateBranchInfo svnCreateBranchInfo = svnAdminService.getSvnBranchInfoIdAndBatchId(deptId, batchId);
		// SvnCreateBranchInfo svnCreateBranchInfo = svnAdminService.getSvnBranchInfoIdAndBatchId(deptId, null);

		logger.info("SvnAdminController.getSvnBranchInfoIdAndBatchId() end");
		if(svnCreateBranchInfo == null) {
			return R.error(-1, "请检查【该产品和批次是否含有svn分支】");
		}
		return svnCreateBranchInfo;
	}

}
