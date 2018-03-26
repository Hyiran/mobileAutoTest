package com.bmtc.task.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bmtc.common.annotation.Log;
import com.bmtc.common.utils.R;
import com.bmtc.task.domain.ManualCaseDO;
import com.bmtc.task.service.ManualCaseService;

@Controller
@RequestMapping("/manualCase")
public class ManualCaseController {
	private static Logger logger = Logger.getLogger(ManualCaseController.class);
	
	@Autowired
	ManualCaseService manualCaseService;
	
	@GetMapping()
	@Log("跳转到手工案例列表界面")
	String manualCase() {
		logger.info("ManualCaseController.manualCase() start");
		logger.info("ManualCaseController.manualCase() end");
		return "/manualCase/manualCase";
	}
	
	/**
	 * 上传手工案例
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseBody
	R upload(@RequestParam(value = "uploadFile")MultipartFile file,Long taskId) {
		logger.info("ManualCaseController.upload() start");
		if (file == null) {
			return R.error("文件不能为空");
		}
		
		String  fileName = file.getOriginalFilename();
		if (!manualCaseService.validateExcel(fileName)) {
			return R.error("上传文件格式错误,请检查文件");
		}
		if (manualCaseService.save(file,taskId) == 1) {
			return R.error(1, "上传文件内容不符,请检查文件");
		}
		
		return R.ok();
	}
	
	/**
	 * 查询手工案例列表数据
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	@Log("查询测试案例")
	@ResponseBody()
	List<ManualCaseDO> list() {
		logger.info("ManualCaseController.list() start");
		List<ManualCaseDO> manualCase = manualCaseService.list();
		logger.info("ManualCaseController.list() end");
		return manualCase;
	}
	
	@Log("删除测试案例")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		logger.info("ManualCaseController.remove() start");
		if (manualCaseService.remove(id) > 0) {
			return R.ok();
		}
		logger.info("ManualCaseController.remove() end");
		return R.error();
	}

	@Log("批量删除测试案例")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		logger.info("ManualCaseController.batchRemove() start");
		int r = manualCaseService.batchremove(ids);
		if (r > 0) {
			logger.info("ManualCaseController.batchRemove() end");
			return R.ok();
		}
		logger.info("ManualCaseController.batchRemove() end");
		return R.error();
	}

}
