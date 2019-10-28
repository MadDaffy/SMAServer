package com.data.server.dataserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

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
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "companies")
    private List<User> users;
}
