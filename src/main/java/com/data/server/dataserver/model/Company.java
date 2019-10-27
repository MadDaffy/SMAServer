package com.data.server.dataserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Company
 *
 * @author Dmitriy
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String name;

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "companies")
//    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "companies")
    private Set<User> users;
}
