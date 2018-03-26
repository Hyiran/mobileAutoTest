package com.bmtc.svn.domain;

/**
 * SVN用户-仓库信息类
 * @author lpf7161
 *
 */
public class SvnInfo {
	
	/**
	 * 用户真实姓名
	 */
	private String name;
	
	/**
	 * svn用户名
	 */
	private String svnUserName;
	
	/**
	 * svn仓库名
	 */
	private String svnRepoName;
	
	/**
	 * svn仓库路径
	 */
	private String svnRepoPath;
	
	/**
	 * svn仓库url
	 */
	private String svnRepoUrl;
	
	/**
	 * svn仓库描述
	 */
	private String svnRepoDes;
	
	/**
	 * svn权限路径
	 */
	private String svnPath;
	
	/**
	 * svn用户权限
	 */
	private String svnUserAuthz; 

	public String getSvnUserName() {
		return svnUserName;
	}

	public void setSvnUserName(String svnUserName) {
		this.svnUserName = svnUserName;
	}

	public String getSvnRepoName() {
		return svnRepoName;
	}

	public void setSvnRepoName(String svnRepoName) {
		this.svnRepoName = svnRepoName;
	}

	public String getSvnRepoPath() {
		return svnRepoPath;
	}

	public void setSvnRepoPath(String svnRepoPath) {
		this.svnRepoPath = svnRepoPath;
	}

	public String getSvnRepoUrl() {
		return svnRepoUrl;
	}

	public void setSvnRepoUrl(String svnRepoUrl) {
		this.svnRepoUrl = svnRepoUrl;
	}

	public String getSvnRepoDes() {
		return svnRepoDes;
	}

	public void setSvnRepoDes(String svnRepoDes) {
		this.svnRepoDes = svnRepoDes;
	}
	
	public String getSvnPath() {
		return svnPath;
	}

	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}

	public String getSvnUserAuthz() {
		return svnUserAuthz;
	}

	public void setSvnUserAuthz(String svnUserAuthz) {
		this.svnUserAuthz = svnUserAuthz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SvnInfo [name=" + name + ", svnUserName=" + svnUserName
				+ ", svnRepoName=" + svnRepoName + ", svnRepoPath="
				+ svnRepoPath + ", svnRepoUrl=" + svnRepoUrl + ", svnRepoDes="
				+ svnRepoDes + ", svnPath=" + svnPath + ", svnUserAuthz="
				+ svnUserAuthz + "]";
	}

	public SvnInfo(String name, String svnUserName, String svnRepoName,
			String svnRepoPath, String svnRepoUrl, String svnRepoDes,
			String svnPath, String svnUserAuthz) {
		super();
		this.name = name;
		this.svnUserName = svnUserName;
		this.svnRepoName = svnRepoName;
		this.svnRepoPath = svnRepoPath;
		this.svnRepoUrl = svnRepoUrl;
		this.svnRepoDes = svnRepoDes;
		this.svnPath = svnPath;
		this.svnUserAuthz = svnUserAuthz;
	}

	public SvnInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
