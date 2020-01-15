package com.data.server.dataserver.dao;

import com.data.server.dataserver.dto.CompanyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CompanyDto
 *
 * @author Dmitriy
 */
@Repository
public interface CompanyDao {
    void creatCompany(CompanyDto companyDto);
}
