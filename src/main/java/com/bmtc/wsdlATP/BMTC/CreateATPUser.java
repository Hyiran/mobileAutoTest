
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
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="teamID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="produceID" type="{BMTC}ArrayOfInt" minOccurs="0"/>
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
    "userName",
    "loginName",
    "password",
    "departmentID",
    "teamID",
    "produceID"
})
@XmlRootElement(name = "CreateATPUser")
public class CreateATPUser {

    protected String userName;
    protected String loginName;
    protected String password;
    protected int departmentID;
    protected int teamID;
    protected ArrayOfInt produceID;

    /**
     * ��ȡuserName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ����userName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * ��ȡloginName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * ����loginName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginName(String value) {
        this.loginName = value;
    }

    /**
     * ��ȡpassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * ����password���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * ��ȡdepartmentID���Ե�ֵ��
     * 
     */
    public int getDepartmentID() {
        return departmentID;
    }

    /**
     * ����departmentID���Ե�ֵ��
     * 
     */
    public void setDepartmentID(int value) {
        this.departmentID = value;
    }

    /**
     * ��ȡteamID���Ե�ֵ��
     * 
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * ����teamID���Ե�ֵ��
     * 
     */
    public void setTeamID(int value) {
        this.teamID = value;
    }

    /**
     * ��ȡproduceID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getProduceID() {
        return produceID;
    }

    /**
     * ����produceID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setProduceID(ArrayOfInt value) {
        this.produceID = value;
    }

}
