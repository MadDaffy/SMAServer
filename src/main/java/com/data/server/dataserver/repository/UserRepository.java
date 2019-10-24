package com.data.server.dataserver.repository;

import com.data.server.dataserver.model.User;

import java.util.List;

/**
 * UserRepository
 *
 * @author Dmitriy
 */
public interface UserRepository extends BaseRepository<User, Long> {

    List<User> findAllByFullName(String fullName);

    User findUserByLogin(String login);

}
