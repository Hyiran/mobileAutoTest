package com.bmtc.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bmtc.common.utils.Query;
import com.bmtc.system.domain.Actuator;

/**
 * 执行机管理的dao接口
 */
@Mapper
public interface ActuatorDao {

	//通过执行机id获取执行机对象
	Actuator get(Long id);
	
	//查询执行机数据
	List<Actuator> list(HashMap<String, Object> map);
	
	//保存执行机数据
	int save(Actuator actuator);
	
	//查询执行机总记录数
	int count(Query query);
	
	//通过执行机id移除执行机对象
	int remove(Long id);
	
	//修改执行机数据
	int update(Actuator actuator);
	
}
