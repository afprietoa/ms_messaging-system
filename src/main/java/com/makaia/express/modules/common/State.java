package com.makaia.express.modules.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum State {
    RECEIVED("received"),
    ON_ROUTE("on_route"),
    DELIVERED("delivered");

    private String type;
    State(String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return type;
    }
}
