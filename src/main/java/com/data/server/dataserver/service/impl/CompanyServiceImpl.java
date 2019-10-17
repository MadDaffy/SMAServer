package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.CompanyDao;
import com.data.server.dataserver.dto.CompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CompanyServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyDao companyDao;

    @Override
    public List<CompanyDto> getAllByCompanyName(String name) {
        return companyDao.getAllCompaniesByName(name);
    }

    @Override
    public void createCompany(CompanyDto companyDto) {
        companyDao.creatCompany(companyDto);
    }
}
