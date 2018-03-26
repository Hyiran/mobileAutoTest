
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
 *         &lt;element name="batchID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jsonCaseList" type="{BMTC}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scriptSVNPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repository" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "batchID",
    "productID",
    "jsonCaseList",
    "userID",
    "scriptSVNPath",
    "productName",
    "repository",
    "username",
    "password"
})
@XmlRootElement(name = "SaveATPCases")
public class SaveATPCases {

    protected int batchID;
    protected int productID;
    protected ArrayOfString jsonCaseList;
    protected int userID;
    protected String scriptSVNPath;
    protected String productName;
    protected String repository;
    protected String username;
    protected String password;

    /**
     * ��ȡbatchID���Ե�ֵ��
     * 
     */
    public int getBatchID() {
        return batchID;
    }

    /**
     * ����batchID���Ե�ֵ��
     * 
     */
    public void setBatchID(int value) {
        this.batchID = value;
    }

    /**
     * ��ȡproductID���Ե�ֵ��
     * 
     */
    public int getProductID() {
        return productID;
    }

    /**
     * ����productID���Ե�ֵ��
     * 
     */
    public void setProductID(int value) {
        this.productID = value;
    }

    /**
     * ��ȡjsonCaseList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getJsonCaseList() {
        return jsonCaseList;
    }

    /**
     * ����jsonCaseList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setJsonCaseList(ArrayOfString value) {
        this.jsonCaseList = value;
    }

    /**
     * ��ȡuserID���Ե�ֵ��
     * 
     */
    public int getUserID() {
        return userID;
    }

    /**
     * ����userID���Ե�ֵ��
     * 
     */
    public void setUserID(int value) {
        this.userID = value;
    }

    /**
     * ��ȡscriptSVNPath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScriptSVNPath() {
        return scriptSVNPath;
    }

    /**
     * ����scriptSVNPath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScriptSVNPath(String value) {
        this.scriptSVNPath = value;
    }

    /**
     * ��ȡproductName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * ����productName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * ��ȡrepository���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepository() {
        return repository;
    }

    /**
     * ����repository���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepository(String value) {
        this.repository = value;
    }

    /**
     * ��ȡusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * ����username���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
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

}
