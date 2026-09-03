import display.Ui;
import list.TaskList;
import task.Task;
import parser.Parser;

public class Atlas {
    private final Ui ui;
    private final TaskList list;

    public Atlas() {
        this.ui = new Ui();
        this.list = new TaskList();
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
                int index = Parser.parseIndex(input);
                Task task = list.getItem(index);
                task.markAsDone();
                ui.displayTaskMarked(task);
            } else if (input.startsWith("unmark")) {
                int index = Parser.parseIndex(input);
                Task task = list.getItem(index);
                task.markAsNotDone();
                ui.displayTaskUnmarked(task);
            } else {
                Task task = Parser.parseTask(input);
                if (task != null) {
                    list.add(task);
                    ui.displayAdd(task, list);
                } else {
                    System.out.println("Invalid input, please try again.");
                }
            }
        }
    }
    public static void main(String[] args) {
        new Atlas().run();
    }
}