package com.data.server.dataserver.mapper;

import com.data.server.dataserver.dto.CarDto;
import com.data.server.dataserver.dto.CompanyDto;
import com.data.server.dataserver.model.Car;
import com.data.server.dataserver.model.Company;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static java.util.Objects.isNull;

/**
 * CarMapper
 *
 * @author Dmitriy
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CarMapper {
    default Car toCar(CarDto carDto) {
        if (carDto == null) {
            return null;
        }

        Car car = new Car();

        car.setId(carDto.getId());
        car.setLatitude(carDto.getLatitude());
        car.setLongitude(carDto.getLongitude());
        car.setLastUpdate(carDto.getLastUpdate());
        car.setName(carDto.getName());
        car.setSpeed(carDto.getSpeed());


        fillCompany(carDto, car);

        return car;
    }

    default CarDto toCarDto(Car car) {
        if (car == null) {
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setId(car.getId());
        carDto.setLatitude(car.getLatitude());
        carDto.setLongitude(car.getLongitude());
        carDto.setLastUpdate(car.getLastUpdate());
        carDto.setName(car.getName());
        carDto.setSpeed(car.getSpeed());

        fillCompanyDto(car, carDto);

        return carDto;
    }

    /**
     * Update Companies field from Dto after mapping.
     *
     * @param dto   dto object
     * @param model model object
     */
    @AfterMapping
    default void fillCompany(CarDto dto,
                           @MappingTarget Car model){
        if(isNull(model) || isNull(dto)) {
            return;
        }

        Company company = new Company();
        CompanyDto companyDto = dto.getCompanyDto();
        company.setId(companyDto.getId());
        company.setName(companyDto.getName());

        model.setCompany(company);
    }

    @AfterMapping
    default void fillCompanyDto(Car model,
                              @MappingTarget CarDto dto){
        if(isNull(model) || isNull(dto)){
            return;
        }


        CompanyDto companyDto = new CompanyDto();
        Company company = model.getCompany();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());

        dto.setCompanyDto(companyDto);;
    }

}

