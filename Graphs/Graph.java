package Graphs;

import java.util.*;

public class Graph {
    //Graph class represents the flight route network
    Map<String, Person> individuals;

    public Graph() {
        this.individuals = new HashMap<>();
    }

    public void addIndividual(String name){
        individuals.put(name, new Person(name));
    }

    public void addFriendship(String person1, String person2){
        Person individual1 = individuals.get(person1);
        Person individual2 = individuals.get(person2);
        if (individual1!= null && individual2!= null){
            individual1.addNeighbor(individual2);
            individual2.addNeighbor(individual1);//assuming friendship is bi-directional
        }else {
            System.out.println("Invalid friendship: "+person1+" <-> "+person2);
        }
    }

    //breadth first search algorithm to find if a path exists between two individuals
    public boolean areConnected(String person1, String person2){
        if (!individuals.containsKey(person1)||!individuals.containsKey(person2))
            return false;
        Set<Person> visited = new HashSet<>();
        Queue<Person> queue = new LinkedList<>();

        Person startPerson = individuals.get(person1);
        Person endPerson = individuals.get(person2);

        queue.add(startPerson);
        visited.add(startPerson);
        while (!queue.isEmpty()){
            Person current = queue.poll();
            if (current == endPerson)
                return true;
            for (Person neighbor : current.getFriends()){
                if (!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    public void printAdjacencyList(){
        for (Person node : individuals.values()){
            System.out.println(node + " -> ");
            for (Person person: node.getFriends()){
                System.out.println(person + " ");
            }
            System.out.println();
        }
    }
}
