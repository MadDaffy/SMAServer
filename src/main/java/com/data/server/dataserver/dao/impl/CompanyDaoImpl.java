package com.data.server.dataserver.dao.impl;

import com.data.server.dataserver.dao.CompanyDao;
import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.mapper.CompanyMapper;
import com.data.server.dataserver.repository.CompanyRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CompanyDtoImpl
 *
 * @author Dmitriy
 */

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {

    CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);
    @Autowired
    CompanyRepository companyRepository;

    @Override
    @Transactional
    public List<CompanyDto> getAllCompaniesByName(String name){
        return companyMapper.toCompanyDtoList(companyRepository.findAllByName(name));
    }

    @Override
    @Transactional
    public void creatCompany(CompanyDto companyDto) {
        companyRepository.save(companyMapper.toCompany(companyDto));
    }

}
