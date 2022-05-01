package net.artux.nextcrm.controller.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.function.BiConsumer;

@RequiredArgsConstructor
public abstract class BaseController {

    protected final String pageTitle;

    @ModelAttribute("title")
    public String getPageTitle() {
        return pageTitle;
    }

    @ModelAttribute("login")
    public String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String pageWithContent(String content, Model model) {
        model.addAttribute("content", content);
        return "template";
    }

    @GetMapping
    protected abstract Object getHome(Model model);

    protected Object defaultPage(Model model) {
        return new RedirectView(getPageUrl(), false);
    }

    protected Object redirect(String url, Model model, RedirectAttributes redirectAttributes) {
        if (model != null && redirectAttributes != null)
            model.asMap().forEach((s, o) -> redirectAttributes.addFlashAttribute(s, o));
        return new RedirectView(url, false);
    }

    @ModelAttribute("url")
    protected String getPageUrl() {
        return getClass().getAnnotation(RequestMapping.class).value()[0] + '/';
    }
}
