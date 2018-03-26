
package com.bmtc.wsdlATP.BMTC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>BaseEntity complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BaseEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreateUser" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ModifyDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ModifyUser" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseEntity", propOrder = {
    "id",
    "createUser",
    "createDate",
    "deleted",
    "modifyDate",
    "modifyUser"
})
@XmlSeeAlso({
    Organization.class,
    Round.class,
    TestType.class,
    BaseUserInfo.class
})
public class BaseEntity {

    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "CreateUser")
    protected int createUser;
    @XmlElement(name = "CreateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    @XmlElement(name = "Deleted")
    protected boolean deleted;
    @XmlElement(name = "ModifyDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifyDate;
    @XmlElement(name = "ModifyUser")
    protected int modifyUser;

    /**
     * ��ȡid���Ե�ֵ��
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

    /**
     * ��ȡcreateUser���Ե�ֵ��
     * 
     */
    public int getCreateUser() {
        return createUser;
    }

    /**
     * ����createUser���Ե�ֵ��
     * 
     */
    public void setCreateUser(int value) {
        this.createUser = value;
    }

    /**
     * ��ȡcreateDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * ����createDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * ��ȡdeleted���Ե�ֵ��
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * ����deleted���Ե�ֵ��
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    /**
     * ��ȡmodifyDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifyDate() {
        return modifyDate;
    }

    /**
     * ����modifyDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifyDate(XMLGregorianCalendar value) {
        this.modifyDate = value;
    }

    /**
     * ��ȡmodifyUser���Ե�ֵ��
     * 
     */
    public int getModifyUser() {
        return modifyUser;
    }

    /**
     * ����modifyUser���Ե�ֵ��
     * 
     */
    public void setModifyUser(int value) {
        this.modifyUser = value;
    }

}
