package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.service.TypeParserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import static com.data.server.dataserver.service.impl.jsonType.*;

/**
 * TypeParserServiceImpl
 *
 * @author Dmitriy
 */

public class TypeParserServiceImpl implements TypeParserService {

    @Override
    public boolean isClient(String msg) throws ParseException {
        if(DataSensor.getJsonTypeNum() == parseType(msg)){
            return false;
        }
        return true;
    }

    private int parseType(String msg) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonType = null;

        jsonType = (JSONObject) parser.parse(msg);

        return Integer.parseInt(jsonType.get("type").toString());
    }


}
