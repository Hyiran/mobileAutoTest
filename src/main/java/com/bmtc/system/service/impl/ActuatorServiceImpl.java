package com.bmtc.system.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmtc.common.utils.Query;
import com.bmtc.common.utils.R;
import com.bmtc.system.dao.ActuatorDao;
import com.bmtc.system.domain.Actuator;
import com.bmtc.system.service.ActuatorService;
/**
 * 执行机管理的service实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class ActuatorServiceImpl implements ActuatorService {

	private static Logger logger = Logger.getLogger(ActuatorServiceImpl.class);
	@Autowired
	ActuatorDao actuatorMapper;

	/**
	 * 查询执行机数据
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<Actuator> list(Query query) {
		logger.info("ActuatorServiceImpl.list() start");
		List<Actuator> list = actuatorMapper.list(query);
		logger.info("ActuatorServiceImpl.list() end");
		return list;
	}
	/**
	 * 查询总记录数
	 * 
	 * @param query
	 * @return int
	 */
	@Override
	public int count(Query query) {
		logger.info("ActuatorServiceImpl.count() start");
		int count = actuatorMapper.count(query);
		logger.info("ActuatorServiceImpl.count() end");
		return count;
	}
	/**
	 * 保存执行机
	 * 
	 * @param actuator
	 * @return int
	 */
	@Override
	public R save(Actuator actuator) {
		logger.info("ActuatorServiceImpl.save() start");
		int save = actuatorMapper.save(actuator);
		if(save <= 0) {
			logger.info("ActuatorServiceImpl.save() end");
			return R.error("保存失败");
		}
		logger.info("ActuatorServiceImpl.save() end");
		return R.ok("保存成功");
	}
	/**
	 * 通过ID获取执行机对象
	 * 
	 * @param Long id
	 * @return Actuator actuator
	 */
	@Override
	public Actuator get(Long id) {
		logger.info("ActuatorServiceImpl.get() start");
		Actuator actuator = actuatorMapper.get(id);
		logger.info("ActuatorServiceImpl.get() end");
		return actuator;
	}
	/**
	 * 执行机修改
	 * 
	 * @param Actuator actuator
	 * @return R
	 */
	@Override
	public R update(Actuator actuator) {
		logger.info("ActuatorServiceImpl.update() start");
		int update = actuatorMapper.update(actuator);
		if(update <= 0) {
			logger.info("ActuatorServiceImpl.update() end");
			return R.error("修改失败");
		}
		logger.info("ActuatorServiceImpl.update() end");
		return R.ok("修改成功");
	}
	/**
	 * 执行机删除
	 * 
	 * @param Long id
	 * @return R
	 */
	@Override
	public R remove(Long id) {
		logger.info("ActuatorServiceImpl.remove() start");
		int remove = actuatorMapper.remove(id);
		if(remove <= 0) {
			logger.info("ActuatorServiceImpl.update() end");
			return R.error("删除失败");
		}
		logger.info("ActuatorServiceImpl.remove() end");
		return R.ok("删除成功");
	}
	
}