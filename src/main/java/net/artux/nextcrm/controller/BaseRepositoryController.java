package net.artux.nextcrm.controller;

import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public abstract class BaseRepositoryController<E extends BaseEntity, // Основная сущность
        S extends CRepository<E>> extends BaseController{

    public final String folder;
    private final S repository;
    public Class<E> dClass;

    public BaseRepositoryController(String pageTitle, String folder, S repository) {
        super(pageTitle);
        this.folder = folder;
        this.repository = repository;
        dClass = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        try {
            model.addAttribute("object", dClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return pageWithContent(folder + "/edit", model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute E object, Model model){
        repository.save(object);
        return getHome(model);
    }

    @Override
    @GetMapping
    public String getHome(Model model){
        var list = repository.findAll();
        model.addAttribute("objects", list);
        return pageWithContent(folder + "/view", model);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id){
        E v = repository.findById(id).orElseThrow();

        model.addAttribute("object", v);
        return pageWithContent(folder + "/edit", model);
    }

    @PostMapping
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public Object update(@ModelAttribute E dto, @PathVariable Long id, Model model) throws IllegalAccessException {
        E v = repository.findById(id).orElseThrow();
        for (Field f :
                v.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            f.set(v, f.get(dto));
        }
        repository.save(v);
        return getHome(model);
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public String remove(@PathVariable Long id, Model model){
        repository.deleteById(id);
        return getHome(model);
    }

    @ModelAttribute("url")
    public String getPageUrl(){
        return getClass().getAnnotation(RequestMapping.class).value()[0] + '/';
    }

}
