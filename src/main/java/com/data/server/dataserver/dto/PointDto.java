package com.data.server.dataserver.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * PointDto
 *
 * @author Dmitriy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointDto {

    private Long id;

    private double latitude;

    private double longitude;

    private FieldDto fieldDto;
}
