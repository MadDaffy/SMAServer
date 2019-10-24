package com.data.server.dataserver.model;

import lombok.*;

import javax.persistence.*;

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
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @Column
    private String fullName;
}

