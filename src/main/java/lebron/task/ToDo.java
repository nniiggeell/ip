package lebron.task;

/**
 * This class represents the ToDo event.
 *
 * @author Nigel Tan
 */
public class ToDo extends Task {

    /**
     * Constructor.
     *
     * @param description the name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Produce the desired format for storing to file.
     *
     * @return the desired string.
     */
    @Override
    public String getStringForFile() {
        return "T | " + super.getDoneValue() + " | " + super.getName();
    }
}

