package com.makaia.express.modules;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(description ="this model represents the employee data")
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @ApiModelProperty(value = "employee id", example ="1")
    @Id
    private Integer idCardNumber;
    @ApiModelProperty(value = "employee first name", example ="Paulette")
    @Column(name = "firstName", length = 50)
    private String firstName;
    @ApiModelProperty(value = "employee last name", example ="Patrick")
    @Column(name = "lastName", length = 50)
    private String lastName;
    @ApiModelProperty(value = "employee cellphone", example ="3004126895")
    @Column(name = "cellphone")
    private Integer cellphone;
    @ApiModelProperty(value = "employee e-mail", example ="pau@example.com")
    @Column(name = "email", length = 100)
    private String email;
    @ApiModelProperty(value = "employee address", example ="street 46 # 69-90")
    @Column(name = "address", length = 100)
    private String address;
    @ApiModelProperty(value = "employee city", example ="Medellin")
    @Column(name = "city", length = 50)
    private String city;
    @ApiModelProperty(value = "employee antiquity", example ="1")
    @Column(name = "antiquity")
    private Integer antiquity;
    @ApiModelProperty(value = "employee bloodType", example ="o+")
    @Column(name = "bloodType", length = 50)
    private String bloodType;
    @ApiModelProperty(value = "employee type", example ="DEALER")
    @Column(name = "employeeType", length = 50)
    private String employeeType;

    public Integer getIdCardNumber() {
        return idCardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCellphone() {
        return cellphone;
    }

    public void setCellphone(Integer cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAntiquity() {
        return antiquity;
    }

    public void setAntiquity(Integer antiquity) {
        this.antiquity = antiquity;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
