package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.dto.UserDto;

/**
 * PointService
 *
 * @author Dmitriy
 */
public interface PointService {
    void createPoint(PointDto pointDto);

    void updatePoint(PointDto pointDto);
}
