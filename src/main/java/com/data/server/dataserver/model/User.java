package com.data.server.dataserver.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "user_company",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName="id")
    )

    private Set<Company> companies;

}

