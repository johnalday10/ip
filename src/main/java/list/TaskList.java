package list;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(String task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public String getItem(int idx) {
        return tasks.get(idx);
    }

    public List<String> getList() {
        return tasks;
    }
}
