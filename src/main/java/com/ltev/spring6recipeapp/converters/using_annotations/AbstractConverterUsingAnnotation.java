package com.ltev.spring6recipeapp.converters.using_annotations;

import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.StreamSupport;

public abstract class AbstractConverterUsingAnnotation<S, T> implements Converter<S, T> {

    private Map<String, Field> aliasToSourceField;
    private Map<String, Field> aliasToDestField;

    @Override
    public T convert(S sourceObject) {
        if (sourceObject == null) {
            return null;
        }

        T desObject = getNewEmptyConvertedInstance();
        convertIterableFields(sourceObject, desObject);

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

                Object value = sourceField.get(sourceObject);
                if (value instanceof Iterable<?>) {
                    sourceField.setAccessible(false);
                    continue;                                   // support in overridden method
                }

                destField.setAccessible(true);

                value = sourceField.getType() == destField.getType()
                        ? sourceField.get(sourceObject)
                        : getConverter(sourceField.getType()).convert(sourceField.get(sourceObject));
                destField.set(desObject, value);

                sourceField.setAccessible(false);
                destField.setAccessible(false);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return desObject;
    }

    protected Converter<Object, Object> getConverter(Class<?> fromType) {
        throw new RuntimeException("Not implemented for class: " + fromType);
    }

    protected void convertIterableFields(S sourceObject, T desObject) {
    }

    protected <A, B, C extends Collection<B>> C createCollection(Collection<A> sourceCollection,
                                                                 C desCollection,
                                                                 Converter<A, B> converter) {
        if (! sourceCollection.isEmpty()) {
            StreamSupport.stream(sourceCollection.spliterator(), false)
                    .map(converter::convert)
                    .forEach(desCollection::add);
        }
        return desCollection;
    }

    protected abstract T getNewEmptyConvertedInstance();

    private Map<String, Field> mapFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Alias.class))
                .collect(HashMap::new,
                        (m, f) -> m.put(f.getAnnotation(Alias.class).value(), f),
                        (m1, m2) -> {throw new RuntimeException("Parallel not supported!");});
    }
}
