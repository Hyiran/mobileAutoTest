package com.bmtc.task.domain;

import java.io.Serializable;

/**
 * 测试任务的执行计划实体类
 * @author Administrator
 *
 */
public class ExecutePlan implements Serializable{

	/**
	 * 属性
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private Long id;
	// 脚本库路径
	private String svnPath;
	// 产品机构id
	private Long deptId;
	// 产品机构名称
	private String deptName;
	// 测试任务名称
	private String taskName;
	// 测试任务id
	private Long taskId;
	// 所属批次名称
	private String batchName;
	// 所属批次id
	private Long batchId;
	// 触发方式字段
	private String triggerMode;
	// 触发条件字段
	private String condition;
	// 执行设备:(1，Android，2，IOS)
	private String deviceType;
	// 状态(逻辑删除标记:1,存在；0:删除)
	private Long isDeleted;
	// 执行状态:0,空闲;1,执行中
	private Long status;
	// 关联测试套路径集
	private String testSuiteCaseNames;
	/**
	 * 构造
	 */
	public ExecutePlan() {
		super();
	}
	/**
	 * set&get
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSvnPath() {
		return svnPath;
	}
	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public String getTriggerMode() {
		return triggerMode;
	}
	public void setTriggerMode(String triggerMode) {
		this.triggerMode = triggerMode;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getTestSuiteCaseNames() {
		return testSuiteCaseNames;
	}
	public void setTestSuiteCaseNames(String testSuiteCaseNames) {
		this.testSuiteCaseNames = testSuiteCaseNames;
	}
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "ExecutePlan [id=" + id + ", svnPath=" + svnPath + ", deptId="
				+ deptId + ", deptName=" + deptName + ", taskName=" + taskName
				+ ", taskId=" + taskId + ", batchName=" + batchName
				+ ", batchId=" + batchId + ", triggerMode=" + triggerMode
				+ ", condition=" + condition + ", deviceType=" + deviceType
				+ ", status=" + status + "]";
	}
}
