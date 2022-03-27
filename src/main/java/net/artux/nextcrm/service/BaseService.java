package net.artux.nextcrm.service;

import net.artux.nextcrm.repository.BasicRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseService <T>{
    protected final BasicRepository<T> repository;

    public BaseService(JdbcTemplate template) {
        repository = new BasicRepository<>(template, (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }

    public List<T> getAll(){
        return repository.findAll();
    }

    public T getWithId(Long id){
        return repository.getById(id);
    }

    public int deleteWithId(Long id){
        return repository.deleteById(id);
    }

    public int deleteAll(){
        return repository.deleteAll();
    }

    public int create(T object){
        return repository.save(object);
    }

    public int update(T object){
        return repository.update(object);
    }
    public int update(Long id, T object){
        return repository.update(id, object);
    }
}
