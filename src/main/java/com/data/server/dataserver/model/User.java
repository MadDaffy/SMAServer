package com.data.server.dataserver.model;

import lombok.*;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.List;

/**
 * User
 *
 * @author Dmitriy
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {
   @Id
   @GeneratedValue
   private Long id;

   @Column(unique = true)
   private String username;

   @Column
   private String password;

   @Column
   private String fullName;

   private List<GrantedAuthority> authorities;
   private boolean accountNonExpired;
   private boolean accountNonLocked;
   private boolean credentialsNonExpired;
   private boolean enabled;

   }

