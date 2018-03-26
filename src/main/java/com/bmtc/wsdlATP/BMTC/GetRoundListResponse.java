
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
 *         &lt;element name="GetRoundListResult" type="{BMTC}ArrayOfRound" minOccurs="0"/>
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
    "getRoundListResult"
})
@XmlRootElement(name = "GetRoundListResponse")
public class GetRoundListResponse {

    @XmlElement(name = "GetRoundListResult")
    protected ArrayOfRound getRoundListResult;

    /**
     * ��ȡgetRoundListResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRound }
     *     
     */
    public ArrayOfRound getGetRoundListResult() {
        return getRoundListResult;
    }

    /**
     * ����getRoundListResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRound }
     *     
     */
    public void setGetRoundListResult(ArrayOfRound value) {
        this.getRoundListResult = value;
    }

}
