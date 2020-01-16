package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.model.Field;
import org.springframework.stereotype.Repository;

/**
 * FieldDao
 *
 * @author Dmitriy
 */
@Repository
public interface FieldDao {

    Field createField(Field field);

    Field updateField(Field field);

}
