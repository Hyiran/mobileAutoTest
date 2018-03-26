package com.bmtc.task.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.bmtc.task.domain.ManualCaseDO;

/**
 * 手工案例Dao
 * @author nienannan
 *
 */
public interface ManualCaseDao {
	
	
	/**
	 * 保存手工案例到数据库
	 * @param manualCaseDO
	 */
	void save(ManualCaseDO manualCaseDO);
	
	/**
	 * 查询手工案例
	 * @return
	 */
	List<ManualCaseDO> list();
	
	/**
	 * 查询手工案例个数
	 * @return
	 */
	int count();
	
	/**
	 * 移除手工案例
	 * @param id
	 * @return
	 */
	int remove(Long id);
	
	/**
	 * 批量移除手工案例
	 * @param ids
	 * @return
	 */
	int batchremove(Long[] ids);
	
	/**
	 * 通过taskId移除手工案例
	 * @param taskId
	 * @return 
	 */
	int removeByTaskId(Long taskId);


	/**
	 * 通过taskID和CaseNum查询手工案例对象
	 * @param Long taskId, List<String> testCaseNum
	 */
	ManualCaseDO hasCaseNum(@Param("taskId") Long taskId, @Param("testCaseNumber") String testCaseNumber);

}
