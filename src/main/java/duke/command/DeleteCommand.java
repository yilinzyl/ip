package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getNumTasks()) {
            throw new DukeException("Invalid task number.");
        }
        Task deletedTask = tasks.deleteTask(this.index);
        storage.update(tasks);
        ui.showDeleteMessage(deletedTask, tasks.getNumTasks());
    };

    @Override
    public boolean isExit() {
        return false;
    };

}
