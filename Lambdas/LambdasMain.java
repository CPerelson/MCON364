package Lambdas;

import java.time.LocalDate;
import java.util.*;

public class LambdasMain {
    public static void main(String[] args){
        ArrayList<SchulMember> membersList = new ArrayList<>();
        // Creating SchulMember objects
        SchulMember member1 = new SchulMember("Parker", "Sammy", LocalDate.of(1985, 5, 6), "Jane", "Parker", new String[]{"Alice", "Bob", "Dan", "Harry"}, 5);
        SchulMember member2 = new SchulMember("Smith", "Alice", LocalDate.of(1990, 6, 23), "John", "Smith", new String[]{"Eve", "Frank"}, 7);

        //adding members
        membersList.add(member1);
        membersList.add(member2);

        //Print how many families belong to your schul
        long numOfFamilies = membersList.stream()
                .count();
        System.out.println("Number of families that belong to your shul: "+ numOfFamilies);

        //Print in sorted order how long each family has been a member of your schul
        membersList.stream().sorted(Comparator.comparingInt(SchulMember::getYearsOfMembership))
                .forEach(entry ->System.out.println("Family: "+ entry.getLastNameOfMember()+", Years of membership: "+entry.getYearsOfMembership()));

        //Print out from oldest to youngest the ages of your members (not spouses)
        membersList.stream().sorted(Comparator.comparing(SchulMember::getBirthDateOfMember))
                .forEach(entry -> System.out.println("Family: "+entry.getSpouseLastName()+", Age: "+entry.getBirthDateOfMember()));

        //Sort the names of the spouses of all members
        membersList.stream().sorted(Comparator.comparing(SchulMember::getSpouseFirstName))
                .forEach(entry -> System.out.println("Spouses:" +entry.getSpouseFirstName()));

        //print out all families who have more than 3 children
        membersList.stream().filter(member -> member.getChildrenNames().length > 3)
                .forEach(entry -> System.out.println("Families with more than 3 children: " + entry.getLastNameOfMember()));

        //print out the names of all children whose name is larger (alphabetically) than the letter “c” (and what family they belong to)
        membersList.stream().filter(member -> Arrays.stream(member.getChildrenNames())
                .anyMatch(name -> name.compareToIgnoreCase("c")>0))
                .forEach(entry -> {
                    System.out.println("\nFamily: "+entry.getLastNameOfMember() + "\nChildren: ");
                    Arrays.stream(entry.getChildrenNames())
                            .filter(name -> name.compareToIgnoreCase("c")>0)
                            .forEach(name -> System.out.println(name +" "));

                });

    }
}
