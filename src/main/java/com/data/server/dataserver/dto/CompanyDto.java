package com.data.server.dataserver.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

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
public class CompanyDto implements Serializable {

    private Long id;

    private String name;

    private Set<UserDto> users;

}
