//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.15 at 02:29:41 PM CEST 
//


package com.example.applicationsoap.soapmodel.usermodel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accessLevel_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="accessLevel_Type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Administrator"/&gt;
 *     &lt;enumeration value="ResourceAdministrator"/&gt;
 *     &lt;enumeration value="Client"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "accessLevel_Type")
@XmlEnum
public enum AccessLevelType {

    @XmlEnumValue("Administrator")
    ADMINISTRATOR("Administrator"),
    @XmlEnumValue("ResourceAdministrator")
    RESOURCE_ADMINISTRATOR("ResourceAdministrator"),
    @XmlEnumValue("Client")
    CLIENT("Client");
    private final String value;

    AccessLevelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccessLevelType fromValue(String v) {
        for (AccessLevelType c: AccessLevelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
