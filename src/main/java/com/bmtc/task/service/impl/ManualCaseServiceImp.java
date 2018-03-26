package com.bmtc.task.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bmtc.common.config.BMTCConfig;
import com.bmtc.task.dao.ManualCaseDao;
import com.bmtc.task.domain.ManualCaseDO;
import com.bmtc.task.service.ManualCaseService;

/**
 * 手工案例service实现类
 * @author nienannan
 *
 */
@Service
public class ManualCaseServiceImp implements ManualCaseService{
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(ManualCaseServiceImp.class);
	
	@Autowired
	ManualCaseDao manualCaseMapper;
	
	@Autowired
	BMTCConfig bmtcConfig;
	
	/**
	 * 
	 * 获取上传的excel文件并将其保存到指定路径
	 * @return 
	 * 
	 */
	@Override
	public int save(MultipartFile file,Long taskId) {
		//获取Excel表名
		String fileName = file.getOriginalFilename();
		
		//创建一个路径用于保存临时文件
		File uploadDir = new File(bmtcConfig.getUploadFile());
		//File uploadDir = new File("E://fileupload/");
		//判断该目录是否存在
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		//新建一个文件
		File tempFile = new File(bmtcConfig.getUploadFile()+new Date().getTime()+".xlsx");
		//File tempFile = new File("E://fileupload/"+new Date().getTime()+".xlsx");
		 InputStream is = null;
		 try {
			 
			 //将上传的文件写入新建文件中
			file.transferTo(tempFile);
			
			is = new FileInputStream(tempFile);
			
			//根据版本创建workbook的方式
			Workbook wb = null;
			if (isExcel2007(fileName)) {
				wb = new XSSFWorkbook(is);
			}else{
				wb = new HSSFWorkbook(is);
			}
			
			//读取Excel文件信息
			return readExcelValue(wb,tempFile,taskId);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}finally{
			if (is != null) {
				try{
				is.close();
				}catch(IOException e){
					is = null;
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	
	/**
	 * 读取Excel文件并将其保存到数据库
	 * @param wb
	 * @param tempFile
	 * @return 
	 */
	@Transactional
	private int readExcelValue(Workbook wb,File tempFile,Long taskId) {
		
		//获取指定的sheet页
		Sheet sheet = wb.getSheetAt(0);
		if (sheet == null) {
			return 1;
		}
		//判断文件内容是否符合要求
		Row FirstRow = sheet.getRow(0);
		if (!"编写者".equals(FirstRow.getCell(0).getStringCellValue()) &&
				!"所属产品".equals(FirstRow.getCell(1).getStringCellValue()) &&
				!"模块".equals(FirstRow.getCell(2).getStringCellValue()) &&
				!"功能".equals(FirstRow.getCell(3).getStringCellValue())) { 
			return 1;
		}
		
		List<ManualCaseDO> manualCaseList = new ArrayList<ManualCaseDO>();
		 ManualCaseDO manualCaseDO;
		 
		 //遍历sheet页的行
		 for (int i = 1; i < sheet.getLastRowNum(); i++) {
			 
			 //获取相应的行信息
			 Row row = sheet.getRow(i);
			 
				 manualCaseDO = new ManualCaseDO();
				 
				 	//获取相应行的列信息
					Cell cell0 = row.getCell(0);
					if (null != cell0 ) {
						manualCaseDO.setAuthor(cell0.getStringCellValue());
					}
					
					Cell cell1 = row.getCell(1);
					if (cell1 != null ) {
						manualCaseDO.setAppurtenentProduce(cell1.getStringCellValue());
					}
					
					Cell cell2 = row.getCell(2);
					if (cell2 != null ) {
						manualCaseDO.setModule(cell2.getStringCellValue());
					}
					
					Cell cell3 = row.getCell(3);
					if (cell3 != null) {
						manualCaseDO.setFunction(cell3.getStringCellValue());
					}
					
					Cell cell4 = row.getCell(4);
					if (cell4 != null) {
						manualCaseDO.setSubfunction(cell4.getStringCellValue());
					}
					
					Cell cell5 = row.getCell(5);
					if (cell5 != null) {
						manualCaseDO.setTestType(cell5.getStringCellValue());
					}
					
					Cell cell6 = row.getCell(6);
					if (cell6 != null) {
						manualCaseDO.setCaseType(cell6.getStringCellValue());
					}
					
					Cell cell7 = row.getCell(7);
					if (cell7 != null) {
						cell7.setCellType(Cell.CELL_TYPE_STRING);
						manualCaseDO.setPriority(cell7.getStringCellValue());
					}
					
					Cell cell8 = row.getCell(8);
					if (cell8 != null) {
						cell8.setCellType(Cell.CELL_TYPE_STRING);
						manualCaseDO.setWeight(cell8.getStringCellValue());
					}
					
					Cell cell9 = row.getCell(9);
					if (cell9 != null) {
						manualCaseDO.setTestItem(cell9.getStringCellValue());
					}
					
					Cell cell10 = row.getCell(10);
					if (cell10 != null) {
						manualCaseDO.setTestPoint(cell10.getStringCellValue());
					}
					
					Cell cell11 = row.getCell(11);
					if (cell11 != null) {
						manualCaseDO.setTestCaseNumber(cell11.getStringCellValue());
					}
					
					Cell cell12 = row.getCell(12);
					if (cell12 != null) {
						manualCaseDO.setSystemInitialState(cell12.getStringCellValue());
					}
					
					Cell cell13 = row.getCell(13);
					if (cell13 != null) {
						manualCaseDO.setTestInstructions(cell13.getStringCellValue());
					}
					
					Cell cell14 = row.getCell(14);
					if (cell14 != null) {
						manualCaseDO.setExpectedResult(cell14.getStringCellValue());
					}
					
					Cell cell15 = row.getCell(15);
					if (cell15 != null) {
						manualCaseDO.setProductionTaskNumber(cell15.getStringCellValue());
					}
					
					Cell cell16 = row.getCell(16);
					if (cell16 != null) {
						manualCaseDO.setTestRequirements(cell16.getStringCellValue());
					}
					
					Cell cell17 = row.getCell(17);
					if (cell17 != null) {
						manualCaseDO.setOther(cell17.getStringCellValue());
					}
					
					Cell cell18 = row.getCell(18);
					if (cell18 != null) {
						manualCaseDO.setTester(cell18.getStringCellValue());
					}
					
					Cell cell19 = row.getCell(19);
					if (cell19 != null){
						manualCaseDO.setTestRounds(cell19.getStringCellValue());
					}
					
					Cell cell20 = row.getCell(20);
					if (cell20 != null) {
						cell20.setCellType(Cell.CELL_TYPE_STRING);
						manualCaseDO.setScheduledTestDate(cell20.getStringCellValue());
					}
					
					Cell cell21 = row.getCell(21);
					if (cell21 != null) {
						manualCaseDO.setCoreTimePointProperties(cell21.getStringCellValue());
					}
					
					Cell cell22 = row.getCell(22);
					if (cell22 != null) {
						manualCaseDO.setNotFunction(cell22.getStringCellValue());
					}
					
					Cell cell23 = row.getCell(23);
					if (cell23 != null) {
						manualCaseDO.setSpecialName(cell23.getStringCellValue());
					}
					
					Cell cell24 = row.getCell(24);
					if (cell24 != null) {
						manualCaseDO.setCustomIndex1(cell24.getStringCellValue());
					}
					
					Cell cell25 = row.getCell(25);
					if (cell25 != null) {
						manualCaseDO.setCustomIndex2(cell25.getStringCellValue());
					}
					
					Cell cell26 = row.getCell(26);
					if (cell26 != null) {
						manualCaseDO.setCustomIndex3(cell26.getStringCellValue());
					}
					
					Cell cell27 = row.getCell(27);
					if (cell27 != null) {
						manualCaseDO.setCustomIndex4(cell27.getStringCellValue());
					}
					
					Cell cell28 = row.getCell(28);
					if (cell28 != null) {
						manualCaseDO.setCustomIndex5(cell28.getStringCellValue());
					}
					
					Cell cell29 = row.getCell(29);
					if (cell29 != null) {
						manualCaseDO.setAutomatedTestScriptName(cell29.getStringCellValue());
					}
					
					Cell cell30 = row.getCell(30);
					if (cell30 != null) {
						manualCaseDO.setAutomatedTestExecutionOrder(cell30.getStringCellValue());
					}
					
					Cell cell31 = row.getCell(31);
					if (cell31 != null) {
						manualCaseDO.setUpstreamProducts(cell31.getStringCellValue());
					}
					
					Cell cell32 = row.getCell(32);
					if (cell32 != null ) {
						manualCaseDO.setDownstreamProducts(cell32.getStringCellValue());
					}
					
					Cell cell33 = row.getCell(33);
					if (cell33 != null) {
						manualCaseDO.setBatch(cell33.getStringCellValue());
					}
					
					
					Cell cell34 = row.getCell(34);
					if (cell34 != null) {
						manualCaseDO.setIfAutomated(cell34.getStringCellValue());
					}
					manualCaseList.add(manualCaseDO);
		 }
		 
		 //通过taskId清除对应的手工案例
		 	manualCaseMapper.removeByTaskId(taskId);
		 //遍历获取到的手工案例对象
		 for (ManualCaseDO manualCase : manualCaseList) {
			 //判断属性是否为null
			if (manualCase.getAuthor() != null || manualCase.getAppurtenentProduce() !=null ||
					manualCase.getTestCaseNumber() != null) {
				manualCase.setTaskId(taskId);
				manualCaseMapper.save(manualCase);
			}else{
				continue;
			}
		}
		 
		 //删除临时文件
		 if (tempFile.exists()) {
			 tempFile.delete();
		}
		 return 0;
	}
	
	
	/**
	 * 定义上传文件的版本
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2003(String fileName) {
		return fileName.matches("^.+\\.(?i)(xls)$");
	}
	
	public static boolean isExcel2007(String fileName) {
		return fileName.matches("^.+\\.(?i)(xlsx)$");
	}
	
	@Override
	public  boolean validateExcel(String fileName){
		if ( isExcel2003(fileName)) {
			return true;
		}
		if (isExcel2007(fileName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询手工案例信息
	 */
	@Override
	public List<ManualCaseDO> list() {
		List<ManualCaseDO> manualCaseDOs = manualCaseMapper.list();
		return manualCaseDOs;
	}
	
	/**
	 * 查询手工案例个数
	 */
	@Override
	public int count() {
		int count = manualCaseMapper.count();
		return count;
	}
	
	/**
	 * 通过id移除手工案例
	 */
	@Override
	public int remove(Long id) {
		int count = manualCaseMapper.remove(id);
		return count;
	}
	
	/**
	 * 通过id批量移除手工案例
	 */
	@Override
	public int batchremove(Long[] ids) {
		int count = manualCaseMapper.batchremove(ids);
		return count;
	}

	/**
	 * 通过taskId批量移除手工案例
	 */
	@Override
	public int removeByTaskId(Long id) {
		int count = manualCaseMapper.removeByTaskId(id);
		return count;
	}

	/**
	 * 判断是否存在caseNum
	 */
	@Override
	public ManualCaseDO hasCaseNum(Long taskId, String caseNume) {
		ManualCaseDO hasCaseNum = manualCaseMapper.hasCaseNum(taskId, caseNume);
		return hasCaseNum;
	}
}
