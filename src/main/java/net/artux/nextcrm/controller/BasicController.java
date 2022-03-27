package net.artux.nextcrm.controller;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.repository.BasicRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public abstract class BasicController<T> {

    protected final BasicRepository<T> repository;

    @GetMapping("/all")
    public List<T> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public T getWithId(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public int deleteWithId(@PathVariable Long id){
        return repository.deleteById(id);
    }

    @PostMapping("/add")
    public int add(@RequestBody T t){
        return repository.save(t);
    }

    @PutMapping("/update")
    public int update(@RequestBody T t){
        return repository.update(t);
    }
}
