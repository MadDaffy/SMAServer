package com.data.server.dataserver.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * SensorDto
 *
 * @author Dmitriy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SensorDto {

    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    private double temperature;

    private double humidity;

    private double pressure;

    private short battery;

    private double gsmlvl;

    private String ground;

    private Date lastUpdate;


    private List<CompanyDto> companies;

    private List<FieldDto> fields;

}
