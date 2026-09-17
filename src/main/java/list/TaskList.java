package list;

import java.util.ArrayList;
import java.util.List;
import task.Task;
import exception.AtlasException;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task getItem(int index){
        return tasks.get(index);
    }
}
