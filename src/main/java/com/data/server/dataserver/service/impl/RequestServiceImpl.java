package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.service.JsonRequestService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.data.server.dataserver.service.impl.jsonType.Authorization;
import static com.data.server.dataserver.service.impl.jsonType.UpdateFields;

/**
 * RequestServiceImpl
 *
 * @author Dmitriy
 */
public class RequestServiceImpl implements JsonRequestService {
    @Override
    public JSONObject parseJsonAndRequest(String jsonMsg) {
//        JSONParser parser = new JSONParser();
//        JSONObject jsonType = null;
//        JSONObject jsonAnswer = null;
//        JSONObject jsonSystemAns = null;
//        JSONObject jsonMainAns = null;
//        JSONObject jsonFieldsAns = null;
//        JSONArray jsonArrayFields = null;
//
//        try {
//            jsonType = (JSONObject) parser.parse(jsonMsg);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Принятый JSON " + jsonType);
//
//        if (UpdateFields.getJsonTypeNum()==Integer.parseInt(jsonType.get("type").toString())){
//            jsonAnswer.put("type", UpdateFields.getJsonTypeNum());
//            jsonSystemAns.put("dt", 1569653340);
//            jsonAnswer.put("system", jsonSystemAns);
//            jsonFieldsAns.put()
//
//        }

        return null;
    }
}
