package com.ltev.spring6recipeapp.converters.domain_to_command;

import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class AbstractDomainToCommandConverter<S, T> implements Converter<S, T> {

    /**
     * Uses Java Reflection to set values on destination object's fields
     */
    @Override
    public T convert(S source) {
        if (source == null) {
            return null;
        }

        T toObject = getNewEmptyConvertedInstance();
        /*
         * Stream on destinationCommandObject (less fields than domain object)
         *
         * Skip fields when not found
         */
        Arrays.stream(toObject.getClass().getDeclaredFields())
                .forEach(toField -> {
                    try {
                        Field sourceField  = source.getClass().getDeclaredField(toField.getName());
                        sourceField.setAccessible(true);
                        toField.setAccessible(true);

                        Object value = sourceField.getType() == toField.getType()
                                ? sourceField.get(source)
                                : getConverter(sourceField.getType()).convert(sourceField.get(source));
                        toField.set(toObject, value);

                        sourceField.setAccessible(false);
                        toField.setAccessible(false);
                    } catch (NoSuchFieldException ignored) {
                        // continue
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return toObject;
    }

    protected Converter getConverter(Class<?> fromType) {
        throw new RuntimeException("Not implemented for class: " + fromType);
    }

    protected abstract T getNewEmptyConvertedInstance();
}
