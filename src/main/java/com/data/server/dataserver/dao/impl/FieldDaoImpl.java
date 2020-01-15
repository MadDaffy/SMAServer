package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.FieldDao;
import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.mapper.FieldMapper;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.repository.FieldRepo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    public void createField(Field field) {
        fieldRepository.saveAndFlush(field);
    }

    @Override
    @Transactional
    public void updateField(Field field) {
        fieldRepository.saveAndFlush(field);
    }

    @Override
    @Transactional
    public Field getFieldByName(String fieldName) {
        return fieldRepository.getFieldByName(fieldName);
    }

    @Override
    @Transactional
    public Long getCountField() {
        return fieldRepository.count();
    }
}
