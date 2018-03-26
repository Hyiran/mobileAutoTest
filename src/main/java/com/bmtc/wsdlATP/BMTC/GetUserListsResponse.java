
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
 *         &lt;element name="GetUserListsResult" type="{BMTC}ArrayOfUserInfo" minOccurs="0"/>
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
    "getUserListsResult"
})
@XmlRootElement(name = "GetUserListsResponse")
public class GetUserListsResponse {

    @XmlElement(name = "GetUserListsResult")
    protected ArrayOfUserInfo getUserListsResult;

    /**
     * ��ȡgetUserListsResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUserInfo }
     *     
     */
    public ArrayOfUserInfo getGetUserListsResult() {
        return getUserListsResult;
    }

    /**
     * ����getUserListsResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUserInfo }
     *     
     */
    public void setGetUserListsResult(ArrayOfUserInfo value) {
        this.getUserListsResult = value;
    }

}
