
package com.bmtc.wsdlATP.BMTC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>BaseUserInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="BaseUserInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{BMTC}BaseEntity">
 *       &lt;sequence>
 *         &lt;element name="LoginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DBEnable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LastLoginDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IsAllPermission" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsLogin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RoleID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DelFlag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OrgCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DeptID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="goToUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseUserInfo", propOrder = {
    "loginName",
    "password",
    "userName",
    "phoneNumber",
    "mailAddress",
    "dbEnable",
    "lastLoginDate",
    "isAllPermission",
    "isLogin",
    "roleID",
    "delFlag",
    "orgCode",
    "deptID",
    "groupID",
    "enabled",
    "goToUrl"
})
@XmlSeeAlso({
    UserInfo.class
})
public class BaseUserInfo
    extends BaseEntity
{

    @XmlElement(name = "LoginName")
    protected String loginName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "MailAddress")
    protected String mailAddress;
    @XmlElement(name = "DBEnable", required = true, type = Boolean.class, nillable = true)
    protected Boolean dbEnable;
    @XmlElement(name = "LastLoginDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastLoginDate;
    @XmlElement(name = "IsAllPermission")
    protected boolean isAllPermission;
    @XmlElement(name = "IsLogin")
    protected boolean isLogin;
    @XmlElement(name = "RoleID")
    protected int roleID;
    @XmlElement(name = "DelFlag")
    protected int delFlag;
    @XmlElement(name = "OrgCode")
    protected int orgCode;
    @XmlElement(name = "DeptID")
    protected int deptID;
    @XmlElement(name = "GroupID")
    protected int groupID;
    @XmlElement(name = "Enabled")
    protected boolean enabled;
    protected String goToUrl;

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
     * ��ȡphoneNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * ����phoneNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * ��ȡmailAddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * ����mailAddress���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailAddress(String value) {
        this.mailAddress = value;
    }

    /**
     * ��ȡdbEnable���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDBEnable() {
        return dbEnable;
    }

    /**
     * ����dbEnable���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDBEnable(Boolean value) {
        this.dbEnable = value;
    }

    /**
     * ��ȡlastLoginDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * ����lastLoginDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastLoginDate(XMLGregorianCalendar value) {
        this.lastLoginDate = value;
    }

    /**
     * ��ȡisAllPermission���Ե�ֵ��
     * 
     */
    public boolean isIsAllPermission() {
        return isAllPermission;
    }

    /**
     * ����isAllPermission���Ե�ֵ��
     * 
     */
    public void setIsAllPermission(boolean value) {
        this.isAllPermission = value;
    }

    /**
     * ��ȡisLogin���Ե�ֵ��
     * 
     */
    public boolean isIsLogin() {
        return isLogin;
    }

    /**
     * ����isLogin���Ե�ֵ��
     * 
     */
    public void setIsLogin(boolean value) {
        this.isLogin = value;
    }

    /**
     * ��ȡroleID���Ե�ֵ��
     * 
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * ����roleID���Ե�ֵ��
     * 
     */
    public void setRoleID(int value) {
        this.roleID = value;
    }

    /**
     * ��ȡdelFlag���Ե�ֵ��
     * 
     */
    public int getDelFlag() {
        return delFlag;
    }

    /**
     * ����delFlag���Ե�ֵ��
     * 
     */
    public void setDelFlag(int value) {
        this.delFlag = value;
    }

    /**
     * ��ȡorgCode���Ե�ֵ��
     * 
     */
    public int getOrgCode() {
        return orgCode;
    }

    /**
     * ����orgCode���Ե�ֵ��
     * 
     */
    public void setOrgCode(int value) {
        this.orgCode = value;
    }

    /**
     * ��ȡdeptID���Ե�ֵ��
     * 
     */
    public int getDeptID() {
        return deptID;
    }

    /**
     * ����deptID���Ե�ֵ��
     * 
     */
    public void setDeptID(int value) {
        this.deptID = value;
    }

    /**
     * ��ȡgroupID���Ե�ֵ��
     * 
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * ����groupID���Ե�ֵ��
     * 
     */
    public void setGroupID(int value) {
        this.groupID = value;
    }

    /**
     * ��ȡenabled���Ե�ֵ��
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * ����enabled���Ե�ֵ��
     * 
     */
    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    /**
     * ��ȡgoToUrl���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoToUrl() {
        return goToUrl;
    }

    /**
     * ����goToUrl���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoToUrl(String value) {
        this.goToUrl = value;
    }

}
