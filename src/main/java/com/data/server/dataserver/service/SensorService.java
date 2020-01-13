package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.model.Sensor;

/**
 * SensorService
 *
 * @author Dmitriy
 */
public interface SensorService {
    void updateSensor(Sensor sensor);
    Sensor findSensor(Long id);
}
