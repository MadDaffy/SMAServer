package com.data.server.dataserver.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * CompanyDto
 *
 * @author Dmitriy
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyDto implements Serializable {

    private Long id;

    private String name;

    private List<UserDto> users;

    private List<SensorDto> sensors;

    private List<FieldDto> fields;

    private List<CarDto> cars;

}
