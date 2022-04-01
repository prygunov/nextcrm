package net.artux.nextcrm.service;

import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.CDto;

import java.util.List;

public interface CService<E extends BaseEntity,
        D extends CDto,
        V extends CDto> {

    D create(D dto);

    D update(Long id, D dto);

    V read(Long id);

    void delete(Long id);

    List<V> readAll();
}