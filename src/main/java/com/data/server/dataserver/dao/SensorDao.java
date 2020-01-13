package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.model.Sensor;
import org.springframework.stereotype.Repository;

/**
 * SensorDao
 *
 * @author Dmitriy
 */
@Repository
public interface SensorDao {

    void updateSensor(Sensor sensor);
    Sensor findSensorById(Long id);

}
