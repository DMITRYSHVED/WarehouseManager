package com.warehouse.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class YesNoConverter implements AttributeConverter <Boolean, Character> {

    private static final char YES = 'Y';
    private static final char NO = 'N';

    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        return attribute ? YES : NO;
    }

    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        return YES == (dbData);
    }
}
