package com.data.server.dataserver;

import com.data.server.dataserver.server.DataServerRequestHandler;
import com.data.server.dataserver.service.JsonAuthService;
import com.data.server.dataserver.service.JsonRequestService;
import com.data.server.dataserver.service.UserService;
import com.data.server.dataserver.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class DataServerApplication implements CommandLineRunner {

    final static Logger logger = Logger.getLogger(DataServerApplication.class);

    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    JsonAuthService jsonParseService;
    @Autowired
    JsonRequestService jsonRequestService;

    public static void main(String[] args) {
        SpringApplication.run(DataServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
        logger.info("Server started");

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("new socked");
                try {
                    new DataServerRequestHandler(socket, userService, jsonParseService, jsonRequestService);
                } catch (IOException e) {
                    socket.close();
                    logger.error("socked closed");
                }
            }
        } finally {
            serverSocket.close();
        }
    }
}
