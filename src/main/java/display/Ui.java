package display;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeBanner() {
        String banner = "  ___  _____ _        _    ____  \n"
                + " / _ \\|_   _| |      / \\  / ___| \n"
                + "| |_| | | | | |     / _ \\ \\___ \\ \n"
                + "|  _  | | | | |___ / ___ \\ ___) |\n"
                + "|_| |_| |_| |_____/_/   \\_\\____/ \n";
        System.out.println(banner);
        System.out.println("Hello! I'm Atlas.");
        System.out.println("What can I do for you?");
    }

    public void displayLine() {
        System.out.println(DIVIDER);
    }

    public String readInput() {
        return scanner.nextLine().trim();
    }

    public void displayEcho(String string) {
        displayLine();
        System.out.println(string);
        displayLine();
    }

    public void displayGoodbye() {
        displayLine();
        System.out.println("Bye. Happy to be of service!");
        displayLine();
    }

    public void close() {
        scanner.close();
    }
}
