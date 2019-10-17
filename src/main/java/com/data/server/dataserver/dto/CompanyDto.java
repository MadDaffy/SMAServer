package com.data.server.dataserver.dto;

import lombok.*;

import java.io.Serializable;

/**
 * CompanyDto
 *
 * @author Dmitriy
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {

    private Long id;
    private String name;

}
