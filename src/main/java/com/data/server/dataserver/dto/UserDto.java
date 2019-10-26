package com.data.server.dataserver.dto;

import com.data.server.dataserver.model.Company;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

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

    private Set<CompanyDto> companies;

}
