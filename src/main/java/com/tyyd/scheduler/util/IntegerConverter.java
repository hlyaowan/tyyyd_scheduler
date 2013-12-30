/*
 * 
 */
// Created on 2013-7-15

package com.tyyd.scheduler.util;

import org.springframework.core.convert.converter.Converter;


/**
 * @author joe.chen
 */
public class IntegerConverter extends ParameterConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        Integer result = null;
        try {
            result = new Integer(source);
        } catch (Exception ex) {
            logger.error("spring mvc request parameter converter is error!", ex);
        }
        return result;
    }

}
