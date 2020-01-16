package com.data.server.dataserver.dao;

import com.data.server.dataserver.model.SensorHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SensorHistoryDao
 *
 * @author Dmitriy
 */
@Repository
public interface SensorHistoryDao {
    void createSensorHistory(SensorHistory sensorHistory);
    List<SensorHistory> getAllSensorHistoryByIdSensor(Long idSensor);
}
