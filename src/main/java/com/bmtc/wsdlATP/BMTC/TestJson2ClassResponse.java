
package com.bmtc.wsdlATP.BMTC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="testJson2ClassResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testJson2ClassResult"
})
@XmlRootElement(name = "testJson2ClassResponse")
public class TestJson2ClassResponse {

    protected boolean testJson2ClassResult;

    /**
     * ��ȡtestJson2ClassResult���Ե�ֵ��
     * 
     */
    public boolean isTestJson2ClassResult() {
        return testJson2ClassResult;
    }

    /**
     * ����testJson2ClassResult���Ե�ֵ��
     * 
     */
    public void setTestJson2ClassResult(boolean value) {
        this.testJson2ClassResult = value;
    }

}
