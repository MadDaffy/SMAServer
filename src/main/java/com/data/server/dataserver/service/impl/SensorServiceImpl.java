package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.SensorDao;
import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.model.Sensor;
import com.data.server.dataserver.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SensorServiceImpl
 *
 * @author Dmitriy
 */
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorDao sensorDao;

    @Override
    public void updateSensor(SensorDto sensorDto) {
        sensorDao.updateSensor(sensorDto);
    }
}
