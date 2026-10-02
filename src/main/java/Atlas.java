import display.Ui;
import exception.AtlasException;
import list.TaskList;
import task.Task;
import parser.Parser;
import storage.Storage;

public class Atlas {
    private static final String FILE_PATH = "./data/atlas.txt";
    private final Storage storage;
    private final Ui ui;
    private final TaskList list;

    public Atlas() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.list = new TaskList(storage.load());
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
                    storage.save(list);
                    ui.displayTaskMarked(task);
                } else if (input.startsWith("unmark ")) {
                    int index = Parser.parseIndex(input);
                    Task task = list.getItem(index);
                    task.markAsNotDone();
                    storage.save(list);
                    ui.displayTaskUnmarked(task);
                } else if (input.equals("delete") || input.startsWith("delete ")) {
                    int index = Parser.parseIndex(input);
                    Task task = list.remove(index);
                    storage.save(list);
                    ui.displayTaskDeleted(task, list);
                } else {
                    Task task = Parser.parseTask(input);
                    list.add(task);
                    storage.save(list);
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