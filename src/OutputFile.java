import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Used to open and write a file.
 *
 * @author 150009974
 * @version 1.1
 */
class OutputFile {

    private static BufferedWriter fileWriter;

    static void open(String filename) {
        try {
            fileWriter = new BufferedWriter(new FileWriter(filename));
        }
        catch (IOException e) {
            System.out.println("Could not open file!");
            e.printStackTrace();
        }
    }

    static void write(String line) {
        try {
            fileWriter.write(line+"\n");
        }
        catch (IOException e) {
            System.out.println("Could not write to file!");
            e.printStackTrace();
        }
    }

    static void close() {
        try {
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Could not close file!");
            e.printStackTrace();
        }
    }

}
