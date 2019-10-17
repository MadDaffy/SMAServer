package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.CompanyDto;

import java.util.List;

/**
 * CompanyDto
 *
 * @author Dmitriy
 */
public interface CompanyDao {
    List<CompanyDto> getAllCompaniesByName(String name);

    void creatCompany(CompanyDto companyDto);
}
