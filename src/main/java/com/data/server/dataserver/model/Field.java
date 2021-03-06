package com.data.server.dataserver.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Field
 *
 * @author Dmitriy
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private double latitude;

    @Column
    private double longitude;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "FIELD_TO_COMPANY",
            joinColumns = {@JoinColumn(name = "FIELD_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FIELD_COMPANY_ID")})
    private List<Company> companies;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "fields")
    private List<Sensor> sensors;

    @OneToMany(fetch =  FetchType.LAZY, mappedBy = "field")
    private List<Point> points;
}
