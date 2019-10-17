package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.CompanyDto;

import java.util.List;

/**
 * CompanyService
 *
 * @author Dmitriy
 */
public interface CompanyService {
    List<CompanyDto> getAllByCompanyName(String name);

    void createCompany(CompanyDto companyDto);
}
