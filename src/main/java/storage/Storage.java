package storage;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import list.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        String userDir = System.getProperty("user.dir");
        File current = new File(userDir);

        String normalizedPath = userDir.replace("\\", "/");

        if (normalizedPath.endsWith("src/main/java")) {
            File projectRoot = current.getParentFile().getParentFile().getParentFile();
            this.filePath = new File(projectRoot, filePath).getPath();
        } else {
            this.filePath = filePath;
        }
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
        // Split by " | " with escaping for pipe regex
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                return null;
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                return null;
            }
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
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