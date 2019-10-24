package com.data.server.dataserver.service;

import org.json.simple.JSONObject;

/**
 * JsonParseService
 *
 * @author Dmitriy
 */
public interface JsonParseService {
    JSONObject parseJsonAndCreate(String jsonMsg);
    
}
