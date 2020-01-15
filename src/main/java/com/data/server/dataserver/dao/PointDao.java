package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.model.Point;
import org.springframework.stereotype.Repository;

/**
 * PointDao
 *
 * @author Dmitriy
 */
@Repository
public interface PointDao {
    Point createPoint(Point point);
    void updatePoint(Point point);
}
