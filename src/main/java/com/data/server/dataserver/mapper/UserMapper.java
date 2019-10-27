package com.data.server.dataserver.mapper;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.model.Company;
import com.data.server.dataserver.model.User;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

/**
 * UserMapper
 *
 * @author Dmitriy
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface UserMapper {

    User toUser(UserDto dto);

    List<User> toUserList(List<UserDto> dtoList);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> users);

    Set<CompanyDto> toCompanyDtoSet(Set<Company> companies);

    Set<Company> toCompanySet(Set<CompanyDto> companiesDto);

}