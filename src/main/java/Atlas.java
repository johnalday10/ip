import display.Ui;
import list.TaskList;

public class Atlas {
    private final Ui ui;
    private final TaskList list;

    public Atlas() {
        this.ui = new Ui();
        this.list = new TaskList();
    }
    public void run() {
        ui.displayLine();
        ui.showWelcomeBanner();
        ui.displayLine();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();

            if (input.equalsIgnoreCase("bye")) {
                isExit = true;
                ui.displayGoodbye();
            } else if (input.equalsIgnoreCase("list")) {
                ui.displayList(list);
            } else {
                // ui.displayEcho(input);
                list.add(input);
                ui.displayAdd(input);
            }
        }
    }
    public static void main(String[] args) {
        new Atlas().run();
    }
}