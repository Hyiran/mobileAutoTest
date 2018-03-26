package com.bmtc.svn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNAuthenticationException;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bmtc.svn.common.utils.EncryptUtil;
import com.bmtc.svn.common.utils.SVNUtil;
import com.bmtc.svn.dao.SvnCreateBranchDao;
import com.bmtc.svn.dao.SvnUserRightDao;
import com.bmtc.svn.domain.SvnCreateBranchInfo;
import com.bmtc.svn.domain.SvnInfo;
import com.bmtc.svn.domain.SvnUserRepoInfo;
import com.bmtc.svn.service.SvnAdminService;
import com.bmtc.svn.service.SvnUserService;
import com.bmtc.system.domain.ConfigInfoDO;
import com.bmtc.system.service.ConfigService;


/**
 * SvnAdminService业务逻辑实现
 * @author lpf7161
 *
 */
@Service("SvnAdminService")
public class SvnAdminServiceImpl implements SvnAdminService {
	private static Logger logger = Logger.getLogger(SvnAdminServiceImpl.class);
	
	@Autowired
	private SvnCreateBranchDao svnCreateBranchDao;
	
	@Autowired
	private SvnUserRightDao svnUserRightDao;
	
	/**
	 * 系统配置信息服务层
	 */
	@Autowired
	private ConfigService configService;
	
	/**
	 * SVN用户服务层
	 */
	@Autowired
	private SvnUserService svnUserService;
	
	/**
	 * 测试svn权限联通性
	 * @param SvnInfo, svnUserRepoInfo
	 */
	@Override
	public int svnRightPassTest(SvnInfo svnInfo, SvnUserRepoInfo svnUserRepoInfo) 
			throws SVNAuthenticationException, SVNException, Exception {
		logger.info("SvnAdminServiceImpl.svnRightPassTest() start");
		String svnRepoUrl = svnInfo.getSvnRepoUrl();
		String svnUrl = svnInfo.getSvnRepoUrl();
		String svnPath = svnInfo.getSvnPath();
		String svnUserName = svnInfo.getSvnUserName();
		String svnPassword = svnUserRepoInfo.getSvnPassword();
		//对SVN用户密码进行解密
		svnPassword = EncryptUtil.decrypt(svnPassword);
		//截取svn路径，如[test:/svnadmin_init/test]，获取/svnadmin_init/test
		svnPath = svnPath.split(":")[1].substring(0, svnPath.split(":")[1].length() - 1);
		if(StringUtils.isBlank(svnPath)){
			svnPath = "/";//root路径
		}
		if(!svnPath.startsWith("/")){
			svnPath = "/" + svnPath;
		}
		svnUrl = svnUrl + svnPath;
		SVNRepository repository = null;
		// boolean isSucceed = false;
		try {
			// 测试svn权限开通成功与否
			// 获取svn仓库信息
			repository = SVNUtil.getRepository(svnRepoUrl, svnUserName, svnPassword);		
			// 若用户名和密码错误，此处便会抛出SVNAuthenticationException，前端显示认证失败的异常
			repository.testConnection();
			long latestRevision = repository.getLatestRevision(); // 修订-1代表最新版本号，初始版本为0
			// 若此用户在svnUrl下没有权限，则抛出SVNAuthenticationException，前端显示认证失败的异常
			repository.checkPath(svnPath, latestRevision);
			// isSucceed = repositoryService.testRepositoryConnected(repository, svnUrl);
		} catch(SVNAuthenticationException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.info("SvnAdminServiceImpl.svnRightPassTest() end");
			throw new RuntimeException("svn.auth.error:认证失败(" + e.getMessage() + ")");
    	} catch (SVNException e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		logger.info("SvnAdminServiceImpl.svnRightPassTest() end");		
    		throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		logger.info("SvnAdminServiceImpl.svnRightPassTest() end");		
    		throw new RuntimeException(e.getMessage());
		} finally {
			if(repository != null) {
				repository.closeSession();
			}
		}
		logger.info("SvnAdminServiceImpl.svnRightPassTest() end");	
/*		if(isSucceed == true) return R.ok("SVN认证成功");
		return R.error(-1, "SVN认证失败");*/
		return 1;
	}

	/**
	 * 通过产品id和批次id查询svn用户信息
	 * @param deptId, batchId
	 * @return SvnUserRepoInfo
	 */
	@Override
	public SvnUserRepoInfo getSvnUserRepoInfoIdAndBatchId(Long deptId,
			Long batchId) {
		logger.info("SvnAdminServiceImpl.getSvnUserRepoInfoIdAndBatchId() start");
		List<SvnCreateBranchInfo> svnCreateBranchInfoList = svnCreateBranchDao.getBranchInfoByDeptIdAndBatchId(deptId, batchId);
		if(svnCreateBranchInfoList == null) {
			return null;
		}
		SvnCreateBranchInfo svnCreateBranchInfo = svnCreateBranchInfoList.get(0);
		if(svnCreateBranchInfo == null) {
			return null;
		}
		// 根据svn url截取svn服务器的IP地址
		String svnServerIp = svnCreateBranchInfo.getSvnRepoUrl().split("//")[1].split("/")[0];
		// 根据svn的IP地址查询超级用户的svn用户名
		ConfigInfoDO configInfoDO = configService.getConfigInfo(svnServerIp);
		if(configInfoDO == null) {
			return null;
		}
		String svnRootUserName = configInfoDO.getSvnRootUserName();
		// 根据SVN用户名和库名查询SVN用户信息，获取超级用户在仓库svnCreateBranchInfo.getSvnRepoName()的svn密码
		SvnUserRepoInfo svnUserRepoInfo = svnUserService.querySvnUser(svnRootUserName, 
				svnCreateBranchInfo.getSvnRepoName());
		
		// 判断超级用户是否拥有相应仓库的根目录的读写权限
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("svnUserName", svnRootUserName);
		params.put("svnRepoName", svnUserRepoInfo.getSvnRepoName());
		params.put("svnPath", "[" + svnUserRepoInfo.getSvnRepoName() + ":/]");
		params.put("svnUserAuthz", "rw");
		// 根据SVN用户名、仓库名、svn路径、svn权限查询用户SVN权限总记录数
		int total = svnUserRightDao.countSvnRight(params);
		
		// 若超级用户没有相应svn仓库的根权限，则直接返回null
		if(svnUserRepoInfo == null || svnUserRepoInfo.getSvnPassword() == null || total == 0) {
			return null;
		} else {
			svnUserRepoInfo.setSvnUserName(svnRootUserName);
			// 对svn用户密码进行解密
			svnUserRepoInfo.setSvnPassword(EncryptUtil.decrypt(svnUserRepoInfo.getSvnPassword()));
		}
		
		logger.info("SvnAdminServiceImpl.getSvnUserRepoInfoIdAndBatchId() end");
		return svnUserRepoInfo;
	}
	
	/**
	 * 通过产品id和批次id查询svn分支url
	 * @param deptId, batchId
	 * @return SvnCreateBranchInfo
	 */
	@Override
	public SvnCreateBranchInfo getSvnBranchInfoIdAndBatchId(Long deptId,
			Long batchId) {
		logger.info("SvnAdminServiceImpl.getSvnBranchInfoIdAndBatchId() start");
		List<SvnCreateBranchInfo> svnCreateBranchInfoList = svnCreateBranchDao.getBranchInfoByDeptIdAndBatchId(deptId, batchId);
		if(svnCreateBranchInfoList == null) {
			return null;
		}
		SvnCreateBranchInfo svnCreateBranchInfo = svnCreateBranchInfoList.get(0);
		if(svnCreateBranchInfo == null) {
			return null;
		}
		logger.info("SvnAdminServiceImpl.getSvnBranchInfoIdAndBatchId() end");
		return svnCreateBranchInfo;
	}
	
}
