package ru.kpfu.it.todo.models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/22/14
 *         Time: 9:20 AM
 */
public class TodoStore {
    private Collection<Todo> todos = new ArrayList<Todo>();

    public Collection<Todo> getAll() {
        return todos;
    }

    public void add(Todo todo) {
        todos.add(todo);
    }
}
