package com.data.server.dataserver.mapper;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.model.Company;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.model.Point;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * PointMapper
 *
 * @author Dmitriy
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface PointMapper {

    default Point toPoint(PointDto pointDto) {
        if (pointDto == null) {
            return null;
        }

        Point point = new Point();

        point.setId(pointDto.getId());
        point.setLatitude(pointDto.getLatitude());
        point.setLongitude(pointDto.getLongitude());

        fillField(pointDto, point);

        return point;
    }

    default PointDto toPointDto(Point point) {
        if (point == null) {
            return null;
        }

        PointDto pointDto = new PointDto();

        pointDto.setId(point.getId());
        pointDto.setLatitude(point.getLatitude());
        pointDto.setLongitude(point.getLongitude());

        fillFieldDto(point, pointDto);

        return pointDto;
    }

    /**
     * Update fields field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillField(PointDto dto,
                            @MappingTarget Point model){
        if(isNull(model) || isNull(dto)) {
            return;
        }

            Field field = new Field();
            FieldDto fieldDto = dto.getFieldDto();
            field.setId(fieldDto.getId());
            field.setName(fieldDto.getName());
            field.setLatitude(fieldDto.getLatitude());
            field.setLongitude(fieldDto.getLongitude());
        model.setField(field);
    }

    @AfterMapping
    default void fillFieldDto(Point model,
                               @MappingTarget PointDto dto){
        if(isNull(model) || isNull(dto)){
            return;
        }


        FieldDto fieldDto = new FieldDto();
        Field field = model.getField();
        fieldDto.setId(field.getId());
        fieldDto.setName(field.getName());
        fieldDto.setLatitude(field.getLatitude());
        fieldDto.setLongitude(field.getLongitude());

        dto.setFieldDto(fieldDto);
    }

}
