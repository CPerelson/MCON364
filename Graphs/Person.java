package Graphs;

import java.util.ArrayList;
import java.util.List;

public class Person {
    //Node class to represent individuals in the social network
    String name;//name of individual
    List<Person> friends;//list of friends of the individual

    public Person(String name) {
        this.name = name;//initialize the name of the individual
        this.friends = new ArrayList<>();//initialize the friends list
    }

    //method to add friend to the individual
    public void addNeighbor(Person friend)
    {
        friends.add(friend);//add the friend to the friends list
    }

    //method to get the list of friends of the individual
    public List<Person> getFriends() {
        return friends;//return the list of friends
    }

    //tostring method to print the individual's name
    @Override
    public String toString() {
        return name;
    }
}
