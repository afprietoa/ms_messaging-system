package com.makaia.express.modules.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    DRIVER("driver"),
    DELIVERER("deliverer"),
    COORDINATOR("coordinator");

    private String type;
    Role(String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return type;
    }
}
