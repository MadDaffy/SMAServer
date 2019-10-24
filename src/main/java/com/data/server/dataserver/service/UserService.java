package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.UserDto;

import java.util.List;

/**
 * UserService
 *
 * @author Dmitriy
 */

public interface UserService {
    List<UserDto> getAllUsersByName(String name);

    UserDto getUserByUsername(String username);

    void createUser(UserDto userDto);
}
