package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.PointDao;
import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.mapper.FieldMapper;
import com.data.server.dataserver.mapper.PointMapper;
import com.data.server.dataserver.model.Point;
import com.data.server.dataserver.repository.PointRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * PointDaoImpl
 *
 * @author Dmitriy
 */
@Repository
@Transactional
public class PointDaoImpl implements PointDao {

    PointMapper pointMapper = Mappers.getMapper(PointMapper.class);

    @Autowired
    PointRepository pointRepository;

    @Override
    @Transactional
    public Point createPoint(Point point) {
        Point point1;
        point1 = pointRepository.save(point);
       return point1;
    }

    @Override
    @Transactional
    public void updatePoint(Point point) {
        pointRepository.saveAndFlush(point);
    }
}
