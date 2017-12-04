import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Used to open and read a file.
 *
 * @author 150009974
 * @version 1.0
 */
class InputFile {

    private static BufferedReader fileReader;

    static void open(String filename){
        try {
            fileReader = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not find input file!");
            e.printStackTrace();
        }
    }

    static String read() {
        try {
            return fileReader.readLine();
        }
        catch (IOException e) {
            System.out.println("Could not read file!");
            e.printStackTrace();
            return null;
        }
    }

    static void close() {
        try {
            fileReader.close();
        }
        catch (IOException e) {
            System.out.println("Could not close file!");
            e.printStackTrace();
        }
    }
}
