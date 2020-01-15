package com.data.server.dataserver.service;

import com.data.server.dataserver.dto.UserDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * JsonRequestService
 *
 * @author Dmitriy
 */
public interface JsonRequestService {
    JSONObject parseJsonAndRequest(String jsonMsg, String login) throws ParseException;
}
