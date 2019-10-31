package com.data.server.dataserver.model;

import lombok.*;

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
@ToString
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "companies")
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "companies")
    private List<Field> fields;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "companies")
    private List<Sensor> sensors;
}
