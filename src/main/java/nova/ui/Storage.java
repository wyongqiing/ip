package nova.ui;

import nova.task.Task;
import nova.task.TaskList;
import nova.exception.NovaException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws NovaException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with an empty task list.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    Task task = Task.decode(line);
                    tasks.add(task);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid task: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new NovaException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(TaskList taskList) throws NovaException {
        try {
            File file = new File(filePath);
            File folder = file.getParentFile();

            if (folder != null && !folder.exists() && !folder.mkdirs()) {
                System.out.println("Failed to create directory: " + folder.getAbsolutePath());
            }

            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : taskList.getTasks()) {
                    writer.write(task.encode() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new NovaException("Error saving tasks: " + e.getMessage());
        }
    }
}