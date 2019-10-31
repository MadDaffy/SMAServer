package com.data.server.dataserver.service;

import org.json.simple.JSONObject;

/**
 * JsonRequestService
 *
 * @author Dmitriy
 */
public interface JsonRequestService {
    JSONObject parseJsonAndRequest(String jsonMsg);
}
