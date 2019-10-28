package com.data.server.dataserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * UserDto
 *
 * @author Dmitriy
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    private String login;

    private String password;

    private String fullName;

    private List<CompanyDto> companies;

}
