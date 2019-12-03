package com.data.server.dataserver.dto;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * CarDto
 *
 * @author Dmitriy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto implements Serializable {

    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    private double speed;

    private Date lastUpdate;

    private CompanyDto companyDto;

}
