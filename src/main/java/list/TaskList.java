package list;

import java.util.ArrayList;
import java.util.List;
import task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> initialTasks) {
        this.tasks = initialTasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task getItem(int index){
        return tasks.get(index);
    }
}
