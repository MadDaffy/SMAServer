package com.data.server.dataserver.service;

import com.data.server.dataserver.service.impl.AuthUserAnswer;
import org.json.simple.JSONObject;

/**
 * JsonParseService
 *
 * @author Dmitriy
 */
public interface JsonAuthService {
    AuthUserAnswer parseJsonAndAuth(String jsonMsg) throws Exception;
}
