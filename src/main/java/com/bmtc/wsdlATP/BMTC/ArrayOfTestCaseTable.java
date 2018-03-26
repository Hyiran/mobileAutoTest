
package com.bmtc.wsdlATP.BMTC;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.bmtc.device.domain.TestCaseTable;


/**
 * <p>ArrayOfTestCaseTable complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTestCaseTable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TestCaseTable" type="{BMTC}TestCaseTable" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTestCaseTable", propOrder = {
    "testCaseTable"
})
public class ArrayOfTestCaseTable {

    @XmlElement(name = "TestCaseTable", nillable = true)
    protected List<TestCaseTable> testCaseTable;

    /**
     * Gets the value of the testCaseTable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testCaseTable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestCaseTable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestCaseTable }
     * 
     * 
     */
    public List<TestCaseTable> getTestCaseTable() {
        if (testCaseTable == null) {
            testCaseTable = new ArrayList<TestCaseTable>();
        }
        return this.testCaseTable;
    }

}
