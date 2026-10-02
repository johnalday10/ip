package storage;

import list.TaskList;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DELIMITER_REGEX = " \\| ";
    private static final String TYPE_TODO = "T";
    private static final String TYPE_DEADLINE = "D";
    private static final String TYPE_EVENT = "E";
    private static final String STATUS_DONE = "1";

    private final String filePath;

    public Storage(String relativePath) {
        this.filePath = Paths.get(relativePath).toString();
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = parseLineToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading storage file: " + e.getMessage());
        }

        return tasks;
    }

    private Task parseLineToTask(String line) {
        String[] parts = line.split(DELIMITER_REGEX);
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals(STATUS_DONE);
        String description = parts[2].trim();

        Task task = createTaskFromType(type, description, parts);
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    private Task createTaskFromType(String type, String description, String[] parts) {
        try {
            switch (type) {
            case TYPE_TODO:
                return new Todo(description);
            case TYPE_DEADLINE:
                if (parts.length < 4) {
                    return null;
                }
                return new Deadline(description, LocalDate.parse(parts[3].trim()));
            case TYPE_EVENT:
                if (parts.length < 5) {
                    return null;
                }
                return new Event(description,
                        LocalDate.parse(parts[3].trim()),
                        LocalDate.parse(parts[4].trim()));
            default:
                return null;
            }
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void save(TaskList list) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.getItem(i).toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error writing to storage file: " + e.getMessage());
        }
    }
}