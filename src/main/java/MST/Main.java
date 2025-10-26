package MST;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar mst-assignment.jar <input.json> <output.json>");
            System.exit(1);
        }
        String input = args[0];
        String output = args[1];

        MSTAnalyzer analyzer = new MSTAnalyzer();
        try {
            analyzer.runAndWrite(input, output);
            System.out.println("MST analysis complete. Results written to: " + output);
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}