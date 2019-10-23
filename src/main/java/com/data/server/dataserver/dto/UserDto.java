package com.data.server.dataserver.dto;

import lombok.*;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

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

    private String userName;

    private String password;

    private String fullName;

    private List<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


}
