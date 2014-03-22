package ru.kpfu.it.todo.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/22/14
 *         Time: 8:33 AM
 */
public class Todo {
    private Long id;
    @NotNull
    @Min(3)
    private String title;
    private Date start;
    private Date end;

    public Todo() {
        start = new Date();
    }

    public Todo(Long id, String title) {
        this.id = id;
        this.title = title;
        start = new Date();
    }

    public boolean isClosed() {
        return end != null;
    }

    public void setClosed(boolean closed) {
        if (closed && (end == null)) {
            end = new Date();
        } else if (end != null){
            end = null;
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
