package com.data.server.dataserver.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Sensor
 *
 * @author Dmitriy
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private double temperature;

    @Column
    private double humidity;

    @Column
    private double pressure;

    @Column
    private short battery;

    @Column
    private double gsmlvl;

    @Column
    @Lob @Type(type = "org.hibernate.type.TextType")
    private String ground;

    @Column
    private Date lastUpdate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SENSOR_TO_COMPANY",
            joinColumns = {@JoinColumn(name = "SENSOR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SENSOR_COMPANY_ID")})
    private List<Company> companies ;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SENSOR_TO_FIELD",
            joinColumns = {@JoinColumn(name = "SENSOR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SENSOR_FIELD_ID")})
    private List<Field> fields;
}
