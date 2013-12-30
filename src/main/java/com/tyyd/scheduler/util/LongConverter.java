/*
 * 
 */
// Created on 2013-8-7

package com.tyyd.scheduler.util;

import org.springframework.core.convert.converter.Converter;


/**
 * @author joe.chen
 */
public class LongConverter extends ParameterConverter implements Converter<String, Long> {

    @Override
    public Long convert(String source) {
        Long result = null;
        try {
            result = new Long(source);
        } catch (Exception ex) {
            logger.error("spring mvc request parameter converter is error!", ex);
        }
        return result;
    }
}
