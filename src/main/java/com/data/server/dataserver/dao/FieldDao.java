package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.FieldDto;

/**
 * FieldDao
 *
 * @author Dmitriy
 */
public interface FieldDao {

    void createField(FieldDto fieldDto);

    void updateField(FieldDto fieldDto);
}
