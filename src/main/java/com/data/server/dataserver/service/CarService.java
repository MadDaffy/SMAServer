package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.CarDto;

/**
 * CarService
 *
 * @author Dmitriy
 */
public interface CarService {
    void createCar(CarDto carDto);
    void updateCar(CarDto carDto);
}
