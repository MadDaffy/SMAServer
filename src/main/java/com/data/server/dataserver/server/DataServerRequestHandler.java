package com.data.server.dataserver.server;

import com.data.server.dataserver.service.JsonAuthService;
import com.data.server.dataserver.service.JsonRequestService;
import com.data.server.dataserver.service.UserService;
import com.data.server.dataserver.service.impl.AuthUserAnswer;
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


    public DataServerRequestHandler(Socket s, UserService userService, JsonAuthService jsonAuthService, JsonRequestService jsonRequestService) throws IOException {
        this.userService = userService;
        this.jsonAuthService = jsonAuthService;
        this.jsonRequestService = jsonRequestService;
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
            if(authQuery==null){
                System.out.println("disconnect at auth: "+ socket.getInetAddress().toString());
                throw new Exception("socket closed ");
            }
            System.out.println(authQuery);

            authUserAnswer = jsonAuthService.parseJsonAndAuth(authQuery);
            System.out.println("jsonAnswer " + authUserAnswer.getJsonObject());
            out.write(authUserAnswer.getJsonObject().toJSONString());
            out.flush();
            while (true) {
                System.out.println("Ждем запросов к бд");

                    String jsonQuery = bufferedReader.readLine();
                    if(jsonQuery==null){
                        System.out.println("disconnect at work: "+ socket.getInetAddress().toString());
                        throw new Exception("socket closed ");
                    }
                    System.out.println(jsonQuery);
                    jsonAnswer = jsonRequestService.parseJsonAndRequest(jsonQuery, authUserAnswer.getUserDto());
                    System.out.println("jsonAnswer " + jsonAnswer);
                    out.write(jsonAnswer.toJSONString());
                    out.flush();


//                userService.createUser(UserDto.builder()
//                        .fullName(line)
//                        .build());
//                System.out.println("users with login "+line+": "+userService.getAllUsersByName(line));


            }
        } catch (Exception e) {
            logger.error("Error::: " + e.getMessage());
        }

    }
}