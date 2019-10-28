package com.data.server.dataserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class CompanyDto implements Serializable {

    private Long id;

    private String name;

    private List<UserDto> users;

}
