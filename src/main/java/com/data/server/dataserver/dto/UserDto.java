package com.data.server.dataserver.dto;

import lombok.*;

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
@ToString
public class UserDto implements Serializable {

    private Long id;

    private String login;

    private String password;

    private String fullName;

    private List<CompanyDto> companies;

}
