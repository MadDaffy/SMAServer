package com.data.server.dataserver.model;

import lombok.*;

import javax.persistence.*;

/**
 * Point
 *
 * @author Dmitriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "FIELD_ID")
    private Field field;
}
