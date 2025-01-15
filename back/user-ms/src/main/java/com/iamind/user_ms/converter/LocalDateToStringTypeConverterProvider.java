package com.iamind.user_ms.converter;


import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverterProvider;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.utils.ImmutableMap;

import java.time.LocalDate;
import java.util.Map;

public class LocalDateToStringTypeConverterProvider implements AttributeConverterProvider {
    private final Map<EnhancedType<?>, AttributeConverter<?>> converterCache = ImmutableMap.of(
            EnhancedType.of(LocalDate.class), new LocalDateConverter());

    @SuppressWarnings("unchecked")
    @Override
    public <T> AttributeConverter<T> converterFor(EnhancedType<T> enhancedType) {
        return (AttributeConverter<T>) converterCache.get(enhancedType);
    }
}
