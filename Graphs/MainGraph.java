package Graphs;

public class MainGraph {
    public static void main(String[] args){
        //creating a social network graph
        Graph socialNetwork = new Graph();//create a new graph object to represent the social network
        //add individuals to the social network
        socialNetwork.addIndividual("Leah");
        socialNetwork.addIndividual("David");
        socialNetwork.addIndividual("John");
        socialNetwork.addIndividual("Alex");
        socialNetwork.addIndividual("Devorah");
        socialNetwork.addIndividual("Sara");
        socialNetwork.addIndividual("Henry");
        //add friendships between individuals
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

        //printing the adjacency list of the social network
        System.out.println("Social network adjacency list:");
        socialNetwork.printAdjacencyList();

        //finding paths between individuals
        System.out.println("\nAre David and Devorah connected? "+socialNetwork.areConnected("David", "Devorah"));
        System.out.println("Are Alex and Sara connected? "+socialNetwork.areConnected("Alex", "Sara"));
        System.out.println("Are John and Leah connected? "+socialNetwork.areConnected("John", "Leah"));
    }
}
