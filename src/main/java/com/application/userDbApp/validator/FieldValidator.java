package com.application.userDbApp.validator;

import java.util.List;

/**
 * Created by james on 04/06/2016.
 */
public interface FieldValidator {

    default boolean isNotNull(String s) {
        return !s.isEmpty() && s != null;
    }

}
