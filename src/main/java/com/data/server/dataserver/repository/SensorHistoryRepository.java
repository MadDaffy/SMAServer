package com.data.server.dataserver.repository;

import com.data.server.dataserver.model.SensorHistory;

import java.util.Date;
import java.util.List;

/**
 * SensorHistoryRepository
 *
 * @author Dmitriy
 */
public interface SensorHistoryRepository extends BaseRepository<SensorHistory, Long> {
    List<SensorHistory> getAllSensorHistoryByIdSensor(Long idSensor);
}
