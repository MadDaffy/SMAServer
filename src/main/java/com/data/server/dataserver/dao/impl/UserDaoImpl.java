package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.UserDao;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.mapper.UserMapper;
import com.data.server.dataserver.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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
    public UserDto getUserByLogin(String username) {
        //TODO не надо менять метод только в самом последнем вызове. У тебя в итоге метод называется
        // поиск по имени, а в итоге поиск по логину, в который ты еще и передаешь имя.
        // Либо меняй всю цепочку методов, либо создавай новый.
        return userMapper.toUserDto(userRepository.findUserByLogin(username));
    }

    @Override
    public void updateUser(UserDto userDto) {
        userRepository.saveAndFlush(userMapper.toUser(userDto));
    }
}
