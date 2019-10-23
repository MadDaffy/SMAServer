package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.UserDao;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.mapper.UserMapper;
import com.data.server.dataserver.repository.UserRepository;
import com.sun.istack.internal.NotNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * UserDaoImpl
 *
 * @author Dmitriy
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public List<UserDto> getAllUsersByName(String name) {
        return userMapper.toUserDtoList(userRepository.findAllByFullName(name));
    }

    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        userRepository.save(userMapper.toUser(userDto));
    }

    @Override
    @Transactional
    public Optional<UserDto> findByUsername(@NotNull String username) {
        return Optional.ofNullable(userMapper.toUserDto(userRepository.findUserByLogin(username)));
    }


}
