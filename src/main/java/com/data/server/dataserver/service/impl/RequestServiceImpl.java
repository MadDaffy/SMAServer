package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.FieldDao;
import com.data.server.dataserver.dao.PointDao;
import com.data.server.dataserver.dto.*;
import com.data.server.dataserver.mapper.CompanyMapper;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.model.Point;
import com.data.server.dataserver.service.FieldService;
import com.data.server.dataserver.service.JsonRequestService;
import com.data.server.dataserver.service.PointService;
import com.data.server.dataserver.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.data.server.dataserver.service.impl.jsonType.*;

/**
 * RequestServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class RequestServiceImpl implements JsonRequestService {

    @Autowired
    UserService userService;

    @Autowired
    FieldService fieldService;

    @Autowired
    PointService pointService;

    @Override
    public JSONObject parseJsonAndRequest(String jsonMsg, String login) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jsonType = new JSONObject();
        JSONObject jsonAnswer = new JSONObject();
        JSONObject jsonSystemAns = new JSONObject();

        try {
            jsonType = (JSONObject) parser.parse(jsonMsg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println("Принятый JSON " + jsonType);

        UserDto userDto = userService.getUserByLogin(login);

        if (UpdateFields.getJsonTypeNum()==Integer.parseInt(jsonType.get("type").toString())){
            JSONArray jsonArrayFields = new JSONArray();
            JSONObject jsonAllFieldsAns = new JSONObject();

            jsonAnswer.put("type", UpdateFields.getJsonTypeNum());
            jsonSystemAns.put("dt", 1569653340);
            jsonAnswer.put("system", jsonSystemAns);
            for (FieldDto fieldDto : userDto.getCompanies().get(0).getFields()){
                JSONObject jsonField = new JSONObject();
                JSONObject center = new JSONObject();
                JSONArray locations = new JSONArray();

                jsonField.put("id", fieldDto.getId());
                jsonField.put("name", fieldDto.getName());
                for (PointDto pointDto : fieldDto.getPoints()){
                    JSONObject location = new JSONObject();
                    location.put("latitude", pointDto.getLatitude());
                    location.put("longitude", pointDto.getLongitude());

                    locations.add(location);
                }
                jsonField.put("location", locations);
                center.put("latitude", fieldDto.getLatitude());
                center.put("longitude", fieldDto.getLongitude());
                jsonField.put("center", center);
                AverageValueOfSensors averageValueOfSensors = AverageValueOfSensors.getAverage(fieldDto);
                jsonField.put("temperature", averageValueOfSensors.getTemperature());
                jsonField.put("humidity", averageValueOfSensors.getHumidity());
                jsonField.put("pressure", averageValueOfSensors.getPressure());
                jsonField.put("lastUpdate", fieldDto.getSensors().get(0).getLastUpdate().toString());

                jsonArrayFields.add(jsonField);
            }

            jsonAllFieldsAns.put("fields", jsonArrayFields);
            jsonAnswer.put("main", jsonAllFieldsAns);
        }
        if(UpdateSensors.getJsonTypeNum()==Integer.parseInt(jsonType.get("type").toString())){
            JSONObject jsonAllSensorsAns = new JSONObject();
            JSONArray sensors = new JSONArray();

            jsonAnswer.put("type", UpdateSensors.getJsonTypeNum());
            jsonSystemAns.put("dt", 1569653340);
            jsonAnswer.put("system", jsonSystemAns);
            for(SensorDto sensorDto : userDto.getCompanies().get(0).getSensors()) {
                JSONObject jSonSensor = new JSONObject();

                jSonSensor.put("id", sensorDto.getId());
                jSonSensor.put("name", sensorDto.getName());
                jSonSensor.put("latitude", sensorDto.getLatitude());
                jSonSensor.put("longitude", sensorDto.getLongitude());
                jSonSensor.put("temperature", sensorDto.getTemperature());
                jSonSensor.put("humidity", sensorDto.getHumidity());
                jSonSensor.put("pressure", sensorDto.getPressure());
                jSonSensor.put("battery", sensorDto.getBattery());
                jSonSensor.put("gsmlvl", sensorDto.getGsmlvl());
                try {
                    JSONObject jSonGround = (JSONObject)parser.parse(sensorDto.getGround());
                    jSonSensor.put("ground", jSonGround);
                } catch (ParseException e) {
                    e.printStackTrace();
                    jSonSensor.put("ground", new JSONObject());
                }
                jSonSensor.put("lastUpdate", sensorDto.getLastUpdate().toString());

                sensors.add(jSonSensor);
            }
            jsonAllSensorsAns.put("sensors", sensors);
            jsonAnswer.put("main", jsonAllSensorsAns);
        }
        if(UpdateCars.getJsonTypeNum()==Integer.parseInt(jsonType.get("type").toString())){
            JSONObject allCarsAns = new JSONObject();
            JSONArray  cars = new JSONArray();

            jsonAnswer.put("type", UpdateCars.getJsonTypeNum());
            jsonSystemAns.put("dt", 1569653340);
            jsonAnswer.put("system", jsonSystemAns);

            for(CarDto carDto : userDto.getCompanies().get(0).getCars()){
                JSONObject jsonCar = new JSONObject();

                jsonCar.put("id", carDto.getId());
                jsonCar.put("name", carDto.getName());
                jsonCar.put("latitude", carDto.getLatitude());
                jsonCar.put("longitude", carDto.getLongitude());
                jsonCar.put("speed", carDto.getSpeed());
                jsonCar.put("lastUpdate", carDto.getLastUpdate().toString());

                cars.add(jsonCar);
            }
            allCarsAns.put("cars", cars);
            jsonAnswer.put("main", allCarsAns);
        }

        if(AddField.getJsonTypeNum() == Integer.parseInt(jsonType.get("type").toString())){
            JSONObject jsonMain = (JSONObject) parser.parse(jsonType.get("main").toString());
            JSONArray location = (JSONArray) parser.parse(jsonMain.get("location").toString());
            JSONObject center = (JSONObject) parser.parse(jsonMain.get("center").toString());
            CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);
            List<Point> pointsList = new ArrayList<>();
            Field field = new Field();

            for(int i = 0; i<location.size(); i++) {
                Point point = new Point();
                JSONObject jPoint = (JSONObject) location.get(i);
                point.setLongitude(Double.parseDouble(jPoint.get("longitude").toString()));
                point.setLatitude(Double.parseDouble(jPoint.get("latitude").toString()));

                pointsList.add(point);
            }
//          field.setId(fieldService.getCountField()+1);
            field.setName(jsonMain.get("name").toString());
            field.setLongitude(Double.parseDouble(center.get("longitude").toString()));
            field.setLatitude(Double.parseDouble(center.get("latitude").toString()));
            fieldService.createField(field);
            field = fieldService.getFieldByName(field.getName());
            field.setCompanies(companyMapper.toCompanyList(userDto.getCompanies()));
            fieldService.updateField(field);
                for(Point point : pointsList){
                    Point point1 = pointService.createPoint(point);
                    point1.setField(fieldService.getFieldByName(field.getName()));
                    pointService.updatePoint(point1);
                }

//            fieldService.createField(Field.builder()
//                    .name(jsonMain.get("name").toString())
//                    .latitude(Double.parseDouble(center.get("latitude").toString()))
//                    .longitude(Double.parseDouble(center.get("longitude").toString()))
//                    .companies(companyMapper.toCompanyList(userDto.getCompanies()))
//                    .points(pointsList)
//                    .build());

            jsonAnswer.put("answer", "Add field successful");

//            JSONObject point = (JSONObject) parser.parse(jsonType.get("main").toString());

        }
//
//            if(AddField.getJsonTypeNum()==Integer.parseInt(jsonType.get("type").toString())){
//
//            }
        return jsonAnswer;
    }

}