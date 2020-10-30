package com.epam.portal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewsTestData {
    public static final String TEST_TEXT = "Test content";
    public static final String TEST_STRING_DATE = "2000-01-01";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final LocalDate TEST_DATE = LocalDate.parse(TEST_STRING_DATE, FORMATTER);

    public static final long NEWS_ID_1 = 1L;
    public static final long NEWS_ID_2 = 2L;
}