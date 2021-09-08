package lebron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the Events event.
 *
 * @author Nigel Tan
 */
public class Events extends Task {

    private final LocalDate at;

    /**
     * Constructor.
     *
     * @param description the name of the task
     * @param at          the start
     */
    public Events(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    @Override
    public String getStringForFile() {
        return "E | " + super.getDoneValue() + " | " + super.getName() + " | " + this.at;
    }
}
