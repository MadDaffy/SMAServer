package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.UserDto;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * UserDao
 *
 * @author Dmitriy
 */
@Repository
public interface UserDao {
    List<UserDto> getAllUsersByName(String name);

    void createUser(UserDto userDto);

    Optional<UserDto> findByUsername(@NotNull String username);
}
