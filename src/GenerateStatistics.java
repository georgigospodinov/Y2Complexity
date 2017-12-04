/**
 * Uses the generated data to generate statistical information.
 * The input file should be the file produced by {@link GenerateData}.
 *
 * @author 150009974
 * @version 1.2
 */
public class GenerateStatistics {

    private static int problemSize;
    // ip = Insertion Performance, mp = Merge performance
    private static double ipBest, ipWorst, ipAverage, mpBest, mpWorst, mpAverage;

    private static int parseRepeats(String line) {
        try {
            return Integer.parseInt(line.substring(Utils.REPEATS.length()));
        }
        catch (NumberFormatException e) {
            System.out.println("Could not parse number of repeats from:");
            System.out.println(line);
            return -1;
        }
    }

    private static String format() {

        String s = "";
        s += problemSize + ",";
        s += ipBest + "," + ipWorst + "," + ipAverage + ",";
        s += mpBest + "," + mpWorst + "," + mpAverage + ",";

        return s;

    }

    private static void resetCounters() {
        ipBest = Double.MAX_VALUE;
        mpBest = Double.MAX_VALUE;

        ipWorst = Double.MIN_VALUE;
        mpWorst = Double.MIN_VALUE;

        ipAverage = 0;
        mpAverage = 0;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java GenerateData <input-filename> <output-filename>");
            return;
        }

        OutputFile.open(args[1]);

        // ps = Problem Size
        String psHeader = "problem_size,";
        String insertionHeader = "insertion_best,insertion_worst,insertion_average,";
        String mergeHeader = "merge_best,merge_worst,merge_average";
        OutputFile.write(psHeader + insertionHeader + mergeHeader);

        InputFile.open(args[0]);

        int repeats = parseRepeats(InputFile.read());
        // Skip header
        InputFile.read();
        String line = InputFile.read();

        double ipCurrent, mpCurrent;
        double[] data;
        while (line != null) {

            data = Utils.parseArray(line);
            problemSize = (int) data[0]; // This is expected to be an int.

            resetCounters();

            for (int i = 0; i < repeats; i++) {
                ipCurrent = data[1];
                mpCurrent = data[2];

                if (ipCurrent > ipWorst)
                    ipWorst = ipCurrent;

                if (ipCurrent < ipBest)
                    ipBest = ipCurrent;

                if (mpCurrent > mpWorst)
                    mpWorst = mpCurrent;

                if (mpCurrent < mpBest)
                    mpBest = mpCurrent;

                ipAverage += ipCurrent;
                mpAverage += mpCurrent;

                line = InputFile.read();
                if (line == null)
                    break;
                data = Utils.parseArray(line);

            }

            ipAverage /= repeats;
            mpAverage /= repeats;

            OutputFile.write(format());

        }

        InputFile.close();
        OutputFile.close();
    }
}
