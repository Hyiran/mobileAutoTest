package com.bmtc.task.domain;

import java.io.Serializable;
/**
 * 手工案例
 * @author nienannan
 *
 */
public class ManualCaseDO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String author; //编写者
	
	private String appurtenentProduce; //所属产品
	
	private String module; //模块
	
	private String function; //功能
	
	private String subfunction; //子功能
	
	private String testType; //测试类型
	
	private String caseType; //案例类型
	
	private String priority; //优先级
	
	private String weight; //权重
	
	private String testItem; //测试项
	
	private String testPoint; //测试点
	
	private String testCaseNumber; //测试案例编号
	
	private String systemInitialState;//系统的初始状态和初始数据要求
	
	private String testInstructions; //测试操作说明
	
	private String expectedResult; //预期结果
	
	private String productionTaskNumber;//生产任务编号
	
	private String testRequirements; //测试需求项
	
	private String other; //其他
	
	private String tester; //测试人员
	
	private String testRounds; //测试轮次
	
	private String scheduledTestDate;//计划测试日期;
	
	private String coreTimePointProperties; //核心时间点属性
	
	private String notFunction;//非功能
	
	private String specialName;//专题名称
	
	private String customIndex1;//自定义索引1
	
	private String customIndex2;//自定义索引2
	
	private String customIndex3;//自定义索引3
	
	private String customIndex4;//自定义索引4
	
	private String customIndex5;//自定义索引5
	
	private String automatedTestScriptName;//自动化测试脚本名称
	
	private String automatedTestExecutionOrder;//自动化测试执行次序
	
	private String upstreamProducts; //上游产品
	
	private String downstreamProducts;//下游产品
	
	private String batch; //批次
	
	private String ifAutomated; //是否为自动化
	
	private Long taskId;//任务id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAppurtenentProduce() {
		return appurtenentProduce;
	}

	public void setAppurtenentProduce(String appurtenentProduce) {
		this.appurtenentProduce = appurtenentProduce;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getSubfunction() {
		return subfunction;
	}

	public void setSubfunction(String subfunction) {
		this.subfunction = subfunction;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}



	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getTestItem() {
		return testItem;
	}

	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}

	public String getTestPoint() {
		return testPoint;
	}

	public void setTestPoint(String testPoint) {
		this.testPoint = testPoint;
	}

	public String getTestCaseNumber() {
		return testCaseNumber;
	}

	public void setTestCaseNumber(String testCaseNumber) {
		this.testCaseNumber = testCaseNumber;
	}

	public String getSystemInitialState() {
		return systemInitialState;
	}

	public void setSystemInitialState(String systemInitialState) {
		this.systemInitialState = systemInitialState;
	}

	public String getTestInstructions() {
		return testInstructions;
	}

	public void setTestInstructions(String testInstructions) {
		this.testInstructions = testInstructions;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getProductionTaskNumber() {
		return productionTaskNumber;
	}

	public void setProductionTaskNumber(String productionTaskNumber) {
		this.productionTaskNumber = productionTaskNumber;
	}

	public String getTestRequirements() {
		return testRequirements;
	}

	public void setTestRequirements(String testRequirements) {
		this.testRequirements = testRequirements;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getTestRounds() {
		return testRounds;
	}

	public void setTestRounds(String testRounds) {
		this.testRounds = testRounds;
	}

	public String getScheduledTestDate() {
		return scheduledTestDate;
	}

	public void setScheduledTestDate(String scheduledTestDate) {
		this.scheduledTestDate = scheduledTestDate;
	}

	public String getCoreTimePointProperties() {
		return coreTimePointProperties;
	}

	public void setCoreTimePointProperties(String coreTimePointProperties) {
		this.coreTimePointProperties = coreTimePointProperties;
	}

	public String getNotFunction() {
		return notFunction;
	}

	public void setNotFunction(String notFunction) {
		this.notFunction = notFunction;
	}

	public String getSpecialName() {
		return specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public String getCustomIndex1() {
		return customIndex1;
	}

	public void setCustomIndex1(String customIndex1) {
		this.customIndex1 = customIndex1;
	}

	public String getCustomIndex2() {
		return customIndex2;
	}

	public void setCustomIndex2(String customIndex2) {
		this.customIndex2 = customIndex2;
	}

	public String getCustomIndex3() {
		return customIndex3;
	}

	public void setCustomIndex3(String customIndex3) {
		this.customIndex3 = customIndex3;
	}

	public String getCustomIndex4() {
		return customIndex4;
	}

	public void setCustomIndex4(String customIndex4) {
		this.customIndex4 = customIndex4;
	}

	public String getCustomIndex5() {
		return customIndex5;
	}

	public void setCustomIndex5(String customIndex5) {
		this.customIndex5 = customIndex5;
	}

	public String getAutomatedTestScriptName() {
		return automatedTestScriptName;
	}

	public void setAutomatedTestScriptName(String automatedTestScriptName) {
		this.automatedTestScriptName = automatedTestScriptName;
	}

	public String getAutomatedTestExecutionOrder() {
		return automatedTestExecutionOrder;
	}

	public void setAutomatedTestExecutionOrder(String automatedTestExecutionOrder) {
		this.automatedTestExecutionOrder = automatedTestExecutionOrder;
	}

	public String getUpstreamProducts() {
		return upstreamProducts;
	}

	public void setUpstreamProducts(String upstreamProducts) {
		this.upstreamProducts = upstreamProducts;
	}

	public String getDownstreamProducts() {
		return downstreamProducts;
	}

	public void setDownstreamProducts(String downstreamProducts) {
		this.downstreamProducts = downstreamProducts;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getIfAutomated() {
		return ifAutomated;
	}

	public void setIfAutomated(String ifAutomated) {
		this.ifAutomated = ifAutomated;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Override
	public String toString() {
		return "ManualCaseDO [id=" + id + ", author=" + author
				+ ", appurtenentProduce=" + appurtenentProduce + ", module="
				+ module + ", function=" + function + ", subfunction="
				+ subfunction + ", testType=" + testType + ", caseType="
				+ caseType + ", priority=" + priority + ", weight=" + weight
				+ ", testItem=" + testItem + ", testPoint=" + testPoint
				+ ", testCaseNumber=" + testCaseNumber
				+ ", systemInitialState=" + systemInitialState
				+ ", testInstructions=" + testInstructions
				+ ", expectedResult=" + expectedResult
				+ ", productionTaskNumber=" + productionTaskNumber
				+ ", testRequirements=" + testRequirements + ", other=" + other
				+ ", tester=" + tester + ", testRounds=" + testRounds
				+ ", scheduledTestDate=" + scheduledTestDate
				+ ", coreTimePointProperties=" + coreTimePointProperties
				+ ", notFunction=" + notFunction + ", specialName="
				+ specialName + ", customIndex1=" + customIndex1
				+ ", customIndex2=" + customIndex2 + ", customIndex3="
				+ customIndex3 + ", customIndex4=" + customIndex4
				+ ", customIndex5=" + customIndex5
				+ ", automatedTestScriptName=" + automatedTestScriptName
				+ ", automatedTestExecutionOrder="
				+ automatedTestExecutionOrder + ", upstreamProducts="
				+ upstreamProducts + ", downstreamProducts="
				+ downstreamProducts + ", batch=" + batch + ", ifAutomated="
				+ ifAutomated + ", taskId=" + taskId + "]";
	}
	
	

}
