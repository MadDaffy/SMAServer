package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.SensorDao;
import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.model.Sensor;
import com.data.server.dataserver.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SensorServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorDao sensorDao;

    @Override
    public void updateSensor(SensorDto sensorDto) {
        sensorDao.updateSensor(sensorDto);
    }

    @Override
    public SensorDto findSensor(Long id) {
        return sensorDao.findSensorById(id);
    }
}
