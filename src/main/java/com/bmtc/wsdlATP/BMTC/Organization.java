
package com.bmtc.wsdlATP.BMTC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Organization complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Organization">
 *   &lt;complexContent>
 *     &lt;extension base="{BMTC}BaseEntity">
 *       &lt;sequence>
 *         &lt;element name="OrganName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrganLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Findkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OrganType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Parent" type="{BMTC}Organization" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organization", propOrder = {
    "organName",
    "organLevel",
    "findkey",
    "sequence",
    "organType",
    "parent"
})
public class Organization
    extends BaseEntity
{

    @XmlElement(name = "OrganName")
    protected String organName;
    @XmlElement(name = "OrganLevel")
    protected int organLevel;
    @XmlElement(name = "Findkey")
    protected String findkey;
    @XmlElement(name = "Sequence")
    protected int sequence;
    @XmlElement(name = "OrganType")
    protected int organType;
    @XmlElement(name = "Parent")
    protected Organization parent;

    /**
     * ��ȡorganName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganName() {
        return organName;
    }

    /**
     * ����organName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganName(String value) {
        this.organName = value;
    }

    /**
     * ��ȡorganLevel���Ե�ֵ��
     * 
     */
    public int getOrganLevel() {
        return organLevel;
    }

    /**
     * ����organLevel���Ե�ֵ��
     * 
     */
    public void setOrganLevel(int value) {
        this.organLevel = value;
    }

    /**
     * ��ȡfindkey���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFindkey() {
        return findkey;
    }

    /**
     * ����findkey���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFindkey(String value) {
        this.findkey = value;
    }

    /**
     * ��ȡsequence���Ե�ֵ��
     * 
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * ����sequence���Ե�ֵ��
     * 
     */
    public void setSequence(int value) {
        this.sequence = value;
    }

    /**
     * ��ȡorganType���Ե�ֵ��
     * 
     */
    public int getOrganType() {
        return organType;
    }

    /**
     * ����organType���Ե�ֵ��
     * 
     */
    public void setOrganType(int value) {
        this.organType = value;
    }

    /**
     * ��ȡparent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Organization }
     *     
     */
    public Organization getParent() {
        return parent;
    }

    /**
     * ����parent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Organization }
     *     
     */
    public void setParent(Organization value) {
        this.parent = value;
    }

}
