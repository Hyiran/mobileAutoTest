
package com.bmtc.wsdlATP.BMTC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TestType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="TestType">
 *   &lt;complexContent>
 *     &lt;extension base="{BMTC}BaseEntity">
 *       &lt;sequence>
 *         &lt;element name="TestTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestType", propOrder = {
    "testTypeName"
})
public class TestType
    extends BaseEntity
{

    @XmlElement(name = "TestTypeName")
    protected String testTypeName;

    /**
     * ��ȡtestTypeName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestTypeName() {
        return testTypeName;
    }

    /**
     * ����testTypeName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestTypeName(String value) {
        this.testTypeName = value;
    }

}
