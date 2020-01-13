package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.service.JsonSensorService;
import com.data.server.dataserver.service.SensorService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.data.server.dataserver.service.impl.jsonType.DataSensor;

/**
 * JsonSensorServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class JsonSensorServiceImpl implements JsonSensorService {
    @Autowired
    SensorService sensorService;

    @Override
    public void parseSensorJson(String jsonSensor) throws ParseException {


        SensorDto sensorDto = new SensorDto();
        Date date;
        JSONParser parser = new JSONParser();
        JSONObject jsonType = null;
        JSONObject jsonMain = null;

        jsonType = (JSONObject) parser.parse(jsonSensor);

        if(DataSensor.getJsonTypeNum() == Integer.parseInt(jsonType.get("type").toString())){
//            try {
            jsonMain = (JSONObject) parser.parse(jsonType.get("main").toString());
//                System.out.println("jsonMain: " + jsonMain);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            sensorDto = sensorService.findSensor( Long.parseLong(jsonMain.get("id").toString()));
            date = new Date();

            sensorService.updateSensor(SensorDto.builder()
                    .id( Long.parseLong(jsonMain.get("id").toString()))
                    .temperature( Double.parseDouble(jsonMain.get("temperature").toString()))
                    .humidity( Double.parseDouble(jsonMain.get("humidity").toString()))
                    .pressure( Double.parseDouble(jsonMain.get("pressure").toString()))
                    .battery(Short.parseShort( jsonMain.get("battery").toString()))
                    .gsmlvl(Double.parseDouble(jsonMain.get("gsmlvl").toString()))
                    .ground(jsonMain.get("ground").toString())
                    .latitude(sensorDto.getLatitude())
                    .longitude(sensorDto.getLongitude())
                    .name(sensorDto.getName())
                    .companies(sensorDto.getCompanies())
                    .fields(sensorDto.getFields())
                    .lastUpdate(date)
                    .build());

        }
    }
}
