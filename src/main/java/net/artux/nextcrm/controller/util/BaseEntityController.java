package net.artux.nextcrm.controller.util;

import net.artux.nextcrm.model.BaseEntity;
import net.artux.nextcrm.model.CDto;
import net.artux.nextcrm.service.CService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.ParameterizedType;

public abstract class BaseEntityController<D extends CDto, // DTO на создание и редактирование
        V extends CDto, // DTO на просмотр
        E extends BaseEntity, // Основная сущность
        S extends CService<E, D, V>> extends BaseController{

    public final String folder;
    protected final S service;
    public Class<D> dClass;

    public BaseEntityController(String pageTitle, String folder, S service) {
        super(pageTitle);
        this.folder = folder;
        this.service = service;
        dClass = (Class<D>) ((ParameterizedType) getClass()
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
    public ModelAndView create(@ModelAttribute D object, Model model){
        service.create(object);
        return new ModelAndView("redirect:" + getPageUrl());
    }

    @Override
    @GetMapping
    public String getHome(Model model){
        var list = service.readAll();
        model.addAttribute("objects", list);
        return pageWithContent(folder + "/view", model);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Long id){
        D d = service.getForEdit(id);
        model.addAttribute("object", d);
        return pageWithContent(folder + "/edit", model);
    }

    @PostMapping
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public Object update(@ModelAttribute D dto, @PathVariable Long id, Model model){
        service.update(id, dto);
        return new ModelAndView("redirect:" + getPageUrl());
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable Long id, Model model){
        service.delete(id);
        return new ModelAndView("redirect:" + getPageUrl());
    }

}
