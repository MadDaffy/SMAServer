package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.service.JsonParseService;
import com.data.server.dataserver.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * JsonParseServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class JsonParseServiceImpl implements JsonParseService {
    @Autowired
    UserService userService;

    @Override
    public void parseJsonAndCreate(String jsonMsg) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(jsonMsg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Принятый JSON" + json);
        JSONArray jsonArray = (JSONArray) json.get("users");
        List<UserDto> userDtoList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            userDtoList.add(UserDto.builder()
                    .fullName(((JSONObject) jsonArray.get(i)).get("name").toString())
                    .login(((JSONObject) jsonArray.get(i)).get("login").toString())
                    .password(((JSONObject) jsonArray.get(i)).get("password").toString())
                    .build());
        }

        System.out.println("Распарсили JSON и получили users list " + userDtoList);

        for (int i = 0; i < userDtoList.size(); i++) {
            userService.createUser(userDtoList.get(i));
        }
    }
}
