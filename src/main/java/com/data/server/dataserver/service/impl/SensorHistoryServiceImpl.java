package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.SensorHistoryDao;
import com.data.server.dataserver.model.SensorHistory;
import com.data.server.dataserver.service.SensorHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * SensorHistoryServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class SensorHistoryServiceImpl implements SensorHistoryService {
    @Autowired
    SensorHistoryDao sensorHistoryDao;

    @Override
    public void createSensorHistory(SensorHistory sensorHistory) {
        sensorHistoryDao.createSensorHistory(sensorHistory);
    }

    @Override
    public List<SensorHistory> getAllSensorHistoryByIdSensor(Long idSensor) {
        return sensorHistoryDao.getAllSensorHistoryByIdSensor(idSensor);
    }
}
