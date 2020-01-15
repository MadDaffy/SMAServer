package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.FieldDao;
import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FieldServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class FieldServiceImpl implements FieldService  {

    @Autowired
    FieldDao fieldDao;

    @Override
    public void createField(Field field) {
        fieldDao.createField(field);
    }

    @Override
    public Field getFieldByName(String fieldName) {
        return fieldDao.getFieldByName(fieldName);
    }

    @Override
    public Long getCountField() {
        return fieldDao.getCountField();
    }

    @Override
    public void updateField(Field field) {
         fieldDao.updateField(field);
    }
}
