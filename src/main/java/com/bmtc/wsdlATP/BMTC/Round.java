
package com.bmtc.wsdlATP.BMTC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Round complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Round">
 *   &lt;complexContent>
 *     &lt;extension base="{BMTC}BaseEntity">
 *       &lt;sequence>
 *         &lt;element name="RoundName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestType" type="{BMTC}TestType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Round", propOrder = {
    "roundName",
    "description",
    "testType"
})
public class Round
    extends BaseEntity
{

    @XmlElement(name = "RoundName")
    protected String roundName;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "TestType")
    protected TestType testType;

    /**
     * ��ȡroundName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoundName() {
        return roundName;
    }

    /**
     * ����roundName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoundName(String value) {
        this.roundName = value;
    }

    /**
     * ��ȡdescription���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * ����description���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * ��ȡtestType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TestType }
     *     
     */
    public TestType getTestType() {
        return testType;
    }

    /**
     * ����testType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TestType }
     *     
     */
    public void setTestType(TestType value) {
        this.testType = value;
    }

}
