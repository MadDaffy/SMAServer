package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dao.CompanyDao;
import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CompanyServiceImpl
 *
 * @author Dmitriy
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;

    @Override
    public void createCompany(CompanyDto companyDto) {
        companyDao.creatCompany(companyDto);
    }
}
