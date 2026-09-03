package parser;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class Parser {
    public static Task parseTask(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
            case "todo":
                return createTodo(args);
            case "deadline":
                return createDeadline(args);
            case "event":
                return createEvent(args);
            default:
                return null;
        }
    }

    public static Todo createTodo(String args) {
        
        return new Todo(args);
    }

    public static Deadline createDeadline(String args) {
        String[] parts = args.split(" /by", 2);
        String description = parts[0].trim();
        String date = parts[1].trim();
        return new Deadline(description, date);
    }

    public static Event createEvent(String args) {
        String[] parts = args.split(" /from", 2);
        String description = parts[0].trim();
        String[] toFrom = parts[1].split(" /to", 2);
        String from = toFrom[0].trim();
        String to = toFrom[1].trim();
        return new Event(description, from, to);
    }

    public static int parseIndex(String input) {
        String[] parts = input.split(" ", 2);
        return Integer.parseInt(parts[1].trim()) - 1;
    }
}
