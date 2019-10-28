package com.data.server.dataserver;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.server.DataServerRequestHandler;
import com.data.server.dataserver.service.JsonParseService;
import com.data.server.dataserver.service.UserService;
import com.data.server.dataserver.service.impl.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DataServerApplication implements CommandLineRunner {

    final static Logger logger = Logger.getLogger(DataServerApplication.class);

    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    JsonParseService jsonParseService;

    public static void main(String[] args) {
        SpringApplication.run(DataServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
        logger.info("Server started");

        //TODO можно удалить до блока try, это я проверял как работает все
        companyService.createCompany(CompanyDto.builder()
                                               .name("OOO test")
                                               .build());
        CompanyDto companyDto = companyService.getAllByCompanyName("OOO test").get(0);
        List<CompanyDto> companyDtos = new ArrayList<>();
        companyDtos.add(companyDto);
        userService.createUser(UserDto.builder()
                                      .login("Kek roflan")
                                      .build());
        UserDto userDto = userService.getUserByLogin("Kek roflan");

        userDto.setCompanies(companyDtos);
        userService.updateUser(userDto);


        userDto = userService.getUserByLogin("Kek roflan");

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("new socked");
                try {
                    new DataServerRequestHandler(socket, userService, jsonParseService);
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
