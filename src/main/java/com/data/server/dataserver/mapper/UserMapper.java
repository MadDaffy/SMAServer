package com.data.server.dataserver.mapper;

import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.addAll;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

import com.data.server.dataserver.dto.*;
import com.data.server.dataserver.model.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

/**
 * UserMapper
 *
 * @author Dmitriy
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface UserMapper {

    default User toUser(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());

        fillCompanies(dto, user);

        return user;
    }

    default UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFullName());

        fillCompaniesDto(user, userDto);

        return userDto;
    }

    List<UserDto> toUserDtoList(List<User> users);

    /**
     * Update companies field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillCompanies(UserDto dto,
                               @MappingTarget User model) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getCompanies())) {
            return;
        }

        List<Company> data = new ArrayList<>();
        List<Sensor> sensorsToCompany = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        List<Sensor> sensorsToFields = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        for (CompanyDto companyDto : dto.getCompanies()) {
            Company company = new Company();
            company.setId(companyDto.getId());
            company.setName(companyDto.getName());
            for(FieldDto fieldDto : companyDto.getFields()){
                Field field = new Field();
                field.setName(fieldDto.getName());
                field.setId(fieldDto.getId());
                field.setLatitude(fieldDto.getLatitude());
                field.setLongitude(fieldDto.getLongitude());
                for(SensorDto sensorDto : fieldDto.getSensors()){
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
                    sensor.setWindSpeed(sensorDto.getWindSpeed());
                    sensor.setWindDirection(sensorDto.getWindDirection());

                    sensorsToFields.add(sensor);
                }
                for(PointDto pointDto : fieldDto.getPoints()){
                    Point point = new Point();
                    point.setId(pointDto.getId());
                    point.setLongitude(pointDto.getLongitude());
                    point.setLatitude(pointDto.getLatitude());
                    points.add(point);
                }
                field.setSensors(sensorsToFields);
                field.setPoints(points);
                fields.add(field);
            }
            for(SensorDto sensorDto : companyDto.getSensors()){
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
                sensor.setWindSpeed(sensorDto.getWindSpeed());
                sensor.setWindDirection(sensorDto.getWindDirection());

                sensorsToCompany.add(sensor);
            }

            for(CarDto carDto : companyDto.getCars()){
                Car car = new Car();
                car.setId(carDto.getId());
                car.setName(carDto.getName());
                car.setLatitude(carDto.getLatitude());
                car.setLongitude(carDto.getLongitude());
                car.setSpeed(carDto.getSpeed());
                car.setLastUpdate(carDto.getLastUpdate());

                cars.add(car);
            }
            company.setCars(cars);
            company.setSensors(sensorsToCompany);
            company.setFields(fields);
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
    default void fillCompaniesDto(User model,
                                  @MappingTarget UserDto dto) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getCompanies())) {
            return;
        }

        List<CompanyDto> data = new ArrayList<>();

        List<CarDto> carsDto = new ArrayList<>();
        for (Company company : model.getCompanies()) {
            CompanyDto companyDto = new CompanyDto();
            List<FieldDto> fieldsDto = new ArrayList<>();
            List<SensorDto> sensorsDtoToCompany = new ArrayList<>();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            for(Field field : company.getFields()){
                FieldDto fieldDto = new FieldDto();
                List<PointDto> pointsDto = new ArrayList<>();
                List<SensorDto> sensorsDtoToFields = new ArrayList<>();
                fieldDto.setName(field.getName());
                fieldDto.setId(field.getId());
                fieldDto.setLatitude(field.getLatitude());
                fieldDto.setLongitude(field.getLongitude());
                for(Sensor sensor : field.getSensors()){
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
                    sensorDto.setWindSpeed(sensor.getWindSpeed());
                    sensorDto.setWindDirection(sensor.getWindDirection());

                    sensorsDtoToFields.add(sensorDto);
                }
                for(Point point : field.getPoints()){
                    PointDto pointDto = new PointDto();
                    pointDto.setId(point.getId());
                    pointDto.setLongitude(point.getLongitude());
                    pointDto.setLatitude(point.getLatitude());

                    pointsDto.add(pointDto);
                }

                fieldDto.setSensors(sensorsDtoToFields);
                fieldDto.setPoints(pointsDto);
                fieldsDto.add(fieldDto);
            }
            for(Sensor sensor : company.getSensors()){
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
                sensorDto.setWindSpeed(sensor.getWindSpeed());
                sensorDto.setWindDirection(sensor.getWindDirection());

                sensorsDtoToCompany.add(sensorDto);
            }

            for(Car car : company.getCars()){
                CarDto carDto = new CarDto();
                carDto.setId(car.getId());
                carDto.setName(car.getName());
                carDto.setLatitude(car.getLatitude());
                carDto.setLongitude(car.getLongitude());
                carDto.setSpeed(car.getSpeed());
                carDto.setLastUpdate(car.getLastUpdate());

                carsDto.add(carDto);
            }
            companyDto.setCars(carsDto);
            companyDto.setSensors(sensorsDtoToCompany);
            companyDto.setFields(fieldsDto);
            data.add(companyDto);
        }
        dto.setCompanies(data);
    }
}
