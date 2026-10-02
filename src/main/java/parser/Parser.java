package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import exception.AtlasException;
import task.Deadline;
import task.Event;
import task.Todo;

public class Parser {

    public static Command parse(String input) throws AtlasException {
        String trimmed = input.trim();
        String[] parts = trimmed.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIndex(args), true);
        case "unmark":
            return new MarkCommand(parseIndex(args), false);
        case "delete":
            return new DeleteCommand(parseIndex(args));
        case "todo":
            return new AddCommand(createTodo(args));
        case "deadline":
            return new AddCommand(createDeadline(args));
        case "event":
            return new AddCommand(createEvent(args));
        default:
            throw new AtlasException("Unknown input, please try again");
        }
    }

    private static Todo createTodo(String args) throws AtlasException {
        if (args.isEmpty()) {
            throw new AtlasException("Invalid TODO: Input cannot be empty");
        }
        return new Todo(args);
    }

    private static Deadline createDeadline(String args) throws AtlasException {
        String[] parts = args.split(" /by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new AtlasException("Invalid DEADLINE: Input requires description and start time");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    private static Event createEvent(String args) throws AtlasException {
        String[] parts = args.split(" /from", 2);
        String description = parts[0].trim();

        if (parts.length < 2 || description.isEmpty()) {
            throw new AtlasException("Invalid EVENT: Input requires description and start time");
        }

        String[] toFrom = parts[1].split(" /to", 2);
        if (toFrom.length < 2 || toFrom[0].trim().isEmpty() || toFrom[1].trim().isEmpty()) {
            throw new AtlasException("Invalid EVENT: Input requires start and end time");
        }

        return new Event(description, toFrom[0].trim(), toFrom[1].trim());
    }

    private static int parseIndex(String args) throws AtlasException {
        if (args.isEmpty()) {
            throw new AtlasException("Invalid input: Please specify a task number");
        }
        try {
            return Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AtlasException("Invalid input: Task number must be a valid integer");
        }
    }
}