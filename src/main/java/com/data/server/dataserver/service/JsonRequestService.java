package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.UserDto;
import org.json.simple.JSONObject;

/**
 * JsonRequestService
 *
 * @author Dmitriy
 */
public interface JsonRequestService {
    JSONObject parseJsonAndRequest(String jsonMsg, UserDto userDto);
}
