package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.FieldDao;
import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.mapper.FieldMapper;
import com.data.server.dataserver.repository.FieldRepo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.repository.FieldRepository;

import javax.transaction.Transactional;

/**
 * FieldDaoImpl
 *
 * @author Dmitriy
 */
@Repository
@Transactional
public class FieldDaoImpl implements FieldDao {

    FieldMapper fieldMapper = Mappers.getMapper(FieldMapper.class);
    @Autowired
    FieldRepo fieldRepository;

    @Override
    @Transactional
    public void createField(FieldDto fieldDto) {
        fieldRepository.save(fieldMapper.toField(fieldDto));
    }

    @Override
    @Transactional
    public void updateField(FieldDto fieldDto) {
        fieldRepository.saveAndFlush(fieldMapper.toField(fieldDto));
    }
}
