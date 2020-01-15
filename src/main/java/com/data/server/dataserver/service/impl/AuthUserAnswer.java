package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;

/**
 * AuthUserAnswer
 *
 * @author Dmitriy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserAnswer {
    String login;
    JSONObject jsonObject;
}
