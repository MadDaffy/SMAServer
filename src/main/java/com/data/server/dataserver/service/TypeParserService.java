package com.data.server.dataserver.service;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

/**
 * TypeParser
 *
 * @author Dmitriy
 */
@Service
public interface TypeParserService {
    boolean isClient(String msg) throws ParseException;
}
