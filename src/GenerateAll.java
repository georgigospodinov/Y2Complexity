/**
 * Runs {@link GenerateRandomArrays}, {@link GenerateData}, and {@link GenerateStatistics}
 * in this order.
 *
 * @author 150009974
 * @version 1.0
 */
public class GenerateAll {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java GenerateAll <arrays-file> <data-file> <statistics-file>");
            return;
        }

        GenerateRandomArrays.main(new String [] {args[0]});
        GenerateData.main(new String [] {args[0], args[1]});
        GenerateStatistics.main(new String [] {args[1], args[2]});

    }
}
