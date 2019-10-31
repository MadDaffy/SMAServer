package com.data.server.dataserver.mapper;

import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.model.Company;
import com.data.server.dataserver.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

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

    default User toUser(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());

        fillCompanies(dto, user);

        return user;
    }

    default UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFullName());

        fillCompaniesDto(user, userDto);

        return userDto;
    }

    List<UserDto> toUserDtoList(List<User> users);

    /**
     * Update companies field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillCompanies(UserDto dto,
                               @MappingTarget User model) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getCompanies())) {
            return;
        }

        List<Company> data = new ArrayList<>();
        for (CompanyDto companyDto : dto.getCompanies()) {
            Company company = new Company();
            company.setId(companyDto.getId());
            company.setName(companyDto.getName());
            data.add(company);
        }
        model.setCompanies(data);
    }

    /**
     * Update companies dto field from Model after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillCompaniesDto(User model,
                                  @MappingTarget UserDto dto) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(model.getCompanies())) {
            return;
        }

        List<CompanyDto> data = new ArrayList<>();
        for (Company company : model.getCompanies()) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            data.add(companyDto);
        }
        dto.setCompanies(data);
    }
}
