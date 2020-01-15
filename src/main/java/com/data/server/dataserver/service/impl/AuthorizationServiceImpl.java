package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.SensorDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.service.JsonAuthService;
import com.data.server.dataserver.service.SensorService;
import com.data.server.dataserver.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.data.server.dataserver.service.impl.jsonType.Authorization;
import static com.data.server.dataserver.service.impl.jsonType.DataSensor;

/**
 * JsonParseServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class AuthorizationServiceImpl implements JsonAuthService {

    @Autowired
    UserService userService;

    @Override
    public AuthUserAnswer parseJsonAndAuth(String jsonMsg) throws ParseException {
        AuthUserAnswer authUserAnswer = new AuthUserAnswer();
        UserDto user = new UserDto();


        JSONParser parser = new JSONParser();
        JSONObject jsonType = null;
        JSONObject jsonMain = null;

        JSONObject jsonAnswer = new JSONObject();
        JSONObject jsonSystemAns = new JSONObject();
        JSONObject jsonMainAns = new JSONObject();

        jsonType = (JSONObject) parser.parse(jsonMsg);

//       System.out.println("Принятый JSON " + jsonType);

        if (Authorization.getJsonTypeNum() == Integer.parseInt(jsonType.get("type").toString())) {

            try {
                jsonMain = (JSONObject) parser.parse(jsonType.get("main").toString());
//                System.out.println("jsonMain: " + jsonMain);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user = userService.getUserByLogin(jsonMain.get("login").toString());
            if (Objects.nonNull(user)) {

                if ((jsonMain.get("password").equals(user.getPassword()))) {
//                    System.out.println("верный лог+пасс");
                    jsonAnswer.put("type", Authorization.getJsonTypeNum());
                    jsonSystemAns.put("dt", 1569653340);
                    jsonAnswer.put("system", jsonSystemAns);
                    jsonMainAns.put("login", user.getLogin());
                    jsonMainAns.put("answer", true);
                    jsonMainAns.put("fullName", user.getFullName());
                    jsonMainAns.put("companyName", getCompaniesNames(user.getCompanies()));
                    jsonAnswer.put("main", jsonMainAns);

                } else {
                    falseLogOrPass(jsonMain, jsonAnswer, jsonSystemAns, jsonMainAns);
//                    throw new Exception("Неверный пароль");
                }
            } else {
                falseLogOrPass(jsonMain, jsonAnswer, jsonSystemAns, jsonMainAns);
//                throw new Exception("Неверный логин ");
            }

        } else {
            falseLogOrPass(jsonMain, jsonAnswer, jsonSystemAns, jsonMainAns);
            System.out.println("Неверный тип запроса!!!");
        }
        authUserAnswer.setJsonObject(jsonAnswer);
        authUserAnswer.setLogin(user.getLogin());
        return authUserAnswer;

    }

    private void falseLogOrPass(JSONObject jsonMain, JSONObject jsonAnswer, JSONObject jsonSystemAns, JSONObject jsonMainAns) {
        jsonAnswer.put("type", Authorization.getJsonTypeNum());
        jsonSystemAns.put("dt", 1569653340);
        jsonAnswer.put("system", jsonSystemAns);
        jsonMainAns.put("login", jsonMain.get("login").toString());
        jsonMainAns.put("answer", false);
        jsonAnswer.put("main", jsonMainAns);
    }

    private String getCompaniesNames(List<CompanyDto> companyDtoList) {
        StringBuilder stringBuilder = new StringBuilder();

        if (companyDtoList.isEmpty()) {
            return stringBuilder.append("Not a member of the company").toString();
        }
        if (companyDtoList.size() > 1) {
            for (CompanyDto companyDto : companyDtoList) {
                stringBuilder.append(companyDto.getName()).append(", ");
            }
            return stringBuilder.toString();
        }

        return stringBuilder.append(companyDtoList.get(0).getName()).toString();
    }

}
