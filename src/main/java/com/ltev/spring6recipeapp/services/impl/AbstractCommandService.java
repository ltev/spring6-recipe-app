package com.ltev.spring6recipeapp.services.impl;

import com.ltev.spring6recipeapp.commands.RecipeCommand;
import com.ltev.spring6recipeapp.domains.Recipe;
import com.ltev.spring6recipeapp.services.BaseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @param <C> - converted command type
 * @param <T> - original domain repository type
 * @param <ID> - id
 */
@AllArgsConstructor
public abstract class AbstractCommandService<C, T, ID> implements BaseService<C, ID> {

    protected CrudRepository<T, ID> repository;
    protected Converter<T, C> fromDomainConverter;
    protected Converter<C, T> toDomainConverter;

    @Override
    public Optional<C> getById(ID id) {
        Optional<T> optional = repository.findById(id);

        return optional.map(entity -> fromDomainConverter.convert(optional.get()));
    }

    @Override
    public List<C> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(entity -> fromDomainConverter.convert(entity))
                .reduce(new ArrayList<>(),
                        (identity, entity) -> {identity.add(entity); return identity;},
                        (identity1, identity2) -> {throw new RuntimeException("Only for parallel.");});
    }

    @Override
    @Transactional
    public C save(C domainCommand) {
        T detachedDomain = toDomainConverter.convert(domainCommand);

        T savedDomain = repository.save(detachedDomain);

        return fromDomainConverter.convert(savedDomain);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
