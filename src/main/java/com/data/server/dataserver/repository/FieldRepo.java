package com.data.server.dataserver.repository;

import com.data.server.dataserver.model.Field;

/**
 * FieldRepo
 *
 * @author Dmitriy
 */
public interface FieldRepo extends BaseRepository<Field, Long> {
    Field getFieldByName(String fieldName);

}
