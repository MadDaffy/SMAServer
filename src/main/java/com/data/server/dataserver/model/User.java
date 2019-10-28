package com.data.server.dataserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_TO_COMPANY",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_COMPANY_ID")})
    private List<Company> companies = new ArrayList<>();
}

