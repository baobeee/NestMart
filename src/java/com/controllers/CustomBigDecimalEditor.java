package com.controllers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.propertyeditors.PropertiesEditor;

public class CustomBigDecimalEditor extends PropertiesEditor {

    @Override
public void setAsText(String text) throws IllegalArgumentException {
    if (text == null || text.trim().isEmpty()) {
        setValue(null);
    } else {
        try {
            // Sử dụng DecimalFormat để parse chuỗi đúng cách
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            df.setParseBigDecimal(true);
            BigDecimal value = (BigDecimal) df.parse(text);
            setValue(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid number format", e);
        }
    }
}

    @Override
    public String getAsText() {
        BigDecimal value = (BigDecimal) getValue();
        if (value == null) {
            return "";
        }
        // Sử dụng NumberFormat để định dạng số nguyên
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        formatter.setGroupingUsed(true);
        formatter.setMaximumFractionDigits(0);
        return formatter.format(value);
    }
}