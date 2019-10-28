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
 * CompanyMapper
 *
 * @author Dmitriy
 */

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CompanyMapper {

    default Company toCompany(CompanyDto companyDto) {
        if (companyDto == null) {
            return null;
        }

        Company company = new Company();

        company.setId(companyDto.getId());
        company.setName(companyDto.getName());

        fillUsers(companyDto, company);

        return company;
    }

    default CompanyDto toCompanyDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setName(company.getName());

        fillUsersDto(company, companyDto);

        return companyDto;
    }

    List<CompanyDto> toCompanyDtoList(List<Company> companies);

    /**
     * Update users field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillUsers(CompanyDto dto,
                           @MappingTarget Company model) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getUsers())) {
            return;
        }

        List<User> data = new ArrayList<>();
        for (UserDto userDto : dto.getUsers()) {
            User user = new User();
            user.setId(userDto.getId());
            user.setFullName(userDto.getFullName());
            user.setLogin(userDto.getLogin());
            user.setPassword(userDto.getPassword());
            data.add(user);
        }
        model.setUsers(data);
    }

    /**
     * Update companies dto field from Model after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillUsersDto(Company model,
                              @MappingTarget CompanyDto dto) {
        if (isNull(model) || isNull(dto)) {
            return;
        }
        if (isEmpty(dto.getUsers())) {
            return;
        }

        List<UserDto> data = new ArrayList<>();
        for (User user : model.getUsers()) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFullName(user.getFullName());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
            data.add(userDto);
        }
        dto.setUsers(data);
    }
}
