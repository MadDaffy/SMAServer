package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.SensorDto;
import org.springframework.stereotype.Repository;

/**
 * SensorDao
 *
 * @author Dmitriy
 */
@Repository
public interface SensorDao {

    void updateSensor(SensorDto sensorDto);
    SensorDto findSensorById(Long id);

}
