package com.bmtc.svn.service;


import org.tmatesoft.svn.core.SVNAuthenticationException;
import org.tmatesoft.svn.core.SVNException;

import com.bmtc.svn.domain.SvnCreateBranchInfo;
import com.bmtc.svn.domain.SvnInfo;
import com.bmtc.svn.domain.SvnUserRepoInfo;

/**
 * SvnAdminService业务逻辑接口
 * @author lpf7161
 *
 */
public interface SvnAdminService {

	/**
	 * 测试svn权限联通性
	 * @param SvnInfo, svnUserRepoInfo
	 */
	int svnRightPassTest(SvnInfo svnInfo, SvnUserRepoInfo svnUserRepoInfo) 
			throws SVNAuthenticationException, SVNException, Exception;
	
	/**
	 * 通过产品id和批次id查询svn用户信息
	 * @param deptId, batchId
	 * @return SvnUserRepoInfo
	 */
	SvnUserRepoInfo getSvnUserRepoInfoIdAndBatchId(Long deptId, Long batchId);
	
	/**
	 * 通过产品id和批次id查询svn分支url
	 * @param deptId, batchId
	 * @return SvnCreateBranchInfo
	 */
	SvnCreateBranchInfo getSvnBranchInfoIdAndBatchId(Long deptId, Long batchId);
}
