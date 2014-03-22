package ru.kpfu.it.todo.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.kpfu.it.todo.models.Todo;
import ru.kpfu.it.todo.models.TodoStore;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/22/14
 *         Time: 4:58 AM
 */

public class ToDoTest {
    MockMvc mockMvc;

    @Mock
    TodoStore store;

    @InjectMocks
    ToDo controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver()).build();

        controller.store = store;
    }

    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("resources/templates");
        resolver.setSuffix("html");
        return resolver;
    }

    @Test
    public void homePage() throws Exception {
        Collection<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo(1L, "Test"));
        when(store.getAll()).thenReturn(todos);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("spisok"))
                .andExpect(model().attribute("spisok", equalTo(todos)));

        verify(store).getAll();
    }


}
