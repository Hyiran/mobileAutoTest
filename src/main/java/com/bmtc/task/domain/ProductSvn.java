package com.bmtc.task.domain;

import java.io.Serializable;

/**
 *@author: Jason.ma
 *@date: 2018年1月29日上午11:05:16
 *
 */
public class ProductSvn implements Serializable{

	private static final long serialVersionUID = -7197060102505395836L;
	//产品名称
	private String productName;
	//产品Id
	private Long productId;
	//产品svn仓库路径
	private String repository;
	//svn用户名
	private String username;
	//svn用户名密码
	private String password;
	//svn产品批次名称
	private String svnBatchName;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
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
	
	public String getSvnBatchName() {
		return svnBatchName;
	}
	public void setSvnBatchName(String svnBatchName) {
		this.svnBatchName = svnBatchName;
	}
	
	@Override
	public String toString() {
		return "ProductSvn [productName=" + productName + ", productId="
				+ productId + ", repository=" + repository + ", username="
				+ username + ", password=" + password + ", svnBatchName="
				+ svnBatchName + "]";
	}
	
}

