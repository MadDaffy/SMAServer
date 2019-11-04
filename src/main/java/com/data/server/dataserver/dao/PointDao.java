package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.dto.PointDto;

/**
 * PointDao
 *
 * @author Dmitriy
 */
public interface PointDao {
    void createPoint(PointDto pointDto);
    void updatePoint(PointDto pointDto);
}
