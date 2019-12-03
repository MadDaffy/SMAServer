package com.data.server.dataserver.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Car
 *
 * @author Dmitriy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "car")
public class Car {

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
    private double speed;

    @Column
    private Date lastUpdate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;
}
