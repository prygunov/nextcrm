package net.artux.nextcrm.service;

import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.CDto;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<
        E extends BaseEntity, // Базовая сущность
        D extends CDto, // DTO на создание и редактирование
        V extends CDto, // DTO на просмотр
        R extends CRepository<E>> implements CService<E, D, V> {

    protected R repository;

    @Autowired
    protected AbstractService(R repository) {
        this.repository = repository;
    }

    public abstract D create(D dto);

    public abstract D update(Long id, D dto);

    public abstract V read(Long id);

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public E findById(Long id, String errorMessage) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(errorMessage));
    }
}
