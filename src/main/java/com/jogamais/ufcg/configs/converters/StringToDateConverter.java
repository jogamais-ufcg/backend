package com.jogamais.ufcg.configs.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Component
public class StringToDateConverter implements Converter<String, Date> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        return Date.from(LocalDate.parse(source, FORMATTER).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
