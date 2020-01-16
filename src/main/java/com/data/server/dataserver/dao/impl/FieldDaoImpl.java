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
    public Field createField(Field field) {
        Field field1;
       field1 = fieldRepository.save(field);
       return field1;
    }

    @Override
    @Transactional
    public Field updateField(Field field) {
        Field field2;
        field2 = fieldRepository.saveAndFlush(field);
        return field2;
    }
}
