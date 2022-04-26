package net.artux.nextcrm.controller.util;

import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.repository.CRepository;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public abstract class BaseRepositoryController<E extends BaseEntity, // Основная сущность
        S extends CRepository<E>> extends BaseController{

    public final String folder;
    protected final S repository;
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
    public Object create(@Valid @ModelAttribute E object, BindingResult result, Model model) throws Exception{
        if (!result.hasErrors()) {
            repository.save(object);
            return defaultPage(model);
        }else{
            model.addAttribute("object", object);
            return pageWithContent(folder + "/edit", model);
        }
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
    public Object update(@Valid @ModelAttribute E dto, @PathVariable Long id, Model model) throws IllegalAccessException {
        E v = repository.findById(id).orElseThrow();
        for (Field f :
                v.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            f.set(v, f.get(dto));
        }
        repository.save(v);
        return defaultPage(model);
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public Object remove(@PathVariable Long id, Model model){
        E v = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        model.addAttribute(v);
        return defaultPage(model);
    }
}
