
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
 *         &lt;element name="GetUserProductsResult" type="{BMTC}ArrayOfOrganization" minOccurs="0"/>
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
    "getUserProductsResult"
})
@XmlRootElement(name = "GetUserProductsResponse")
public class GetUserProductsResponse {

    @XmlElement(name = "GetUserProductsResult")
    protected ArrayOfOrganization getUserProductsResult;

    /**
     * ��ȡgetUserProductsResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrganization }
     *     
     */
    public ArrayOfOrganization getGetUserProductsResult() {
        return getUserProductsResult;
    }

    /**
     * ����getUserProductsResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrganization }
     *     
     */
    public void setGetUserProductsResult(ArrayOfOrganization value) {
        this.getUserProductsResult = value;
    }

}
