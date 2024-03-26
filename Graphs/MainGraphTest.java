package Graphs;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainGraphTest {
    private Graph socialNetwork;

    @BeforeEach
    void setUp(){
        socialNetwork = new Graph();
        socialNetwork.addIndividual("Leah");
        socialNetwork.addIndividual("David");
        socialNetwork.addIndividual("John");
        socialNetwork.addIndividual("Alex");
        socialNetwork.addIndividual("Devorah");
        socialNetwork.addIndividual("Sara");
        socialNetwork.addIndividual("Henry");

        socialNetwork.addFriendship("Leah", "David");
        socialNetwork.addFriendship("Leah", "Alex");
        socialNetwork.addFriendship("David", "Henry");
        socialNetwork.addFriendship("John", "Devorah");
        socialNetwork.addFriendship("John", "Sara");
        socialNetwork.addFriendship("Alex", "Sara");
        socialNetwork.addFriendship("Leah", "John");
        socialNetwork.addFriendship("Devorah", "Henry");
        socialNetwork.addFriendship("Sara", "David");
        socialNetwork.addFriendship("Henry", "Leah");
    }

    @Test
    public void testAddIndividual(){
        socialNetwork.addIndividual("NewPerson");
        assertTrue(socialNetwork.individuals.containsKey("NewPerson"));
    }
    @Test
    public void testAddFriendship() {
        socialNetwork.addFriendship("Leah", "Sara");
        Person leah = socialNetwork.individuals.get("Leah");
        Person sara = socialNetwork.individuals.get("Sara");
        assertTrue(leah.getFriends().contains(sara));
        assertTrue(sara.getFriends().contains(leah));
    }

    @Test
    public void testAreConnectedTrue() {
        assertTrue(socialNetwork.areConnected("David", "Devorah"));
        assertTrue(socialNetwork.areConnected("Alex", "Sara"));
        assertTrue(socialNetwork.areConnected("John", "Leah"));
    }

    @Test
    public void testAreConnectedFalse() {
        assertFalse(socialNetwork.areConnected("David", "Alex"));
        assertFalse(socialNetwork.areConnected("Devorah", "Sara"));
    }

    @Test
    public void testPrintAdjacencyList() {
        // Capture the output from System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        socialNetwork.printAdjacencyList();

        // Convert the output to a string and check for expected content
        String output = outContent.toString();
        assertTrue(output.contains("Leah -> David Alex John "));
        assertTrue(output.contains("David -> Leah Henry Sara "));
    }
}