package com.data.server.dataserver.dto;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * FieldDto
 *
 * @author Dmitriy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FieldDto implements Serializable {

    private Long id;

    private String name;

    private double latitude;

    private double longitude;


    private List<CompanyDto> companies;

    private List<SensorDto> sensors;

    private List<PointDto> points;
}
