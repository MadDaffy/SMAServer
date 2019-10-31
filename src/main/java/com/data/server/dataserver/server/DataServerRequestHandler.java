package com.data.server.dataserver.server;

import com.data.server.dataserver.service.JsonAuthService;
import com.data.server.dataserver.service.UserService;
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
    private JsonAuthService jsonParseService;
    JSONObject jsonAnswer = new JSONObject();


    public DataServerRequestHandler(Socket s, UserService userService, JsonAuthService jsonParseService) throws IOException {
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String authQuery = bufferedReader.readLine();
            System.out.println(authQuery);
            jsonAnswer = jsonParseService.parseJsonAndAuth(authQuery);
            System.out.println("jsonAnswer " + jsonAnswer);
            out.write(jsonAnswer.toJSONString());
            out.flush();

            while (true) {
                try {
                    String query = bufferedReader.readLine();
                    System.out.println("query "+query);

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