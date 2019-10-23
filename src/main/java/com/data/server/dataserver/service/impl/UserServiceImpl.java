package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.UserDao;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.model.User;
import com.data.server.dataserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return userDao.findByUsername(username).orElse(null);

    }
}
