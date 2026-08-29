package list;

import java.util.ArrayList;
import java.util.List;
import task.Task;

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

    public Task getItem(int idx) {
        return tasks.get(idx);
    }

    public List<Task> getList() {
        return tasks;
    }
}
