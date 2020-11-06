package com.epam.portal.validation;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class NewsDTOTextLengthValidator {

    private static final int TITLE_LENGTH = 100;
    private static final int BRIEF_LENGTH = 500;
    private static final int CONTENT_LENGTH = 2000;
    private static final int DATE_LENGTH = 10;

    public static boolean isTitleLengthValid(NewsDTO newsDTO) throws BusinessException {
        if (newsDTO.getTitle().length() > TITLE_LENGTH)
            throw new BusinessException("Invalid title length");

        return true;
    }

    public static boolean isBriefLengthValid(NewsDTO newsDTO) throws BusinessException {
        if (newsDTO.getBrief().length() > BRIEF_LENGTH)
            throw new BusinessException("Invalid brief length");

        return true;
    }

    public static boolean isContentLengthValid(NewsDTO newsDTO) throws BusinessException {
        if (newsDTO.getContent().length() > CONTENT_LENGTH)
            throw new BusinessException("Invalid content length");

        return true;
    }

    public static boolean isDateLengthValid(NewsDTO newsDTO) throws BusinessException {
        if (newsDTO.getNewsDate().length() > DATE_LENGTH)
            throw new BusinessException("Invalid date length");

        return true;
    }
}
