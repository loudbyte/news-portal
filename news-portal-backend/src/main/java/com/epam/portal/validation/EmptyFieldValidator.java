package com.epam.portal.validation;

import com.epam.portal.exception.BusinessException;

import java.util.regex.Pattern;

public class EmptyFieldValidator {

    private static final String EMPTY_STRING = "";
    private static final String SPACE_REGEX = "^[ ]";

    public static boolean isNotEmptyField(String field) throws BusinessException {
        //TODO there os better to use null==field insetad of 'field == null'
        if (field == null || field.equals(EMPTY_STRING) || Pattern.compile(SPACE_REGEX).matcher(field).find())
            throw new BusinessException("Empty field");
        return true;
    }
}
