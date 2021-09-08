package lebron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lebron.task.Deadline;
import lebron.task.Events;
import lebron.task.Task;
import lebron.task.ToDo;

/**
 * Represents a storage class to handle writing and loading from a file.
 *
 * @author Nigel Tan
 */
public class Storage {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private File file;
    private final String filePath;

    /**
     * Constructor.
     *
     * @param filePath the file path
     * @throws IOException
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            //if file does not exist
            if (!file.isFile()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            this.file = file;
        } catch (IOException e) {
            System.out.println("Something went wrong when creating/finding directory: "
                    + e.getMessage());
        }
    }

    /**
     * Loads the file contents and saves it in an arraylist.
     *
     * @param filePath the file path
     * @return an ArrayList containing tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadFileContents(String filePath) {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            while (s.hasNext()) {
                String line = s.nextLine();
                String[] splitWords = line.split(" \\| ", 4);
                String taskName = splitWords[0];
                String isDone = splitWords[1];
                Task task;
                switch (taskName) {
                case "T":
                    task = new ToDo(splitWords[2]);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    taskList.add(task);
                    break;
                case "D":
                    String deadline = splitWords[3];
                    task = new Deadline(splitWords[2], deadline);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    taskList.add(task);
                    break;
                case "E":
                    String at = splitWords[3];
                    task = new Events(splitWords[2], at);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    taskList.add(task);
                    break;
                default:
                    System.out.println(HORIZONTAL_LINE
                            + "    :( OOPS! I'm sorry, but I don't know what that means.\n"
                            + HORIZONTAL_LINE);
                    break;
                }

            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when loading file contents "
                    + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Saves the current list of tasks into the specified file.
     *
     * @param taskList the list of tasks
     * @throws IOException if the stream is invalid
     */
    public void saveToFile(ArrayList<Task> taskList) {
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                String textToAppend = taskList.get(i).getStringForFile();
                builder.append(textToAppend).append("\n");
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(builder.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when saving file contents "
                    + e.getMessage());
        }
    }
}
