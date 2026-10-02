import display.Ui;
import exception.AtlasException;
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

    public void run(){
        ui.displayLine();
        ui.displayWelcomeBanner();
        ui.displayLine();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    isExit = true;
                    ui.displayGoodbye();
                } else if (input.equalsIgnoreCase("list")) {
                    ui.displayList(list);
                } else if (input.startsWith("mark ")) {
                    int index = Parser.parseIndex(input);
                    Task task = list.getItem(index);
                    task.markAsDone();
                    ui.displayTaskMarked(task);
                } else if (input.startsWith("unmark ")) {
                    int index = Parser.parseIndex(input);
                    Task task = list.getItem(index);
                    task.markAsNotDone();
                    ui.displayTaskUnmarked(task);
                } else if (input.equals("delete") || input.startsWith("delete ")) {
                    int index = Parser.parseIndex(input);
                    Task task = list.remove(index);
                    ui.displayTaskDeleted(task, list);
                } else {
                    Task task = Parser.parseTask(input);
                    list.add(task);
                    ui.displayAdd(task, list);
                }
            } catch (AtlasException e) {
                ui.displayLine();
                System.out.println(" " + e.getMessage());
                ui.displayLine();
            } catch (IndexOutOfBoundsException e) {
                ui.displayLine();
                System.out.println("Invalid: Task number does not exist");
                ui.displayLine();
            }
        }
    }
    public static void main(String[] args) {
        new Atlas().run();
    }
}