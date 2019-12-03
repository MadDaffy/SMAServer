package com.data.server.dataserver.model;

import lombok.*;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "companies")
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "companies")
    private List<Field> fields;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "companies")
    private List<Sensor> sensors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Car> cars;
}
