package nova.ui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;

/**
 * Handles loading and saving tasks from/to a file.
 * This class manages persistent storage of tasks, ensuring that task lists are saved and loaded correctly.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the given file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file does not exist, an empty task list is returned.
     *
     * @return A list of tasks retrieved from the file.
     * @throws NovaException If an error occurs while reading the file.
     */
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
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid task: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new NovaException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given task list to the storage file.
     * If the storage directory does not exist, it is created.
     *
     * @param taskList The task list to be saved.
     * @throws NovaException If an error occurs while writing to the file.
     */
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
