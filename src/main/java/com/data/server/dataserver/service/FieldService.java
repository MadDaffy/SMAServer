package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.model.Field;

/**
 * FieldService
 *
 * @author Dmitriy
 */
public interface FieldService {
    Field createField(Field field);
    Field updateField(Field field);

}
