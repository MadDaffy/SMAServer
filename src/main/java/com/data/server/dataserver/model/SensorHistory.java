package com.data.server.dataserver.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * SensorHistory
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
@Table(name = "sensorHistory")
public class SensorHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long idSensor;

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
    private double windSpeed;

    @Column
    private double windDirection;

    @Column
    @Lob @Type(type = "org.hibernate.type.TextType")
    private String ground;

    @Column
    private Date timeUpdate;
}
