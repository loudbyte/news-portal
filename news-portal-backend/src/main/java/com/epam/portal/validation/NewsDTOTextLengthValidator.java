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

    //You are able to cahnge return boolean value to void and return nothing
    public static boolean isTitleLengthValid(NewsDTO newsDTO) throws BusinessException {
        //TODO there could be NPE if newsDTO.getTitle()==null
        if (newsDTO.getTitle().length() > TITLE_LENGTH)
            throw new BusinessException("Invalid title length");

        return true;
    }

    //You are able to cahnge return boolean value to void and return nothing
    public static boolean isBriefLengthValid(NewsDTO newsDTO) throws BusinessException {
        //TODO there could be NPE if newsDTO.getBrief()==null
        if (newsDTO.getBrief().length() > BRIEF_LENGTH)
            throw new BusinessException("Invalid brief length");

        return true;
    }

    //You are able to cahnge return boolean value to void and return nothing
    public static boolean isContentLengthValid(NewsDTO newsDTO) throws BusinessException {
         //TODO there could be NPE if newsDTO.getContent()
        if (newsDTO.getContent().length() > CONTENT_LENGTH)
            throw new BusinessException("Invalid content length");

        return true;
    }

    //You are able to cahnge return boolean value to void and return nothing
    public static boolean isDateLengthValid(NewsDTO newsDTO) throws BusinessException {
        //TODO there could be NPE if newsDTO.getNewsDate()
        if (newsDTO.getNewsDate().length() > DATE_LENGTH)
            throw new BusinessException("Invalid date length");

        return true;
    }
}
