package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.model.Point;

/**
 * PointService
 *
 * @author Dmitriy
 */
public interface PointService {
    Point createPoint(Point point);
    void updatePoint(Point point);

}
