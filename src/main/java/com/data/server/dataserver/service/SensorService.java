package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.SensorDto;

/**
 * SensorService
 *
 * @author Dmitriy
 */
public interface SensorService {
    void updateSensor(SensorDto sensorDto);
    SensorDto findSensor(Long id);
}
