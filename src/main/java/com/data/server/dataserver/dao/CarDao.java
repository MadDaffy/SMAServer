package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.CarDto;

/**
 * CarDao
 *
 * @author Dmitriy
 */
public interface CarDao {
    void createCar(CarDto carDto);
    void updateCar(CarDto carDto);
}
