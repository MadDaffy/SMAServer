package com.data.server.dataserver.service;

import org.json.simple.JSONObject;

/**
 * JsonParseService
 *
 * @author Dmitriy
 */
public interface JsonAuthService {
    JSONObject parseJsonAndAuth(String jsonMsg);
}
