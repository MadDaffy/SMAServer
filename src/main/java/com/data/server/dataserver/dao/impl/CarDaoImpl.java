package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.CarDao;
import com.data.server.dataserver.dto.CarDto;
import com.data.server.dataserver.mapper.CarMapper;
import com.data.server.dataserver.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * CarDaoImpl
 *
 * @author Dmitriy
 */
@Repository
@Transactional
public class CarDaoImpl implements CarDao {

    CarMapper carMapper;

    @Autowired
    CarRepository carRepository;

    @Override
    @Transactional
    public void createCar(CarDto carDto) {
       carRepository.save(carMapper.toCar(carDto));
    }

    @Override
    @Transactional
    public void updateCar(CarDto carDto) {
        carRepository.saveAndFlush(carMapper.toCar(carDto));
    }
}
