package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.PointDao;
import com.data.server.dataserver.dto.PointDto;
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
    public void createPoint(PointDto pointDto) {
        pointDao.createPoint(pointDto);
    }

    @Override
    public void updatePoint(PointDto pointDto) {
        pointDao.updatePoint(pointDto);
    }
}
