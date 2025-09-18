package com.pial.gym.gymapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Component
public class DateUtils {
    @Value("${gymapi.date.format}")
    private String dateFormat;

    public LocalDate convertStringToLocalDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException exception) {
            log.error("Error while converting string to date: {}", exception.getMessage());
            return LocalDate.now();
        }
    }
}
