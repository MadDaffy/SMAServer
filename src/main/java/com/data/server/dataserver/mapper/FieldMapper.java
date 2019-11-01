package com.data.server.dataserver.mapper;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.model.Company;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.model.Sensor;
import com.data.server.dataserver.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * FieldMapper
 *
 * @author Dmitriy
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"

)
public interface FieldMapper {
    default Field toField(FieldDto fieldDto) {
        if (fieldDto == null) {
            return null;
        }

        Field field = new Field();

        field.setId(fieldDto.getId());
        field.setName(fieldDto.getName());
        field.setCentre(fieldDto.getCentre());
        field.setLocation(fieldDto.getLocation());

        fillCompanies(fieldDto, field);
        fillSensors(fieldDto, field);

        return field;
    }

    default FieldDto toFieldDto(Field field) {
        if (field == null) {
            return null;
        }

        FieldDto fieldDto = new FieldDto();

        fieldDto.setId(field.getId());
        fieldDto.setName(field.getName());
        fieldDto.setCentre(field.getCentre());
        fieldDto.setLocation(field.getLocation());

        fillCompaniesDto(field, fieldDto);
        fillSensorsDto(field, fieldDto);

        return fieldDto;
    }

    List<FieldDto> toFieldDtoList(List<Field> fields);

    /**
     * Update companies field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillCompanies(FieldDto dto,
                               @MappingTarget Field model) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getCompanies())) {
            return;
        }

        List<Company> data = new ArrayList<>();
        for (CompanyDto companyDto : dto.getCompanies()) {
            Company company = new Company();
            company.setId(companyDto.getId());
            company.setName(companyDto.getName());

            data.add(company);
        }
        model.setCompanies(data);
    }

    /**
     * Update companies dto field from Model after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillCompaniesDto(Field model,
                                  @MappingTarget FieldDto dto) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getCompanies())) {
            return;
        }

        List<CompanyDto> data = new ArrayList<>();
        for (Company company : model.getCompanies()) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());

            data.add(companyDto);
        }
        dto.setCompanies(data);
    }

    /**
     * Update sensors field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillSensors(FieldDto dto,
                             @MappingTarget Field model) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getSensors())) {
            return;
        }

        List<Sensor> data = new ArrayList<>();
        for (SensorDto sensorDto : dto.getSensors()) {
            Sensor sensor = new Sensor();
            sensor.setId(sensorDto.getId());
            sensor.setName(sensorDto.getName());
            sensor.setLatitude(sensorDto.getLatitude());
            sensor.setLongitude(sensorDto.getLongitude());
            sensor.setBattery(sensorDto.getBattery());
            sensor.setGround(sensorDto.getGround());
            sensor.setGsmlvl(sensorDto.getGsmlvl());
            sensor.setHumidity(sensorDto.getHumidity());
            sensor.setLastUpdate(sensorDto.getLastUpdate());
            sensor.setPressure(sensorDto.getPressure());
            sensor.setTemperature(sensorDto.getTemperature());

        }
        model.setSensors(data);
    }

    /**
     * Update companies dto field from Model after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillSensorsDto(Field model,
                                  @MappingTarget FieldDto dto) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getSensors())) {
            return;
        }

        List<SensorDto> data = new ArrayList<>();
        for (Sensor sensor : model.getSensors()) {
            SensorDto sensorDto = new SensorDto();
            sensorDto.setId(sensor.getId());
            sensorDto.setName(sensor.getName());
            sensorDto.setLatitude(sensor.getLatitude());
            sensorDto.setLongitude(sensor.getLongitude());
            sensorDto.setBattery(sensor.getBattery());
            sensorDto.setGround(sensor.getGround());
            sensorDto.setGsmlvl(sensor.getGsmlvl());
            sensorDto.setHumidity(sensor.getHumidity());
            sensorDto.setLastUpdate(sensor.getLastUpdate());
            sensorDto.setPressure(sensor.getPressure());
            sensorDto.setTemperature(sensor.getTemperature());
        }
        dto.setSensors(data);
    }
}