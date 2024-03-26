package Graphs;

import java.util.*;

public class Graph {
    //Graph class represents the social network
    Map<String, Person> individuals;//map to store individuals in the graph

    public Graph() {
        //initialize hte individuals map
        this.individuals = new HashMap<>();
    }

    public void addIndividual(String name){
        //method to add a new individual to the graph
        individuals.put(name, new Person(name));
    }

    public void addFriendship(String person1, String person2) {
        //method to add a friendship between two individuals
        Person individual1 = individuals.get(person1);//get the person object for person1
        Person individual2 = individuals.get(person2);//get the person object for person2
        if (individual1 != null && individual2 != null) {//check if both individuals exist in the graph
            individual1.addNeighbor(individual2);//add person2 as a friend of person1
            individual2.addNeighbor(individual1);//add person1 as a friend of person2 (assume friendship is bi-directional)
        } else {
            System.out.println("Invalid friendship: " + person1 + " <-> " + person2);//print an error message if one or both individuals dont exist        }
        }
    }

    //breadth first search algorithm to find if a path exists between two individuals
    public boolean areConnected(String person1, String person2){
        if (!individuals.containsKey(person1)||!individuals.containsKey(person2))//check if both individuals exist in the graph
            return false;//Return false if one or both individuals don't exist
        Set<Person> visited = new HashSet<>();//set to keep track of visited individuals
        Queue<Person> queue = new LinkedList<>();//queue for bfs traversal

        Person startPerson = individuals.get(person1);//get the starting person object
        Person endPerson = individuals.get(person2);//get the ending person object

        queue.add(startPerson);//add the starting person to the queue
        visited.add(startPerson);//mark the starting person as visited
        while (!queue.isEmpty()){//loop until queue is empty
            Person current = queue.poll();//remove and get the next person from the queue
            if (current == endPerson)//check if the current person is the ending person
                return true;//return true if they are connected
            for (Person friend : current.getFriends()){//get the person of the current person
                if (!visited.contains(friend)){//check if the person is not visited
                    visited.add(friend);//mark the person as visited
                    queue.add(friend);//add the friend to queue
                }
            }
        }
        return false;//return false if no path is found between the two individuals
    }
        //method to print the adjacency list representation of the graph
    public void printAdjacencyList(){
        for (Person node : individuals.values()){//iterate over all individuals in the graph
            System.out.println(node + " -> ");//print the individuals name
            for (Person person: node.getFriends()){//get the friends of the individual
                System.out.println(person + " ");//print the friend name
            }
            System.out.println();//print a new line after printing all the friends of the individual
        }
    }
}
