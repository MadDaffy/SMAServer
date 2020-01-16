package com.data.server.dataserver.dto;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
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
public class SensorDto implements Serializable {

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

    private double windSpeed;

    private double windDirection;

    private List<CompanyDto> companies;

    private List<FieldDto> fields;

}
