package com.ltev.spring6recipeapp.converters.using_annotations;

import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConverterUsingAnnotation<S, T> implements Converter<S, T> {

    private Map<String, Field> aliasToSourceField;
    private Map<String, Field> aliasToDestField;

    @Override
    public T convert(S sourceObject) {
        if (sourceObject == null) {
            return null;
        }

        T desObject = getNewEmptyConvertedInstance();

        // fill maps
        if (aliasToSourceField == null) {
            aliasToSourceField = mapFields(sourceObject.getClass());
            aliasToDestField = mapFields(desObject.getClass());
        }

        for (String alias : aliasToSourceField.keySet()) {
            var sourceField = aliasToSourceField.get(alias);
            var destField = aliasToDestField.get(alias);
            try {
                sourceField.setAccessible(true);
                destField.setAccessible(true);

                Object value = sourceField.getType() == destField.getType()
                        ? sourceField.get(sourceObject)
                        : this.getConverter(sourceField.getType()).convert(sourceField.get(sourceObject));
                destField.set(desObject, value);

                sourceField.setAccessible(false);
                destField.setAccessible(false);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return desObject;
    }

    private Map<String, Field> mapFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Alias.class))
                .collect(HashMap::new,
                        (m, f) -> m.put(f.getAnnotation(Alias.class).value(), f),
                        (m1, m2) -> {throw new RuntimeException("Parallel not supported!");});
    }

    protected Converter<Object, Object> getConverter(Class<?> fromType) {
        throw new RuntimeException("Not implemented for class: " + fromType);
    }

    protected abstract T getNewEmptyConvertedInstance();
}
