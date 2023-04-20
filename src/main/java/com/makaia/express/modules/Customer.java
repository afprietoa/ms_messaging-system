package com.makaia.express.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@ApiModel(description ="this model represent the Customer data")
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @ApiModelProperty(value = "customer id", example ="1")
    @Id
    private Integer idCardNumber;
    @ApiModelProperty(value = "customer first name", example ="Grace")
    @Column(name = "firstName", length = 50)
    private String firstName;
    @ApiModelProperty(value = "customer last name", example ="Guido")
    @Column(name = "lastName", length = 50)
    private String lastName;
    @ApiModelProperty(value = "customer cellphone", example ="3004126895")
    @Column(name = "cellphone")
    private Long cellphone;
    @ApiModelProperty(value = "customer e-mail", example ="gabi@example.com")
    @Column(name = "email", length = 100)
    private String email;
    @ApiModelProperty(value = "customer address", example ="street 46 # 69-90")
    @Column(name = "address", length = 100)
    private String address;
    @ApiModelProperty(value = "customer city", example ="Medellin")
    @Column(name = "city", length = 50)
    private String city;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<Shipment> shipments;

    public Customer() {}

    public Customer(Integer idCardNumber, String firstName, String lastName,
                    Long cellphone, String email, String address,
                    String city) {
        this.idCardNumber = idCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellphone = cellphone;
        this.email = email;
        this.address = address;
        this.city = city;
    }

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

    public Long getCellphone() {
        return cellphone;
    }

    public void setCellphone(Long cellphone) {
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

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }
}
