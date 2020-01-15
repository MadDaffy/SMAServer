package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.model.Field;

/**
 * FieldService
 *
 * @author Dmitriy
 */
public interface FieldService {
    void createField(Field field);
    Field getFieldByName(String fieldName);
    Long getCountField();
    void updateField(Field field);

}
