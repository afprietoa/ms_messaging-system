package com.makaia.express.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(description ="this model represents the packet data")
@Entity
@Table(name = "packet")
public class Packet implements Serializable {
    @ApiModelProperty(value = "packet id", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    @ApiModelProperty(value = "packet type", example ="BIG")
    @Column(name = "packetType", length = 50)
    private String packetType;
    @ApiModelProperty(value = "packet weight", example ="1")
    @Column(name = "weight")
    private Double weight;
    @ApiModelProperty(value = "packet declared value", example ="190")
    @Column(name = "declaredValue")
    private Double declaredValue;

    @ManyToOne
    @JoinColumn(name = "guideNumber")
    @JsonIgnoreProperties("packets")
    private Shipment shipment;

    public Packet() {}

    public Packet(Integer code, String packetType, Double weight,
                  Double declaredValue) {
        this.code = code;
        this.packetType = packetType;
        this.weight = weight;
        this.declaredValue = declaredValue;
    }

    public Integer getCode() {
        return code;
    }

    public String getPacketType() {
        return packetType;
    }

    public void setPacketType(String packetType) {
        this.packetType = packetType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(Double declaredValue) {
        this.declaredValue = declaredValue;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
