package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the Duke application.
 */
public class Storage {

    private File file;
    private String filePath;

    /**
     * Initialises a storage object with given path to file.
     * @param filePath Path to file where data should be stored.
     */
    public Storage(String filePath) {
        assert !filePath.equals("");
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Updates the contents of this storage with the given task list.
     * @param tasks Updated task list to be stored.
     * @throws DukeException If the application is unable to edit the file.
     */
    public void update(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(tasks.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file");
        }
    }

    /**
     * Reads content from the file specified in the filePath. Creates the file if it does not exist.
     * @return A list with each element being each line of the file.
     * @throws DukeException If unable to create the file from filepath.
     */
    public ArrayList<String> load() throws DukeException{
        try {
            Scanner s = new Scanner(file).useDelimiter("\\r?\\n|\\r");
            ArrayList<String> taskStrings = new ArrayList<String>();
            while (s.hasNext()){
                taskStrings.add(s.next());
            }
            s.close();
            return taskStrings;
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                int directoryEndIndex = filePath.lastIndexOf("/");
                if (directoryEndIndex != -1) {
                    String directory = filePath.substring(0, directoryEndIndex);
                    new File(directory).mkdir();
                }
                file.createNewFile();
                return new ArrayList<>();
            } catch (IOException ioException) {
                throw new DukeException("Unable to create file. Changes made will not be saved.");
            }
        }
    }

}
