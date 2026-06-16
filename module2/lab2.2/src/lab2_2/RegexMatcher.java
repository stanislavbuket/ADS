package lab2_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegexMatcher {
    private static final String REGEX = "^<(\\+|-)([0-5]+|[P-Z]+)>$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static boolean isValid(String word) {
        return PATTERN.matcher(word).matches();
    }

    public static void processFile(String filepath) {
        System.out.println("Processing file: " + filepath + " using Regex...");
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                boolean valid = isValid(line);
                System.out.printf("  Word: %-15s -> %s\n", line, valid ? "VALID" : "INVALID");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
