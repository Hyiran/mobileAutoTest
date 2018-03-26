
package com.bmtc.wsdlATP.BMTC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="UploadATPTasksResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "uploadATPTasksResult"
})
@XmlRootElement(name = "UploadATPTasksResponse")
public class UploadATPTasksResponse {

    @XmlElement(name = "UploadATPTasksResult")
    protected boolean uploadATPTasksResult;

    /**
     * ��ȡuploadATPTasksResult���Ե�ֵ��
     * 
     */
    public boolean isUploadATPTasksResult() {
        return uploadATPTasksResult;
    }

    /**
     * ����uploadATPTasksResult���Ե�ֵ��
     * 
     */
    public void setUploadATPTasksResult(boolean value) {
        this.uploadATPTasksResult = value;
    }

}
