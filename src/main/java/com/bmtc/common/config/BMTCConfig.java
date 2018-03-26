package com.bmtc.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="bmtc")
public class BMTCConfig {
	// 上传路径
	private String uploadPath;
	// 查询设备状态的接口地址
	private String deviceInfoAdd;
	// 执行测试计划的接口地址
	private String executeAdd;
	// svn库地址的url
	private String url;
	// 下载脚本需要登录的用户名
	private String username;
	// 下载脚本需要登录的密码
	private String password;
	// 下载到本地的路径wcPath
	private String localPath;
	//测试案例上传临时文件路径
	private String uploadFile;

   // svn配置文件(authz, passwd, svnserve.conf)在本地的存放位置
	private String svnConfFilesLocation;
	// svn服务器的用户名，用于远程操作svn的配置文件
    private String svnServerUserName;
    // svn服务器登录口令，用于远程操作svn的配置文件
    private String svnServerPassword;
    
    // svn超级用户的用户名，用于获取svn仓库目录的树形结构
    private String svnRootUserName;
    
    // 远程连接端口号
    private String port;
    //wsdl接口的url
    private String bmtcURL;
    //wsdl接口Qname参数1
    private String Qname1;
    //wsdl接口Qname参数2
    private String Qname2;
    //定时更新sys_organization表的cron表达式
    private String updateOrganization;
    //定时更新test_batch表的cron表达式
    private String updateBatch;
	public String getDeviceInfoAdd() {
		return deviceInfoAdd;
	}

	public void setDeviceInfoAdd(String deviceInfoAdd) {
		this.deviceInfoAdd = deviceInfoAdd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getExecuteAdd() {
		return executeAdd;
	}

	public void setExecuteAdd(String executeAdd) {
		this.executeAdd = executeAdd;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getSvnConfFilesLocation() {
		return svnConfFilesLocation;
	}

	public void setSvnConfFilesLocation(String svnConfFilesLocation) {
		this.svnConfFilesLocation = svnConfFilesLocation;
	}

	public String getSvnServerUserName() {
		return svnServerUserName;
	}

	public void setSvnServerUserName(String svnServerUserName) {
		this.svnServerUserName = svnServerUserName;
	}

	public String getSvnServerPassword() {
		return svnServerPassword;
	}

	public void setSvnServerPassword(String svnServerPassword) {
		this.svnServerPassword = svnServerPassword;
	}
	
	public String getSvnRootUserName() {
		return svnRootUserName;
	}

	public void setSvnRootUserName(String svnRootUserName) {
		this.svnRootUserName = svnRootUserName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getBmtcURL() {
		return bmtcURL;
	}

	public void setBmtcURL(String bmtcURL) {
		this.bmtcURL = bmtcURL;
	}


	public String getQname1() {
		return Qname1;
	}

	public void setQname1(String qname1) {
		Qname1 = qname1;
	}

	public String getQname2() {
		return Qname2;
	}

	public void setQname2(String qname2) {
		Qname2 = qname2;
	}

	public String getUpdateOrganization() {
		return updateOrganization;
	}

	public void setUpdateOrganization(String updateOrganization) {
		this.updateOrganization = updateOrganization;
	}

	public String getUpdateBatch() {
		return updateBatch;
	}

	public void setUpdateBatch(String updateBatch) {
		this.updateBatch = updateBatch;
	}

	@Override
	public String toString() {
		return "BMTCConfig [uploadPath=" + uploadPath + ", deviceInfoAdd="
				+ deviceInfoAdd + ", executeAdd=" + executeAdd + ", url=" + url
				+ ", username=" + username + ", password=" + password
				+ ", localPath=" + localPath + ", uploadFile=" + uploadFile
				+ ", svnConfFilesLocation=" + svnConfFilesLocation
				+ ", svnServerUserName=" + svnServerUserName
				+ ", svnServerPassword=" + svnServerPassword
				+ ", svnRootUserName=" + svnRootUserName + ", port=" + port
				+ ", bmtcURL=" + bmtcURL + ", Qname1=" + Qname1 + ", Qname2="
				+ Qname2 + "]";
	}
	
}
