package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.service.JsonRequestService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import static com.data.server.dataserver.service.impl.jsonType.Authorization;
import static com.data.server.dataserver.service.impl.jsonType.UpdateFields;

/**
 * RequestServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class RequestServiceImpl implements JsonRequestService {
    @Override
    public JSONObject parseJsonAndRequest(String jsonMsg, UserDto userDto) {

        JSONParser parser = new JSONParser();
        JSONObject jsonType = new JSONObject();
        JSONObject jsonAnswer = new JSONObject();
        JSONObject jsonSystemAns = new JSONObject();
        JSONObject jsonMainAns = new JSONObject();
        JSONObject jsonAllFieldsAns = new JSONObject();
        JSONArray jsonArrayFields = new JSONArray();
        JSONObject jsonField = new JSONObject();
        JSONArray locations = new JSONArray();
        JSONObject location = new JSONObject();
        JSONObject center = new JSONObject();


        try {
            jsonType = (JSONObject) parser.parse(jsonMsg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Принятый JSON " + jsonType);

        if (UpdateFields.getJsonTypeNum()==Integer.parseInt(jsonType.get("type").toString())){
            jsonAnswer.put("type", 2);
            jsonSystemAns.put("dt", 1569653340);
            jsonAnswer.put("system", jsonSystemAns);
            for (FieldDto fieldDto : userDto.getCompanies().get(0).getFields()){
                jsonField.put("id", fieldDto.getId());
                jsonField.put("name", fieldDto.getName());
                for (PointDto pointDto : fieldDto.getPoints()){
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

        return jsonAnswer;
    }


}