package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.UserDao;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserDto> getAllUsersByName(String name) {
        return userDao.getAllUsersByName(name);
    }

    @Override
    public void createUser(UserDto userDto) {
        userDao.createUser(userDto);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userDao.getUserByUsername(username);

    }
}
