package Graphs;

import java.util.ArrayList;
import java.util.List;

public class Person {
    //Node class to represent individuals in the social network
    String name;
    List<Person> friends;

    public Person(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
    }

    public void addNeighbor(Person friend){
        friends.add(friend);
    }

    public List<Person> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return name;
    }
}
