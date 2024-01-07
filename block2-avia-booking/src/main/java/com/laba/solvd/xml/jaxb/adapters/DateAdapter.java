package com.laba.solvd.xml.jaxb.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String s) {
        DateTimeFormatter dtf =
                DateTimeFormatter.ofPattern("MMMM-dd-yyyy", Locale.ENGLISH);
        return LocalDate.parse(s, dtf);
    }

    @Override
    public String marshal(LocalDate date) {
        return date.toString();
    }
}
