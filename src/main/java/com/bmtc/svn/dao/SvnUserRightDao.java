package com.bmtc.svn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bmtc.svn.domain.SvnUserAuthz;
import com.bmtc.svn.domain.SvnUserAuthzInfo;

/**
 * SVN权限开通接口
 * @author lpf7161
 *
 */
@Mapper
public interface SvnUserRightDao {
	/**
	 * 根据SVN用户名查询svn用户id
	 * @param svnUserName
	 */
	List<String> querySvnUserIdBySvnUserName(String svnUserName);
	
	/**
	 * 根据SVN用户id查询svn_user_authz表中记录个数
	 * @param svnUserId
	 */
	int countBySvnUserId(long svnUserId);
	
	/**
	 * 根据SVN用户id和SVN路径查询svn_user_authz表中记录个数
	 * @param svnUserId,svnPath
	 */
	int countBySvnUserIdAndSvnPath(@Param("svnUserId") long svnUserId, 
			@Param("svnPath") String svnPath);
	
	/**
	 * 增加用户SVN权限，首先根据SVN库名执行querySvnRepoIdBySvnRepoName得到svnRepoId，
	 * 然后根据SVN用户名和svnRepoId执行querySvnUser()得到svnUserId，
	 * 接着根据svnUserId和svnPath查询记录个数，记录为0时将svnUserAuthz写入svn_user_authz表；
	 * 记录非0时，说明表中已经有该用户在该SVN仓库下svnPath的权限，则不用插入记录
	 * @param svnUserAuthz
	 */
	int addSvnUserRight(SvnUserAuthz svnUserAuthz);
	
	/**
	 * 根据SVN用户名、库名和SVN路径修改用户SVN权限，首先根据SVN库名执行querySvnRepoIdBySvnRepoName得到svnRepoId，
	 * 然后根据SVN用户名和svnRepoId执行querySvnUser()得到svnUserId，
	 * 然后根据svnUserId和svnPath查询记录个数，记录非0时修改svnUserAuthz信息；
	 * @param svnUserAuthz
	 */
	int updateSvnUserRight(SvnUserAuthz svnUserAuthz);
	
	/**
	 * 根据svnRepoId查询用户在该仓库下的svn权限信息集
	 * @param svnRepoId
	 */
	List<SvnUserAuthzInfo> getSvnUserRightList(long svnRepoId);

	/**
	 * @param rootPath
	 * 			svn root path
	 * @return 查询具有相同svn root的项目资源的权限列表
	 */
	List<SvnUserAuthzInfo> getListByRootPath(String rootPath);
	
	/**
	 * 根据SVN用户名和仓库名查询用户SVN权限，首先根据SVN库名执行querySvnRepoIdBySvnRepoName得到svnRepoId，
	 * 然后根据SVN用户名和svnRepoId执行querySvnUser()得到svnUserId，
	 * 接着根据svnUserId查询该用户在该仓库下的svn权限信息集
	 * @param svnUserId
	 */
	List<SvnUserAuthzInfo> querySvnUserRight(Map<String, Object> params);
	
	
	/**
	 * 根据SVN用户名和仓库名查询用户SVN权限记录数
	 * @param params
	 * @return
	 */
	int countSvnRight(Map<String, Object> params);
	
	/**
	 * 根据SVN用户名和仓库名查询用户SVN权限
	 * @param params
	 * @return
	 */
	List<SvnUserAuthzInfo> querySvnRight(Map<String, Object> params);
	
	
	
	/**
	 * 删除用户SVN权限，首先根据SVN库名执行querySvnRepoIdBySvnRepoName得到svnRepoId，
	 * 然后根据SVN用户名和svnRepoId执行querySvnUser()得到svnUserId，
	 * 接着根据svnUserId和svnPath查询记录个数，记录非0时删除用户SVN权限
	 * @param svnUserId,svnPath
	 */
	int deleteSvnUserRight(@Param("svnUserId") long svnUserId, 
			@Param("svnPath") String svnPath);
	
	/**
	 * 删除用户SVN权限
	 * 首先根据SVN用户名和仓库名执行querySvnUser()得到svnUserId，
	 * 接着根据svnUserId查询记录个数，记录非0时删除用户SVN权限，
	 * 即删除与用户名为svnUserName和仓库名为svnRepoName相关的所有权限信息
	 * @param svnUserId
	 */
	int deleteSvnUserRightBySvnUserId(long svnUserId);
	
	/**
	 * 根据SVN用户名查询用户SVN权限，首先根据SVN用户名执行querySvnUserIdBySvnUserName()得到svnUserId集，
	 * 然后根据svnUserId集查询该用户在每一个仓库中的svn权限信息集
	 * @param svnUserId
	 */
	//List<SvnUserAuthzInfo> querySvnUserRightBySvnUserName(String svnUserId);
	
	/**
	 * 根据SVN用户名和仓库名查询用户SVN权限，首先根据SVN用户名、库名执行querySvnUserId()得到svnUserId，
	 * 然后根据svnUserId查询该用户在该仓库下的svn权限信息集
	 * @param svnUserId
	 */
	List<SvnUserAuthzInfo> querySvnUserRightBySvnUserId(Map<String, Object> params);
	
}
