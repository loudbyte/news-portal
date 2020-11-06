package com.epam.portal.validation;

import com.epam.portal.dto.NewsDTO;
import com.epam.portal.exception.BusinessException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewsDTOTextLengthValidatorTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void isContentLengthValid() throws BusinessException {
        exceptionRule.expect(BusinessException.class);
        exceptionRule.expectMessage("Invalid content length");
        NewsDTO newsDTO = new NewsDTO();
        StringBuilder stringLengthMoreThan2000 = new StringBuilder();

        for (int counter = 0; counter < 201; counter++) {
            stringLengthMoreThan2000.append("0123456789");

        }
        newsDTO.setContent(stringLengthMoreThan2000.toString());
        NewsDTOTextLengthValidator.isContentLengthValid(newsDTO);
    }
}