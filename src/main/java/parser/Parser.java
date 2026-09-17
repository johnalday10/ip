package parser;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import exception.AtlasException;

public class Parser {
    public static Task parseTask(String input) throws AtlasException {
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
                throw new AtlasException("Unknown input, please try again");
        }
    }

    public static Todo createTodo(String args) throws AtlasException {
        if (args.isEmpty()) {
            throw new AtlasException("Invalid TODO: Input cannot be empty");
        }
        return new Todo(args);
    }

    public static Deadline createDeadline(String args) throws AtlasException {
        String[] parts = args.split(" /by", 2);

        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new AtlasException("Invalid DEADLINE: Input requires description and start time");
        }

        String description = parts[0].trim();
        String date = parts[1].trim();

        return new Deadline(description, date);
    }

    public static Event createEvent(String args) throws AtlasException {
        String[] parts = args.split(" /from", 2);
        String description = parts[0].trim();

        if (parts.length < 2 || description.isEmpty()) {
            throw new AtlasException("Invalid EVENT: Input requries description and start time");
        }

        String[] toFrom = parts[1].split(" /to", 2);
        String from = toFrom[0].trim();
        String to = toFrom[1].trim();

        if (toFrom.length < 2 || from.isEmpty() || to.isEmpty()) {
            throw new AtlasException("Invalid EVENT: Input requires start and end time");
        }
        return new Event(description, from, to);
    }

    public static int parseIndex(String input) throws AtlasException{
        String[] parts = input.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new AtlasException("Invalid input: Please specify a task number");
        }

        try {
            return Integer.parseInt(parts[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AtlasException("Invalid input: Task number must be a valid integer");
        }
    }
}
