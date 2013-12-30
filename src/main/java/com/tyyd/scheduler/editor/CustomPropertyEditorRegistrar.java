/*
 * 
 */
// Created on 2013-9-4

package com.tyyd.scheduler.editor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

/**
 * @author joe.chen
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {

        registry.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));
    }

}
