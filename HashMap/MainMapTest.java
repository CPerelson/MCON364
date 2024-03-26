package HashMapTests;

import Maps.MainMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMapTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testMainWithNaiveHash() {
        String inputData = "1\n3\n"; // Choose naive hash and exit
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        String filePath = "src/test/resources/test_file.txt"; // Path to your test file
        MainMap.main(new String[]{filePath});

        String output = outContent.toString();
        assertTrue(output.contains("Choose the hash function level:"));
        assertTrue(output.contains("1. Naive"));
        assertTrue(output.contains("2. Sophisticated"));
        assertTrue(output.contains("Exiting..."));
    }

    @Test
    void testMainWithSophisticatedHash() {
        String inputData = "2\n3\n"; // Choose sophisticated hash and exit
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        String filePath = "src/test/resources/test_file.txt"; // Path to your test file
        MainMap.main(new String[]{filePath});

        String output = outContent.toString();
        assertTrue(output.contains("Choose the hash function level:"));
        assertTrue(output.contains("1. Naive"));
        assertTrue(output.contains("2. Sophisticated"));
        assertTrue(output.contains("Exiting..."));
    }

    @Test
    void testMainWithInvalidChoice() {
        String inputData = "0\n3\n"; // Enter an invalid choice and exit
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        String filePath = "src/test/resources/test_file.txt"; // Path to your test file
        MainMap.main(new String[]{filePath});

        String output = outContent.toString();
        assertTrue(output.contains("Choose the hash function level:"));
        assertTrue(output.contains("1. Naive"));
        assertTrue(output.contains("2. Sophisticated"));
        assertTrue(output.contains("Invalid choice. Using the default hash function."));
        assertTrue(output.contains("Exiting..."));
    }
}