package com.mcb.creditfactory.service.collateralObject;


import java.util.Optional;

public interface CollateralObjectService<T, Dto> {
    boolean approve(Dto dto);

    T save(T entity);

    Optional<T> load(Long id);

    T fromDto(Dto dto);

    Dto toDTO(T entity);

    Long getId(T entity);
}
