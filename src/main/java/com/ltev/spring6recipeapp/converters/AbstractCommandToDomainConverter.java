package com.ltev.spring6recipeapp.converters;

import com.ltev.spring6recipeapp.commands.UnitOfMeasureCommand;
import com.ltev.spring6recipeapp.domains.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class AbstractCommandToDomainConverter<S, T> implements Converter<S, T> {

    /**
     * Uses Java Reflection to set values on destination object's fields
     */
    @Override
    public T convert(S source) {
        if (source == null) {
            return null;
        }

        T tObject = getNewEmptyConvertedInstance();

        Arrays.stream(source.getClass().getDeclaredFields())
                        .forEach(sourceField -> {
                            try {
                                Field desField = tObject.getClass().getDeclaredField(sourceField.getName());
                                sourceField.setAccessible(true);
                                desField.setAccessible(true);

                                Object value = sourceField.getType() == desField.getType()
                                        ? sourceField.get(source)
                                        : this.getConverter(sourceField.getType()).convert(sourceField.get(source));
                                desField.set(tObject, value);

                                sourceField.setAccessible(false);
                                desField.setAccessible(false);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        });
        return tObject;
    }

    protected Converter getConverter(Class<?> fromType) {
        throw new RuntimeException("Not implemented for class: " + fromType);
    }

    protected abstract T getNewEmptyConvertedInstance();
}
