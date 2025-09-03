package com.pial.gym.gymapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class DateUtils {
    @Value("gymapi.date.format")
    private String dateFormat;

    public Date convertStringToDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            return simpleDateFormat.parse(date);
        } catch (ParseException parseException) {
            log.error("Error while converting string to date: {}", parseException.getMessage());
            return new Date();
        }
    }
}
