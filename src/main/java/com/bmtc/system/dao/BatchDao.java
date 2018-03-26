package com.bmtc.system.dao;

import java.util.HashMap;
import java.util.List;

import com.bmtc.system.domain.BatchDO;

public interface BatchDao {
	
	//通过批次id获取批次对象
	BatchDO get(Long id);
	
	//查询批次数据
	List<BatchDO> list(HashMap<String, Object> map);
	
	//保存批次数据
	void save(BatchDO batch);
	
	//通过批次id移除批次对象
	int remove(Integer  batchId);
	
	//更新批次
	int update(BatchDO batch);
	
	//通过批次名获取批次对象
	BatchDO getBatch(String batchName);
	
	//移除临时表数据
	void removeTemp();
	
	//保存数据到临时表
	void saveTemp(BatchDO batch);
	
	//清除表数据
	void remove();
	
	//复制临时表数据到批次表
	void copyDate();

}
