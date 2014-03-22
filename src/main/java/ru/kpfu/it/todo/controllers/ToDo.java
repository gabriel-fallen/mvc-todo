package ru.kpfu.it.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.it.todo.models.Todo;
import ru.kpfu.it.todo.models.TodoStore;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/22/14
 *         Time: 4:49 AM
 */
@Controller
@RequestMapping("/")
public class ToDo {
    @Autowired
    TodoStore store;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("spisok", store.getAll());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@Valid Todo t, BindingResult result, Model model){
        if (!result.hasErrors()) {
            store.add(t);
            return "redirect:/";
        } else {
            model.addAttribute("todo", t);
            return "form";
        }
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model) {
        Todo todo = new Todo();
        model.addAttribute("todo", todo);
        return "form";
    }


    @Configuration
    static class config {
        @Bean
        public TodoStore todos() {
            return new TodoStore();
        }
    }

}
