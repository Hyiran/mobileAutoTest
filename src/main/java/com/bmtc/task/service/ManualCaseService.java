package com.bmtc.task.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bmtc.task.domain.ManualCaseDO;

/**
 * 手工案例service
 * @author nienannan
 *
 */
public interface ManualCaseService {
	
	/**
	 * 上传并且保存临时文件
	 * @param file
	 * @return 
	 */
	int save(MultipartFile file,Long taskId);
	
	/**
	 * 查询手工案例
	 * @return
	 */
	List<ManualCaseDO> list();
	
	/**
	 * 查询手工案例个数
	 * @param query
	 * @return
	 */
	int count();
	
	/**
	 * 通过手工案例id移除手工案例
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
	 * 校验Excel文件
	 * @param fileName
	 * @return
	 */
	boolean validateExcel(String fileName);
	
	/**
	 * 通过任务id移除手工案例
	 * @param id
	 * @return
	 */
	int removeByTaskId(Long id);

	/**
	 * 通过任务id移除手工案例
	 * @param Long taskId, String caseNume
	 * @return ManualCaseDO
	 */
	ManualCaseDO hasCaseNum(Long taskId, String caseNume);

}
