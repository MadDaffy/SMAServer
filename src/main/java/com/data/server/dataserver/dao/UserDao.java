package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao
 *
 * @author Dmitriy
 */
@Repository
public interface UserDao {

    List<UserDto> getAllUsersByName(String name);

    UserDto getUserByLogin(String username);

    void createUser(UserDto userDto);

    void updateUser(UserDto userDto);


}
