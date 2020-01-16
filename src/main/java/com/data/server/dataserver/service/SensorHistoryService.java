package com.data.server.dataserver.service;

import com.data.server.dataserver.model.SensorHistory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * SensorHistoryService
 *
 * @author Dmitriy
 */

public interface SensorHistoryService {
    void createSensorHistory(SensorHistory sensorHistory);
    List<SensorHistory> getAllSensorHistoryByIdSensor(Long idSensor);
}
