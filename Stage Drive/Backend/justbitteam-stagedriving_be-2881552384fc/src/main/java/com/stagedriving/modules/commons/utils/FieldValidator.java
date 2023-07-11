/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.utils;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simone
 */
public class FieldValidator {

    public static boolean isStringFieldValid(String value, Class clazz) {

        if (value == null || clazz == null) {
            return false;
        }

        try {
            for (Field field : clazz.getFields()) {
                if (value.equalsIgnoreCase((String) field.get(null))) {
                    return true;
                }
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FieldValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FieldValidator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
