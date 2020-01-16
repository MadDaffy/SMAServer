package com.data.server.dataserver.mapper;

import com.data.server.dataserver.dto.*;
import com.data.server.dataserver.model.*;
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
        List<Sensor> sensorsToFields = new ArrayList<>();
        List<Point> points = new ArrayList<>();

        field.setId(fieldDto.getId());
        field.setName(fieldDto.getName());
        field.setLatitude(fieldDto.getLatitude());
        field.setLongitude(fieldDto.getLongitude());

        fillCompanies(fieldDto, field);
        fillSensors(fieldDto, field);
        fillPoints(fieldDto, field);

        return field;
    }

    default FieldDto toFieldDto(Field field) {
        if (field == null) {
            return null;
        }

        FieldDto fieldDto = new FieldDto();

        fieldDto.setId(field.getId());
        fieldDto.setName(field.getName());
        fieldDto.setLatitude(field.getLatitude());
        fieldDto.setLongitude(field.getLongitude());

        fillCompaniesDto(field, fieldDto);
        fillSensorsDto(field, fieldDto);
        fillPointsDto(field, fieldDto);

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
        List<Field> fields = new ArrayList<>();
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
            sensor.setWindSpeed(sensorDto.getWindSpeed());
            sensor.setWindDirection(sensorDto.getWindDirection());
                for(FieldDto fieldDto : sensorDto.getFields()){
                    Field field = new Field();
                    field.setId(fieldDto.getId());
                    field.setName(fieldDto.getName());
                    field.setLatitude(fieldDto.getLatitude());
                    field.setLongitude(fieldDto.getLongitude());

                    fields.add(field);
                }
               sensor.setFields(fields);
        }
        model.setSensors(data);
    }

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
        List<FieldDto> fields = new ArrayList<>();
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
            sensorDto.setWindSpeed(sensor.getWindSpeed());
            sensorDto.setWindDirection(sensor.getWindDirection());
            for(Field field : sensor.getFields()){
                FieldDto fieldDto = new FieldDto();
                fieldDto.setId(field.getId());
                fieldDto.setName(field.getName());
                fieldDto.setLatitude(field.getLatitude());
                fieldDto.setLongitude(field.getLongitude());

                fields.add(fieldDto);
            }
            sensorDto.setFields(fields);
        }
        dto.setSensors(data);
    }
    /**
     * Update points field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillPoints(FieldDto dto,
                             @MappingTarget Field model) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getPoints())) {
            return;
        }

        List<Point> data = new ArrayList<>();
        for (PointDto pointDto : dto.getPoints()) {
            Point point = new Point();
            point.setId(pointDto.getId());
            point.setLatitude(pointDto.getLatitude());
            point.setLongitude(pointDto.getLongitude());

        }
        model.setPoints(data);
    }

    @AfterMapping
    default void fillPointsDto(Field model,
                                @MappingTarget FieldDto dto) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getPoints())) {
            return;
        }

        List<PointDto> data = new ArrayList<>();
        for (Point point : model.getPoints()) {
            PointDto pointDto = new PointDto();
            pointDto.setId(point.getId());
            pointDto.setLatitude(point.getLatitude());
            pointDto.setLongitude(point.getLongitude());
        }
        dto.setPoints(data);
    }
}