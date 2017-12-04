import java.util.Random;

/**
 * This class can generate arrays of random length.
 *
 * @author 150009974
 * @version 1.0
 */
public class GenerateRandomArrays {

    private static Random r = new Random();

    private static double[] generateArrayOfLength(int n) {

        double[] array = new double[n];

        for (int i = 0; i < n; i++)
            array[i] = r.nextInt() * r.nextDouble();

        return array;

    }

    private static String stringifyArray(double[] arr) {

        String s = "";
        int length = arr.length;

        if (length < 1)
            return s;

        s += arr[0];

        for (int i = 1; i < length; i++) {
            s += ",";
            s += arr[i];
        }

        return s;
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java GenerateRandomArrays <output-filename>");
            return;
        }

        System.out.print("Initial number of elements:");
        int initialNumberOfElements = Utils.readInputNumber();

        System.out.print("Maximum number of elements:");
        int maxNumberOfElements = Utils.readInputNumber();

        System.out.print("The increase to the initial number after each array is generated:");
        int step = Utils.readInputNumber();

        OutputFile.open(args[0]);

        System.out.println("Creating arrays with random contents...");
        for (int i = initialNumberOfElements; i <= maxNumberOfElements; i += step)
            OutputFile.write(stringifyArray(generateArrayOfLength(i)));

        OutputFile.close();

    }

}
