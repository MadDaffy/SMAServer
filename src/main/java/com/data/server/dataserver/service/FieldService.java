package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.FieldDto;

/**
 * FieldService
 *
 * @author Dmitriy
 */
public interface FieldService {
    void updateField(FieldDto fieldDto);
    void createField(FieldDto fieldDto);
}
