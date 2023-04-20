package com.makaia.express.modules.Request;

public class TrackingRequest {
    private String shipmentState;
    private Integer idCardNumber;

    public TrackingRequest(String shipmentState, Integer idCardNumber){
        this.shipmentState = shipmentState;
        this.idCardNumber = idCardNumber;
    }

    public String getShipmentState() {
        return shipmentState;
    }

    public Integer getIdCardNumber() {
        return idCardNumber;
    }
}
