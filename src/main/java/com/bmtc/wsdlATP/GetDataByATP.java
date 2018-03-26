package com.bmtc.wsdlATP;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bmtc.common.config.BMTCConfig;
import com.bmtc.wsdlATP.BMTC.BMTCSoap;
@Component
public class GetDataByATP {
	private static final Logger logger = LoggerFactory
			.getLogger(GetDataByATP.class);

	
	@Autowired
	BMTCConfig bmtcConfig;
	
	/**
	 * 从ATP获取数据
	 * @param dataType
	 * @return
	 * @throws MalformedURLException 
	 * @throws Exception
	 */
	
	public BMTCSoap  getData() throws MalformedURLException{
		logger.info("GetDataByATP.getData() start");
		URL url = new URL(bmtcConfig.getBmtcURL());
		QName qName = new QName(bmtcConfig.getQname1(),bmtcConfig.getQname2());
		Service service = Service.create(url,qName);
		BMTCSoap bmtcSoap = service.getPort(BMTCSoap.class);
		logger.info("GetDataByATP.getData() end");
		return bmtcSoap;
		
	}
	
	
	
}