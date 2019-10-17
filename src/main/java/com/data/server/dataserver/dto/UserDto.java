package com.data.server.dataserver.dto;

import lombok.*;

import java.io.Serializable;

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
}
