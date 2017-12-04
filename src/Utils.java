import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Utility algorithms.
 *
 * @author 150009974
 * @version 1.0
 */
class Utils {

    static final String REPEATS = "repeats:";
    private static final int DEFAULT_VALUE = 1;

    private static double[] insert(double value, double[] array) {

        double[] newArray = new double[array.length + 1];

        int valueWasInserted = 0;
        int i;
        for (i = 0; i < array.length; i++) {

            if (valueWasInserted == 0 && value < array[i]) {
                newArray[i] = value;
                valueWasInserted = 1;
                i--;
                continue;
            }

            newArray[i + valueWasInserted] = array[i];

        }

        if (valueWasInserted == 0)
            newArray[array.length] = value;

        return newArray;

    }

    static double[] insertionSort(double[] array) {

        double[] sorted = new double[1];
        sorted[0] = array[0];

        for (int i = 1; i < array.length; i++)
            sorted = insert(array[i], sorted);

        return sorted;

    }

    private static double[] merge(double[] a, double[] b) {

        double[] merged = new double[a.length + b.length];

        int aIndex = 0, bIndex = 0;
        int i;
        for (i = 0; aIndex < a.length && bIndex < b.length; i++) {

            if (a[aIndex] < b[bIndex]) {
                merged[i] = a[aIndex];
                aIndex++;
            }

            else {
                merged[i] = b[bIndex];
                bIndex++;
            }

        }

        if (aIndex < a.length)
            for (int j = 0; aIndex + j < a.length; j++)
                merged[i + j] = a[aIndex + j];

        else if (bIndex < b.length)
            for (int j = 0; bIndex + j < b.length; j++)
                merged[i + j] = b[bIndex + j];

        return merged;

    }

    static double[] mergeSort(double[] array) {

        int size = array.length;

        if (size == 1)
            return array;

        // partition
        int half = (array.length + 1) / 2;
        double[] left = new double[half];
        double[] right = new double[array.length - half];
        int i;
        for (i = 0; i < half; i++)
            left[i] = array[i];

        for (i = half; i < array.length; i++)
            right[i - half] = array[i];

        // sort and merge the sub-sequences
        return merge(mergeSort(left), mergeSort(right));

    }

    static int readInputNumber() {

        int valueRead;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            valueRead = Integer.parseInt(br.readLine());
            if (valueRead < 1)
                throw new NumberFormatException();

            return valueRead;
        }
        catch (NumberFormatException e) {
            System.out.println("Please, enter a positive integer!");
            return readInputNumber();
        }
        catch (IOException e) {
            System.out.println("Could not read terminal input!");
            System.out.println("Using default value - " + DEFAULT_VALUE);
            return DEFAULT_VALUE;
        }

    }

    static double[] parseArray(String line) {

        int leftEnd = 0;
        int length = line.length();
        LinkedList<Double> list = new LinkedList<>();

        for (int i = 0; i < length; i++)
            if (line.charAt(i) == ',') {
                list.add(Double.parseDouble(line.substring(leftEnd, i)));
                leftEnd = i + 1;
            }

        // It is assumed that here is no comma after the last number.
        list.add(Double.parseDouble(line.substring(leftEnd)));

        double[] array = new double[list.size()];

        int i = 0;
        while (!list.isEmpty()) {
            array[i] = list.pop();
            i++;
        }

        return array;

    }

}
