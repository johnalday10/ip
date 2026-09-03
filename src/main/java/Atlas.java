import display.Ui;
import list.TaskList;
import task.Task;

public class Atlas {
    private final Ui ui;
    private final TaskList list;

    public Atlas() {
        this.ui = new Ui();
        this.list = new TaskList();
    }

    public Task parseInput(String input) {
        String[] parts = input.split(" ", 2);
        int taskIdx = Integer.parseInt(parts[1]) - 1;
        Task task = list.getItem(taskIdx);

        return task;
    }

    public void run() {
        ui.displayLine();
        ui.displayWelcomeBanner();
        ui.displayLine();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();

            if (input.equalsIgnoreCase("bye")) {
                isExit = true;
                ui.displayGoodbye();
            } else if (input.equalsIgnoreCase("list")) {
                ui.displayList(list);
            } else if (input.startsWith("mark")) {
                Task task = parseInput(input);
                task.markAsDone();
                ui.displayTaskMarked(task);
            } else if (input.startsWith("unmark")) {
                Task task = parseInput(input);
                task.markAsNotDone();
                ui.displayTaskUnmarked(task);
            } else {
                Task task = new Task(input);
                list.add(task);
                ui.displayAdd(input);
            }
        }
    }
    public static void main(String[] args) {
        new Atlas().run();
    }
}