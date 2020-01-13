package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.CarDao;
import com.data.server.dataserver.dto.CarDto;
import com.data.server.dataserver.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CarServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarDao carDao;

    @Override
    public void createCar(CarDto carDto) {
        carDao.createCar(carDto);
    }

    @Override
    public void updateCar(CarDto carDto) {
        carDao.updateCar(carDto);
    }
}
