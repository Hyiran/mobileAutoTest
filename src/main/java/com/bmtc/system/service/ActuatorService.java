package com.bmtc.system.service;

import java.util.List;

import com.bmtc.common.utils.Query;
import com.bmtc.common.utils.R;
import com.bmtc.system.domain.Actuator;

/**
 * 执行机管理的service接口
 */
public interface ActuatorService {

	// 查询执行机数据
	List<Actuator> list(Query query);
	// 查询总记录数
	int count(Query query);
	// 保存执行机
	R save(Actuator actuator);
	// 通过ID获取执行机对象
	Actuator get(Long id);
	// 执行机修改
	R update(Actuator actuator);
	// 删除执行机
	R remove(Long id);

}
