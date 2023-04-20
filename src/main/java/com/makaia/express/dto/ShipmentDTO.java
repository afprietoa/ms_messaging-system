package com.makaia.express.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.makaia.express.modules.Customer;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ShipmentDTO {

    private Integer idCardCustomer;

    private String originCity;

    private String destinyCity;

    private String destinyAddress;

    private String recipientName;

    private Long recipientContact;

    private Double declaredValue;

    private Double weight;

    private Double shippingCosts;

    private String shipmentState;

    private Integer guideNumber;

    public ShipmentDTO(Integer idCardCustomer, String originCity, String destinyCity,
                       String destinyAddress, String recipientName, Long recipientContact,
                       Double declaredValue, Double weight, Double shippingCosts,
                       String shipmentState, Integer guideNumber) {
        this.idCardCustomer = idCardCustomer;
        this.originCity = originCity;
        this.destinyCity = destinyCity;
        this.destinyAddress = destinyAddress;
        this.recipientName = recipientName;
        this.recipientContact = recipientContact;
        this.declaredValue = declaredValue;
        this.weight = weight;
        this.shippingCosts = shippingCosts;
        this.shipmentState = shipmentState;
        this.guideNumber = guideNumber;
    }

    public void setIdCardCustomer(Integer idCardCustomer) {
        this.idCardCustomer = idCardCustomer;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public void setDestinyCity(String destinyCity) {
        this.destinyCity = destinyCity;
    }

    public void setDestinyAddress(String destinyAddress) {
        this.destinyAddress = destinyAddress;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientContact(Long recipientContact) {
        this.recipientContact = recipientContact;
    }

    public void setDeclaredValue(Double declaredValue) {
        this.declaredValue = declaredValue;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setShippingCosts(Double shippingCosts) {
        this.shippingCosts = shippingCosts;
    }

    public void setShipmentState(String shipmentState) {
        this.shipmentState = shipmentState;
    }

    public void setGuideNumber(Integer guideNumber) {
        this.guideNumber = guideNumber;
    }
}
