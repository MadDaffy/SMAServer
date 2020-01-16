package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.SensorHistoryDao;
import com.data.server.dataserver.model.SensorHistory;
import com.data.server.dataserver.repository.SensorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * SensorHistoryDaoImpl
 *
 * @author Dmitriy
 */
@Repository
@Transactional
public class SensorHistoryDaoImpl implements SensorHistoryDao {

    @Autowired
    SensorHistoryRepository sensorHistoryRepository;

    @Override
    @Transactional
    public void createSensorHistory(SensorHistory sensorHistory) {
        sensorHistoryRepository.saveAndFlush(sensorHistory);
    }

    @Override
    @Transactional
    public List<SensorHistory> getAllSensorHistoryByIdSensor(Long idSensor) {
        return sensorHistoryRepository.getAllSensorHistoryByIdSensor(idSensor);
    }
}
