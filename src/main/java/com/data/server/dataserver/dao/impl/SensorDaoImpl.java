package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.SensorDao;
import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.mapper.SensorMapper;
import com.data.server.dataserver.model.Sensor;
import com.data.server.dataserver.repository.SensorRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * SensorDaoImpl
 *
 * @author Dmitriy
 */
@Repository
@Transactional
public class SensorDaoImpl implements SensorDao {

    SensorMapper sensorMapper = Mappers.getMapper(SensorMapper.class);
    @Autowired
    SensorRepository sensorRepository;

    @Override
    @Transactional
    public void updateSensor(Sensor sensor) {
        sensorRepository.saveAndFlush(sensor);
    }

    @Override
    @Transactional
    public Sensor findSensorById(Long id) {

        return sensorRepository.getSensorById(id);
    }
}
