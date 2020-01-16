package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.FieldDao;
import com.data.server.dataserver.dao.PointDao;
import com.data.server.dataserver.dto.*;
import com.data.server.dataserver.mapper.CompanyMapper;
import com.data.server.dataserver.model.Field;
import com.data.server.dataserver.model.Point;
import com.data.server.dataserver.model.SensorHistory;
import com.data.server.dataserver.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    SensorHistoryService sensorHistoryService;

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
//                jsonField.put("lastUpdate", fieldDto.getSensors().get(0).getLastUpdate().toString());

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
                jSonSensor.put("windSpeed", sensorDto.getWindSpeed());
                jSonSensor.put("windDirection", sensorDto.getWindDirection());
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

            field.setName(jsonMain.get("name").toString());
            field.setLongitude(Double.parseDouble(center.get("longitude").toString()));
            field.setLatitude(Double.parseDouble(center.get("latitude").toString()));
            Field field1;
            field1 = fieldService.createField(field);
            field1.setCompanies(companyMapper.toCompanyList(userDto.getCompanies()));
            Field field2 = fieldService.updateField(field1);
                for(Point point : pointsList){
                    Point point1 = pointService.createPoint(point);
                    point1.setField(field2);
                    pointService.updatePoint(point1);
                }

            jsonAnswer.put("answer", "Add field successful");

        }
        if (HistorySensors.getJsonTypeNum() == Integer.parseInt(jsonType.get("type").toString())){
            JSONObject jsonMain = (JSONObject) parser.parse(jsonType.get("main").toString());

            JSONArray historyList = new JSONArray();
            JSONObject jsonMainAns = new JSONObject();

            List<SensorHistory> sensorHistoryList = sensorHistoryService.getAllSensorHistoryByIdSensor(Long.parseLong(jsonMain.get("target_id").toString()));
            jsonAnswer.put("type", HistorySensors.getJsonTypeNum());
            jsonSystemAns.put("dt", 1569653340);
            jsonAnswer.put("system", jsonSystemAns);
            jsonMainAns.put("target_id", sensorHistoryList.get(0).getIdSensor());
            jsonMainAns.put("property", jsonMain.get("property"));
            for(SensorHistory sensorHistory : sensorHistoryList) {
                    JSONObject history = new JSONObject();
                    switch (jsonMain.get("property").toString()){
                        case "temperature" :
                            history.put("dt", sensorHistory.getTimeUpdate().toString());
                            history.put("value", sensorHistory.getTemperature());

                            historyList.add(history);
                            break;
                        case "humidity" :
                            history.put("dt", sensorHistory.getTimeUpdate().toString());
                            history.put("value", sensorHistory.getHumidity());

                            historyList.add(history);
                            break;

                        case "pressure" :
                            history.put("dt", sensorHistory.getTimeUpdate().toString());
                            history.put("value", sensorHistory.getPressure());

                            historyList.add(history);
                            break;

                        case "battery" :
                            history.put("dt", sensorHistory.getTimeUpdate().toString());
                            history.put("value", sensorHistory.getBattery());

                            historyList.add(history);
                            break;

                        case "gsmlvl" :
                            history.put("dt", sensorHistory.getTimeUpdate().toString());
                            history.put("value", sensorHistory.getGsmlvl());

                            historyList.add(history);
                            break;

                        case "windSpeed" :
                            history.put("dt", sensorHistory.getTimeUpdate().toString());
                            history.put("value", sensorHistory.getWindSpeed());

                            historyList.add(history);
                            break;

                        case "windDirection" :
                            history.put("dt", sensorHistory.getTimeUpdate().toString());
                            history.put("value", sensorHistory.getWindDirection());

                            historyList.add(history);
                            break;

                    }

            }
            jsonMainAns.put("history", historyList);
            jsonAnswer.put("main", jsonMainAns);
        }
        return jsonAnswer;
    }

}