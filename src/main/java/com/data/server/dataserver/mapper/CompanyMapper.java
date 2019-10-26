package com.data.server.dataserver.mapper;

import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.dto.UserDto;
import com.data.server.dataserver.model.Company;
import com.data.server.dataserver.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

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

    Company toCompany(CompanyDto companyDto);

    List<Company> toCompanyList(List<CompanyDto> dtoList);

    CompanyDto toCompanyDto(Company company);

    List<CompanyDto> toCompanyDtoList(List<Company> companies);

    Set<UserDto> toUserDtoSet(Set<User> users);

    Set<User> toUserSet(Set<UserDto> usersDto);
}
