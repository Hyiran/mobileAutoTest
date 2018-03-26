package com.bmtc.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bmtc.common.annotation.Log;
import com.bmtc.common.controller.BaseController;
import com.bmtc.common.utils.PageUtils;
import com.bmtc.common.utils.Query;
import com.bmtc.common.utils.R;
import com.bmtc.system.domain.Actuator;
import com.bmtc.system.service.ActuatorService;

/**
 * 执行机管理的Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/actuator")
public class ActuatorController extends BaseController{

	private static Logger logger = Logger.getLogger(ActuatorController.class);
	// 返回映射路径的前缀
	private String prefix = "system/actuator";
	@Autowired
	ActuatorService actuatorService;
	
	/**
	 * 前段请求访问执行机管理列表页面
	 * @return String
	 */
	@RequiresPermissions("test:actuator:actuator")
	@GetMapping()
	String actuator() {
		logger.info("ActuatorController.actuator() start");
		logger.info("ActuatorController.actuator() end");
		return prefix + "/actuator";
	}
	/**
	 * 查询执行机数据
	 * @param params
	 * @return PageUtils
	 */
	@GetMapping("/list")
	@RequiresPermissions("test:actuator:list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		logger.info("ActuatorController.list() start");
		// 分页查询参数数据
		Query query = new Query(params);
		// 查询执行机数据
		List<Actuator> list = actuatorService.list(query);
		// 查询总记录数
		int total = actuatorService.count(query);
		// 封装到返回对象
		PageUtils pageUtil = new PageUtils(list, total);
		
		logger.info("ActuatorController.list() end");
		
		// 返回数据
		return pageUtil;
	}
	/**
	 * 执行机add页面跳转
	 * @param 
	 * @return String
	 */
	@RequiresPermissions("test:actuator:add")
	@Log("添加执行机")
	@GetMapping("/add")
	String add() {
		logger.info("ActuatorController.add() start");
		logger.info("ActuatorController.add() end");
		return prefix + "/add";
	}
	
	/**
	 * 保存执行机
	 * @param Actuator actuator
	 * @return R
	 */
	@RequiresPermissions("test:actuator:add")
	@Log("保存执行机")
	@PostMapping("/save")
	@ResponseBody
	R save(Actuator actuator) {
		logger.info("ActuatorController.save() start");
		R save = actuatorService.save(actuator);
		logger.info("ActuatorController.save() end");
		return save;
	}
	/**
	 * 执行机edit页面跳转
	 * @param Long id
	 * @return String
	 */
	@RequiresPermissions("test:actuator:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		logger.info("ActuatorController.edit() start");
		// 通过id获得执行机对象
		Actuator actuator = actuatorService.get(id);
		model.addAttribute("actuator", actuator);
		logger.info("ActuatorController.edit() end");
		return prefix+"/edit";
	}
	/**
	 * 执行机修改
	 * @param Actuator actuator
	 * @return R
	 */
	@RequiresPermissions("test:actuator:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(Actuator actuator) {
		logger.info("ActuatorController.update() start");
		R update = actuatorService.update(actuator);
		logger.info("ActuatorController.update() end");
		return update;
	}
	/**
	 * 执行机删除
	 * @param id
	 * @return R
	 */
	@RequiresPermissions("test:actuator:remove")
	@Log("删除执行机")
	@PostMapping("/remove")
	@ResponseBody
	R remove(@RequestParam("id") Long id) {
		logger.info("ActuatorController.remove() start");
		R remove = actuatorService.remove(id);
		return remove;
	}
}
