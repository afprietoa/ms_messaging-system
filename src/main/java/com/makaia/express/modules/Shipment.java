package com.makaia.express.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@ApiModel(description ="this model represents the shipment data")
@Entity
@Table(name = "shipment")
public class Shipment implements Serializable {
    @ApiModelProperty(value = "shipment id", example ="81F065276A")
    @Id
    private Integer guideNumber;
    @ApiModelProperty(value = "shipment origin city", example ="Medellin")
    @Column(name = "originCity", length = 50)
    private String originCity;
    @ApiModelProperty(value = "shipment destiny city", example ="Bogota")
    @Column(name = "destinyCity", length = 50)
    private String destinyCity;
    @ApiModelProperty(value = "shipment destiny address", example ="street 46 # 69-90")
    @Column(name = "destinyAddress", length = 50)
    private String destinyAddress;
    @ApiModelProperty(value = "shipment recipient name", example ="Juan Manuel")
    @Column(name = "recipientName", length = 50)
    private String recipientName;
    @ApiModelProperty(value = "shipment recipient contact", example ="3046303886")
    @Column(name = "recipientContact")
    private Long recipientContact;
    @ApiModelProperty(value = "shipment delivery hour", example ="2")
    @Column(name = "deliveryHour")
    private Integer deliveryHour;
    @ApiModelProperty(value = "shipment state", example ="RECEIVED")
    @Column(name = "shipmentState", length = 50)
    private String shipmentState;
    @ApiModelProperty(value = "shipping costs", example ="100")
    @Column(name = "shippingCosts")
    private Double shippingCosts;
    @ManyToOne
    @JoinColumn(name = "idCardNumber")
    @JsonIgnoreProperties("shipments")
    private Customer customer;
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "shipment")
    @JsonIgnoreProperties("shipment")
    private List<Packet> packets;

    public Shipment() {}

    public Shipment(Integer guideNumber, String originCity, String destinyCity, String destinyAddress,
                    String recipientName, Long recipientContact, Integer deliveryHour,
                    String shipmentState, Double shippingCosts, Customer customer) {
        this.guideNumber = guideNumber;
        this.originCity = originCity;
        this.destinyCity = destinyCity;
        this.destinyAddress = destinyAddress;
        this.recipientName = recipientName;
        this.recipientContact = recipientContact;
        this.deliveryHour = deliveryHour;
        this.shipmentState = shipmentState;
        this.shippingCosts = shippingCosts;
        this.customer = customer;
    }

    public Integer getGuideNumber() {
        return guideNumber;
    }

    public void setGuideNumber(Integer guideNumber) {
        this.guideNumber = guideNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinyCity() {
        return destinyCity;
    }

    public void setDestinyCity(String destinyCity) {
        this.destinyCity = destinyCity;
    }

    public String getDestinyAddress() {
        return destinyAddress;
    }

    public void setDestinyAddress(String destinyAddress) {
        this.destinyAddress = destinyAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName= recipientName;
    }

    public Long getRecipientContact() {
        return recipientContact;
    }

    public void setRecipientContact(Long recipientContact) {
        this.recipientContact = recipientContact;
    }

    public Integer getDeliveryHour() {
        return deliveryHour;
    }

    public void setDeliveryHour(Integer deliveryHour) {
        this.deliveryHour = deliveryHour;
    }

    public String getShipmentState() {
        return shipmentState;
    }

    public void setShipmentState(String shipmentState) {
        this.shipmentState = shipmentState;
    }

    public Double getShippingCosts() {
        return shippingCosts;
    }

    public void setShippingCosts(Double shippingCosts) {
        this.shippingCosts = shippingCosts;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }
}
