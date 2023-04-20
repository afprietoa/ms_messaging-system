package com.makaia.express.modules.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private String type;
    Size(String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return type;
    }
}
