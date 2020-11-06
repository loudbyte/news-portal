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

    public static boolean isNotContainsForbiddenWords(String inputText) throws BusinessException {
        if (inputText == null) {
            throw new BusinessException("Empty field");
        }
        String[] inputTextAsWordArray = inputText.split(" ");
        List<String> items = getWords();
        for (int counterOfForbiddenWords = 0; counterOfForbiddenWords < items.size(); counterOfForbiddenWords++) {
            for (int counterOfInputWords = 0; counterOfInputWords < inputTextAsWordArray.length; counterOfInputWords++) {
                if (inputTextAsWordArray[counterOfInputWords].toLowerCase().equals(items.get(counterOfForbiddenWords))) {
                    throw new BusinessException("Text contains forbidden words");
                }
            }
        }
        return true;
    }

    public static List<String> getWords() {
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
            System.out.println("error getWords() : " + ex.getMessage());
        }
        return null;
    }
}