package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.service.JsonParseService;
import com.data.server.dataserver.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.data.server.dataserver.service.impl.jsonType.Authorization;

/**
 * JsonParseServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class AuthorizationServiceImpl implements JsonParseService {
    @Autowired
    UserService userService;
    CompanyService companyService;

    @Override
    public JSONObject parseJsonAndCreate(String jsonMsg) {
        JSONParser parser = new JSONParser();
        JSONObject jsonType = null;
        JSONObject jsonMain = null;

        JSONObject jsonAnswer = new JSONObject();
        JSONObject jsonSystemAns = new JSONObject();
        JSONObject jsonMainAns = new JSONObject();
        try {
            jsonType = (JSONObject) parser.parse(jsonMsg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Принятый JSON " + jsonType);


        if (Authorization.getJsonTypeNum()==1) {
            System.out.println("jsonType Authorization");
            try {
                jsonMain = (JSONObject) parser.parse(jsonType.get("main").toString());
                System.out.println("jsonMain: " + jsonMain);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (userService.getUserByLogin(jsonMain.get("login").toString()) != null) {

                if ((jsonMain.get("password")
                        .equals(userService
                                .getUserByLogin(jsonMain.get("login").toString())
                                .getPassword()))) {
                    System.out.println("верный лог+пасс");
                    jsonAnswer.put("type", Authorization.getJsonTypeNum());
                    jsonSystemAns.put("dt", 1569653340);
                    jsonAnswer.put("system", jsonSystemAns);
                    jsonMainAns.put("login", userService
                            .getUserByLogin(jsonMain
                                    .get("login").toString())
                            .getLogin());
                    jsonMainAns.put("answer", true);
                    jsonMainAns.put("fullname", userService
                            .getUserByLogin(jsonMain
                                    .get("login").toString())
                            .getFullName());
                    jsonMainAns.put("companyName", userService
                                    .getUserByLogin(jsonMain.get("login").toString())
                                    .getCompanies());
                    jsonAnswer.put("main", jsonMainAns);

                } else {
                    falseLogOrPass(jsonMain, jsonAnswer, jsonSystemAns, jsonMainAns);
                    System.out.println("Неверный пароль");

                }
            } else {
                falseLogOrPass(jsonMain, jsonAnswer, jsonSystemAns, jsonMainAns);
                System.out.println("Неверный логин ");
            }
//        JSONArray jsonArray = (JSONArray) json.get("users");
//        List<UserDto> userDtoList = new ArrayList<>();
//
//        for (int i = 0; i < jsonArray.size(); i++) {
//            userDtoList.add(UserDto.builder()
//                    .fullName(((JSONObject) jsonArray.get(i)).get("name").toString())
//                    .username(((JSONObject) jsonArray.get(i)).get("login").toString())
//                    .password(((JSONObject) jsonArray.get(i)).get("password").toString())
//                    .build());
//        }
//
//        System.out.println("Распарсили JSON и получили users list " + userDtoList);
//
//        for (int i = 0; i < userDtoList.size(); i++) {
//            userService.createUser(userDtoList.get(i));
//        }
        } else System.out.println("Неверный type");
        return jsonAnswer;
    }

    private void falseLogOrPass(JSONObject jsonMain, JSONObject jsonAnswer, JSONObject jsonSystemAns, JSONObject jsonMainAns) {
        jsonAnswer.put("type", Authorization.getJsonTypeNum());
        jsonSystemAns.put("dt", 1569653340);
        jsonAnswer.put("system", jsonSystemAns);
        jsonMainAns.put("login", jsonMain.get("login").toString());
        jsonMainAns.put("answer", false);
        jsonAnswer.put("main", jsonMainAns);
    }
}