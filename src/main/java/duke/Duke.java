package duke;

import duke.admin.Parser;
import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.commands.Command;
import duke.exceptions.DukeException;
/**
 * Duke class is the main class of the program.
 */
public class Duke {
    private static String DEFAULT_FILE_PATH = ".\\src\\main\\java\\duke\\data\\duke.txt";
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        storage = new Storage(DEFAULT_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }
    
    /**
     * Constructor of Duke that takes in a file path specifying the location of the
     * storage file.
     * @param filePath string specifying location of storage file 
     */
    public Duke(String filePath) {
        assert filePath != null;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String fullCommand) throws DukeException {
        assert fullCommand != null;
        try {
            Command c = Parser.parse(fullCommand);
            String response = c.execute(tasks, storage);
            
            return response;
        } catch (DukeException e) {
            return Ui.showErrorMessage(e.getMessage());
        }

    }
}
