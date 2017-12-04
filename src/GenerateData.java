import java.util.Locale;

/**
 * Generates statistics for the performance of the insertion sort and merge sort algorithms.
 * Takes in a file with the arrays to sort. Elements should be separated by commas (','),
 * different arrays should be separated by new lines ('\n').
 * Produces a file which describes (for each input array) the problem size (number of elements),
 * how each of the two algorithms performed with it, and which one was faster.
 * When run, the program asks how many times should each array be sorted,
 * in order to produce an error margin.
 *
 * @author 150009974
 * @version 2.0
 */
public class GenerateData {

    private static String stringifyResult(int problemSize, double insertionTime, double mergeTime) {

        // convert to milliseconds
        insertionTime /= 1000000.0;
        mergeTime /= 1000000.0;

        String s = problemSize + ",";

        s += String.format(Locale.ENGLISH, "%.8f", insertionTime);
        s += ",";

        s += String.format(Locale.ENGLISH, "%.8f", mergeTime);

        return s;

    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java GenerateData <input-filename> <output-filename>");
            return;
        }

        System.out.print("Times each array should be sorted:");
        int repeat = Utils.readInputNumber();

        OutputFile.open(args[1]);
        OutputFile.write(Utils.REPEATS+repeat);
        OutputFile.write("problem_size,insertion_sort_time(ms),merge_sort_time(ms)");

        InputFile.open(args[0]);

        double[] arr;
        String line = InputFile.read();
        long startTime, insertionTime, mergeTime;
        System.out.println("Sorting...");
        while (line != null) {

            arr = Utils.parseArray(line);

            // Multiple sorts, in order to produce an error margin.
            for (int i = 0; i < repeat; i++) {

                startTime = System.nanoTime();
                Utils.insertionSort(arr);
                insertionTime = System.nanoTime() - startTime;

                startTime = System.nanoTime();
                Utils.mergeSort(arr);
                mergeTime = System.nanoTime() - startTime;

                OutputFile.write(stringifyResult(arr.length, insertionTime, mergeTime));

            }

            line = InputFile.read();

        }

        InputFile.close();
        OutputFile.close();

    }
}
