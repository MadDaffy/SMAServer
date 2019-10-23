package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.UserDto;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.List;

/**
 * UserService
 *
 * @author Dmitriy
 */

public interface UserService  {
    List<UserDto> getAllUsersByName(String name);
    void createUser(UserDto userDto);
}
