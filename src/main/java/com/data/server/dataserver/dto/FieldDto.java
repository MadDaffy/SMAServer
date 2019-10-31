package com.data.server.dataserver.dto;

import lombok.*;

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
public class FieldDto {

    private Long id;

    private String name;

    private String location;

    private String centre;


    private List<CompanyDto> companies;

    private List<SensorDto> sensors;
}
