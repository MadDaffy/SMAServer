package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.FieldDao;
import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FieldServiceImpl
 *
 * @author Dmitriy
 */
public class FieldServiceImpl implements FieldService  {

    @Autowired
    FieldDao fieldDao;

    @Override
    public void updateField(FieldDto fieldDto) {
        fieldDao.updateField(fieldDto);
    }

    @Override
    public void createField(FieldDto fieldDto) {
        fieldDao.createField(fieldDto);
    }
}
