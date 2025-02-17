package duke.task;

/**
 * Represents a task without any date/time attached.
 */
public class Todo extends Task {

    /**
     * Initialises a Todo with its description.
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
