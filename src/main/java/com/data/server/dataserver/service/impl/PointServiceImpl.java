package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.PointDao;
import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.model.Point;
import com.data.server.dataserver.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PointServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class PointServiceImpl implements PointService {
    @Autowired
    PointDao pointDao;

    @Override
    public Point createPoint(Point point) {
       return pointDao.createPoint(point);
    }

    @Override
    public void updatePoint(Point point) {
        pointDao.updatePoint(point);
    }


}
