package com.data.server.dataserver.mapper;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.model.Company;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.model.Sensor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * SensorMapper
 *
 * @author Dmitriy
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"

)
public interface SensorMapper {
    default Sensor toSensor(SensorDto sensorDto) {
        if (sensorDto == null) {
            return null;
        }

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

        fillCompanies(sensorDto, sensor);
        fillFields(sensorDto, sensor);

        return sensor;
    }

    default SensorDto toSensorDto(Sensor sensor) {
        if (sensor == null) {
            return null;
        }

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

        fillCompaniesDto(sensor, sensorDto);
        fillFieldsDto(sensor, sensorDto);

        return sensorDto;
    }

    List<SensorDto> toSensorDtoList(List<Sensor> sensors);

    /**
     * Update companies field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillCompanies(SensorDto dto,
                               @MappingTarget Sensor model) {
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
    default void fillCompaniesDto(Sensor model,
                                  @MappingTarget SensorDto dto) {
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
     * Update fields field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillFields(SensorDto dto,
                            @MappingTarget Sensor model){
        if(isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getFields())){
            return;
        }

        List<Field> data = new ArrayList<>();
        for(FieldDto fieldDto : dto.getFields()){
            Field field = new Field();
            field.setId(fieldDto.getId());
            field.setName(fieldDto.getName());
            field.setLocation(fieldDto.getLocation());
            field.setCentre(fieldDto.getCentre());
        }
        model.setFields(data);
    }

    @AfterMapping
    default void fillFieldsDto(Sensor model,
                               @MappingTarget SensorDto dto){
        if(isNull(model) || isNull(dto)){
            return;
        }
        if (isEmpty(dto.getFields())){
            return;
        }

        List<FieldDto> data = new ArrayList<>();
        for(Field field : model.getFields()){
            FieldDto fieldDto = new FieldDto();
            fieldDto.setId(field.getId());
            fieldDto.setName(field.getName());
            fieldDto.setLocation(field.getLocation());
            fieldDto.setCentre(field.getCentre());
        }
        dto.setFields(data);
    }
}
