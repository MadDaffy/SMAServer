package com.data.server.dataserver.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * jsonType
 *
 * @author Dmitriy
 */

public enum jsonType {

    Authorization(1),
    UpdateFields(2),
    UpdateSensors(3),
    UpdateCars(4);

    private int jsonTypeNum;

    jsonType(int jsonTypeNum){
        this.jsonTypeNum = jsonTypeNum;
    }

    public int getJsonTypeNum() {
        return jsonTypeNum;
    }
}
