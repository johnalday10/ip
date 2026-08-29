import display.Ui;

public class Atlas {
    private final Ui ui;

    public Atlas() {
        this.ui = new Ui();
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
            } else {
                ui.displayEcho(input);
            }
        }
    }
    public static void main(String[] args) {
        new Atlas().run();
    }
}