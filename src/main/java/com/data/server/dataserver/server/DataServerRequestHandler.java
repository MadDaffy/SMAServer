package com.data.server.dataserver.server;

import com.data.server.dataserver.service.*;
import com.data.server.dataserver.service.impl.AuthUserAnswer;
import com.data.server.dataserver.service.impl.JsonSensorServiceImpl;
import com.data.server.dataserver.service.impl.TypeParserServiceImpl;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;

/**
 * DataServerRequestHandler
 *
 * @author Dmitriy
 */

public class DataServerRequestHandler extends Thread {

    final static Logger logger = Logger.getLogger(DataServerRequestHandler.class);

    private UserService userService;
    private Socket socket;
    private BufferedInputStream in;
    private BufferedWriter out;
    private JsonAuthService jsonAuthService;
    private JsonRequestService jsonRequestService;
    JSONObject jsonAnswer = new JSONObject();
    AuthUserAnswer authUserAnswer = new AuthUserAnswer();
    private JsonSensorService jsonSensorService;
    private TypeParserServiceImpl typeParserService = new TypeParserServiceImpl();


    public DataServerRequestHandler(Socket s, UserService userService, JsonAuthService jsonAuthService, JsonRequestService jsonRequestService, JsonSensorService jsonSensorService) throws IOException {
        this.userService = userService;
        this.jsonAuthService = jsonAuthService;
        this.jsonRequestService = jsonRequestService;
        this.jsonSensorService = jsonSensorService;
        socket = s;
        in = new BufferedInputStream(socket.getInputStream());
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(socket.getOutputStream())));
        start();

    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String authQuery = bufferedReader.readLine();
            System.out.println(authQuery);
            if (authQuery == null) {
                System.out.println("disconnect at auth: " + socket.getInetAddress().toString());
                throw new Exception("socket closed ");
            }

            if(!typeParserService.isClient(authQuery)) {
                System.out.println("Update Data Sensors request:: "+authQuery);

                jsonSensorService.parseSensorJson(authQuery);
                socket.close();
                System.out.println("Sensor socket "+socket.getInetAddress().toString()+ " closed");
                return;
            }
            System.out.println("Authorization request:: " + authQuery);
            authUserAnswer = jsonAuthService.parseJsonAndAuth(authQuery);
            System.out.println("Authorization response:: " + authUserAnswer.getJsonObject());
            out.write(authUserAnswer.getJsonObject().toJSONString());
            out.flush();
            while (true) {
//                System.out.println("Ждем запросов к бд");

                String jsonQuery = bufferedReader.readLine();
                if (jsonQuery == null) {
                    System.out.println("disconnect at work: " + socket.getInetAddress().toString());
                    throw new Exception("socket closed ");
                }
                    System.out.println("request:: "+jsonQuery);
                jsonAnswer = jsonRequestService.parseJsonAndRequest(jsonQuery, authUserAnswer.getLogin());
                   System.out.println("response:: " + jsonAnswer);
                out.write(jsonAnswer.toJSONString());
                out.flush();


//                userService.createUser(UserDto.builder()
//                        .fullName(line)
//                        .build());
//                System.out.println("users with login "+line+": "+userService.getAllUsersByName(line));


            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error::: " + e.getMessage());
        }

    }
}