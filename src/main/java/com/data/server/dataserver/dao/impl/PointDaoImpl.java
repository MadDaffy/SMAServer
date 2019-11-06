package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.PointDao;
import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.mapper.PointMapper;
import com.data.server.dataserver.repository.PointRepository;
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

    PointMapper pointMapper;

    @Autowired
    PointRepository pointRepository;

    @Override
    @Transactional
    public void createPoint(PointDto pointDto) {
        pointRepository.save(pointMapper.toPoint(pointDto));
    }

    @Override
    @Transactional
    public void updatePoint(PointDto pointDto) {
        pointRepository.saveAndFlush(pointMapper.toPoint(pointDto));
    }
}
