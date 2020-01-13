package com.data.server.dataserver.service;

import org.json.simple.parser.ParseException;

/**
 * JsonSensorService
 *
 * @author Dmitriy
 */
public interface JsonSensorService {
    void parseSensorJson (String jsonSensor) throws ParseException;
}
