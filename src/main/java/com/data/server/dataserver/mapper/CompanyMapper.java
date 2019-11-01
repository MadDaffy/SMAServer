package com.data.server.dataserver.mapper;

import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

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

/**
 * CompanyMapper
 *
 * @author Dmitriy
 */

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CompanyMapper {

    default Company toCompany(CompanyDto companyDto) {
        if (companyDto == null) {
            return null;
        }

        Company company = new Company();

        company.setId(companyDto.getId());
        company.setName(companyDto.getName());

        fillUsers(companyDto, company);
        fillSensors(companyDto, company);
        fillFields(companyDto, company);

        return company;
    }

    default CompanyDto toCompanyDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setName(company.getName());

        fillUsersDto(company, companyDto);
        fillSensorsDto(company, companyDto);
        fillFieldsDto(company, companyDto);

        return companyDto;
    }

    List<CompanyDto> toCompanyDtoList(List<Company> companies);

    /**
     * Update users field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillUsers(CompanyDto dto,
                           @MappingTarget Company model) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getUsers())) {
            return;
        }

        List<User> data = new ArrayList<>();
        for (UserDto userDto : dto.getUsers()) {
            User user = new User();
            user.setId(userDto.getId());
            user.setFullName(userDto.getFullName());
            user.setLogin(userDto.getLogin());
            user.setPassword(userDto.getPassword());
            data.add(user);
        }
        model.setUsers(data);
    }

    /**
     * Update companies dto field from Model after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillUsersDto(Company model,
                              @MappingTarget CompanyDto dto) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getUsers())) {
            return;
        }

        List<UserDto> data = new ArrayList<>();
        for (User user : model.getUsers()) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFullName(user.getFullName());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
            data.add(userDto);
        }
        dto.setUsers(data);
    }

    /**
     * Update sensors field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */

    @AfterMapping
    default void fillSensors(CompanyDto dto,
                             @MappingTarget Company model){
        if(isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getSensors())){
            return;
        }

        List<Sensor> data = new ArrayList<>();
        for(SensorDto sensorDto : dto.getSensors()){
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

    @AfterMapping
    default void fillSensorsDto(Company model,
                                @MappingTarget CompanyDto dto){
        if(isNull(model) || isNull(dto)){
            return;
        }
        if (isEmpty(dto.getSensors())){
            return;
        }

        List<SensorDto> data = new ArrayList<>();
        for(Sensor sensor : model.getSensors()){
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

    /**
     * Update fields field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillFields(CompanyDto dto,
                             @MappingTarget Company model){
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
    default void fillFieldsDto(Company model,
                                @MappingTarget CompanyDto dto){
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

