package com.bmtc.task.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bmtc.common.utils.Query;
import com.bmtc.task.domain.ExecutePlan;

/**
 * 测试任务执行计划的Dao接口
 * @author Administrator
 *
 */
@Mapper
public interface ExecutePlanDao {

	// 查询执行计划列表数据
	List<ExecutePlan> list(Map<String,Object> map);
	// 查询执行计划总记录数
	int count(Query query);
	// 通过ID查询执行计划
	ExecutePlan get(Long id);
	// 查询指定条件的执行计划总记录数
	int count(Map<String,Object> map);
	// 保存执行计划
	int save(ExecutePlan executePlan);
	// 修改执行计划
	int update(ExecutePlan executePlan);
	// 删除执行计划
	int remove(@Param("isDeleted")Long isDeleted, @Param("id")Long id);
	// 批量删除执行计划
	int batchRemove(@Param("isDeleted")Long isDeleted, @Param("ids")Long[] ids);
	// 通过taskId查询执行计划
	List<ExecutePlan> getExecutePlanByTaskId(Long taskId);
	
}
