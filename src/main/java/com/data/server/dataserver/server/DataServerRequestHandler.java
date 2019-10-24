package com.data.server.dataserver.server;

import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.service.JsonParseService;
import com.data.server.dataserver.service.UserService;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private JsonParseService jsonParseService;
    JSONObject jsonAnswer = new JSONObject();


    public DataServerRequestHandler(Socket s, UserService userService, JsonParseService jsonParseService) throws IOException {
        this.userService = userService;
        this.jsonParseService = jsonParseService;
        socket = s;
        in = new BufferedInputStream(socket.getInputStream());
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(socket.getOutputStream())));

        start();

    }

    public void run() {
        try {
            while (true) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = bufferedReader.readLine();
                    System.out.println(line);
                    jsonAnswer = jsonParseService.parseJsonAndCreate(line);
                    System.out.println("jsonAnswer " + jsonAnswer);
                    out.write(jsonAnswer.toJSONString());
                    out.flush();
//                userService.createUser(UserDto.builder()
//                        .fullName(line)
//                        .build());
//                System.out.println("users with login "+line+": "+userService.getAllUsersByName(line));
                }catch (NullPointerException e){
                    logger.error("NPE::: "+e.getMessage());
                }

            }
        } catch (IOException e) {
           logger.error("IO Error::: " + e.getMessage());
        }

    }
}