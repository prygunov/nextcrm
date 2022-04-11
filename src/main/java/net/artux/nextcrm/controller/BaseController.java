package net.artux.nextcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class BaseController {

    protected final String pageTitle;

    @ModelAttribute("title")
    public String getPageTitle(){
        return pageTitle;
    }

    @ModelAttribute("login")
    public String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String pageWithContent(String content, Model model){
        model.addAttribute("content", content);
        return "template";
    }

    @GetMapping
    protected abstract Object getHome(Model model);

    @ModelAttribute("url")
    protected String getPageUrl(){
        return getClass().getAnnotation(RequestMapping.class).value()[0] + '/';
    }
}
