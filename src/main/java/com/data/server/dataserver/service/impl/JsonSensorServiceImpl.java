package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.model.Sensor;
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


        Sensor sensor = new Sensor();
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
            sensor = sensorService.findSensor( Long.parseLong(jsonMain.get("id").toString()));
            date = new Date();

            sensorService.updateSensor(Sensor.builder()
                    .id( Long.parseLong(jsonMain.get("id").toString()))
                    .temperature( Double.parseDouble(jsonMain.get("temperature").toString()))
                    .humidity( Double.parseDouble(jsonMain.get("humidity").toString()))
                    .pressure( Double.parseDouble(jsonMain.get("pressure").toString()))
                    .battery(Short.parseShort( jsonMain.get("battery").toString()))
                    .gsmlvl(Double.parseDouble(jsonMain.get("gsmlvl").toString()))
                    .ground(jsonMain.get("ground").toString())
                    .windSpeed(Double.parseDouble(jsonMain.get("windSpeed").toString()))
                    .windDirection(Double.parseDouble(jsonMain.get("windDirection").toString()))
                    .latitude(sensor.getLatitude())
                    .longitude(sensor.getLongitude())
                    .name(sensor.getName())
                    .companies(sensor.getCompanies())
                    .fields(sensor.getFields())
                    .lastUpdate(date)
                    .build());

        }
    }
}
