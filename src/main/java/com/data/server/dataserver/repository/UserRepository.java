package com.data.server.dataserver.repository;

import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.model.User;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository
 *
 * @author Dmitriy
 */
public interface UserRepository extends BaseRepository<User, Long> {

    List<User> findAllByFullName(String fullName);

    User findUserByLogin(String login);

}
