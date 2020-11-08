package com.epam.portal.validation;

import com.epam.portal.exception.BusinessException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class TextForbiddenWordValidator {

    public static void isNotContainsForbiddenWords(String inputText) throws BusinessException {
        if (null == inputText) {
            throw new BusinessException("Empty field");
        }
        String[] inputTextAsWordArray = inputText.split(" ");
        List<String> items = getWords();
        for (String item : items) {
            for (String s : inputTextAsWordArray) {
                if (s.toLowerCase().equals(item)) {
                    throw new BusinessException("Text contains forbidden words");
                }
            }
        }
    }

    private static List<String> getWords() throws BusinessException {
        try {
            String line = "";
            File file = new ClassPathResource("/forbiddenWords.txt").getFile();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> wordsList = new LinkedList<>();
            while ((line = bufferedReader.readLine()) != null) {
                wordsList.add(line.toLowerCase());
            }
            bufferedReader.close();
            return wordsList;
        } catch (IOException ex) {
            throw new BusinessException("Cannot reach forbidden words");
        }
    }
}