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
@Table(name = "point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FIELD_ID")
    private Field field;
}
