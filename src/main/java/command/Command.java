package command;

import display.Ui;
import exception.AtlasException;
import list.TaskList;
import storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException;

    public boolean isExit() {
        return false;
    }
}