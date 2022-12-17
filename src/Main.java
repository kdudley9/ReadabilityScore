import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader buffer = new BufferedReader(new FileReader(file)))
        {
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String input = builder.toString();


        // Split file input into sentences
        String[] sentences = input.split("[?!.][\\s]*");
        String[] words = String.join(" ", sentences).split("\\s+");

        int numSentences = sentences.length;

        // Calculate the number of non-whitespace characters
        int characterCount = 0;
        Pattern onlySymbols = Pattern.compile("\\S");
        Matcher m = onlySymbols.matcher(input);
        while (m.find()) {
            characterCount++;
        }

        System.out.println("Words: " + words.length);
        System.out.println("Sentences: " + numSentences);
        System.out.println("Characters: " + characterCount);

        System.out.println("Syllables: "+calculateSyllableCount(input));
        System.out.println("Polysyllables: " + numberOfPolysyllables(words));

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): \n");
        Scanner scanner = new Scanner(System.in);
        String userScoreCalculation = scanner.nextLine();

        switch (userScoreCalculation) {
            case "all" -> {
                automatedReadabilityIndex(characterCount, words.length, numSentences);
                fleschKincaidIndex(words.length, numSentences, input);
                smogIndex(numberOfPolysyllables(words),numSentences);
                colemanLiauIndex(words.length, sentences.length, characterCount);
            }
            case "ARI"  -> automatedReadabilityIndex(characterCount, words.length, numSentences);
            case "FK"   -> fleschKincaidIndex(words.length, numSentences, input);
            case "SMOG" -> smogIndex(numberOfPolysyllables(words),numSentences);
            case "CL"   -> colemanLiauIndex(words.length, sentences.length, characterCount);
        }
    }

    public static int calculateAgeRange(double score) {
        int scoreCeiling = (int) Math.ceil(score);

        // Immutable map that maps score to approximate age level
        Map<Integer, Integer> ages = Map.ofEntries(
                Map.entry(1, 6),
                Map.entry(2, 7),
                Map.entry(3, 9),
                Map.entry(4, 10),
                Map.entry(5, 11),
                Map.entry(6, 12),
                Map.entry(7, 13),
                Map.entry(8, 14),
                Map.entry(9, 15),
                Map.entry(10, 16),
                Map.entry(11, 17),
                Map.entry(12, 18),
                Map.entry(13, 24),
                Map.entry(14, 25)
        );
        return ages.getOrDefault(scoreCeiling, 25);
    }

    public static void automatedReadabilityIndex(int characters, int words, int sentences) {
        double score = 4.71 * characters / words + 0.5 * words/ sentences - 21.43;
        System.out.println("Automated Readability Index: " + score +
                " (about "+calculateAgeRange(score)+"-year-olds).");
    }

    public static void fleschKincaidIndex(int words, int sentences, String input) {
        double score = 0.39 * words / sentences + 11.8 * calculateSyllableCount(input) / words - 15.59;
        System.out.println("Flesch–Kincaid readability tests: " + score +
                " (about "+calculateAgeRange(score)+"-year-olds).");
    }

    public static void smogIndex(int polysyllables, int sentences) {
        double calc = polysyllables * 30/sentences;
        double score = 1.043 * Math.sqrt(calc) + 3.1291;
        System.out.println("Simple Measure of Gobbledygook: " + score +
                " (about "+calculateAgeRange(score)+"-year-olds).");
    }

    public static void colemanLiauIndex (int words, int sentences, int characters) {
        int s = sentences / words * 100;
        int l = characters / words * 100;
        double score = .0588 * l - .296 * s - 15.8;
        System.out.println("Coleman–Liau index " + score +
                " (about "+calculateAgeRange(score)+"-year-olds).");
    }

    public static int numberOfSyllables(String input) {
        return Math.max(1, input.toLowerCase()
                .replaceAll("e$", "")
                .replaceAll("[aeiouy]{2}", "a")
                .replaceAll("[^aeiouy]", "")
                .length());
    }

    public static int numberOfPolysyllables(String[] words) {
        int count = 0;
        for (String word : words) {
            if (calculateSyllableCount(word) > 2) {
                count++;
            }
        }

        return count;
    }

    private static int calculateSyllableCount(String text) {
        String[] words = text.split(" ");
        int syllableCount = 0;

        for (String word : words) {
            syllableCount += numberOfSyllables(word);
        }

        return syllableCount;
    }
}