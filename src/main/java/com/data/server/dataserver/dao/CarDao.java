package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.CarDto;
import org.springframework.stereotype.Repository;

/**
 * CarDao
 *
 * @author Dmitriy
 */
@Repository
public interface CarDao {
    void createCar(CarDto carDto);
    void updateCar(CarDto carDto);
}
