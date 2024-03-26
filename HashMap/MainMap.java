package Maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainMap {

    public static void main(String[] args) {
        //scanner object for reading users input
        Scanner scanner = new Scanner(System.in);
        //declaring a hashtable object
        HashTable<String, Integer> hashTable;
        //creating another scanner object to read the file
        Scanner fileReader = null;
        //file object representing the filepath
        File filePath = new File("Oliver_Twist.txt");
        //here its attempting to read the file and if file not found it prints an error
        // message and exits the program
        try {
            fileReader = new Scanner(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the file path.");
            return;
        }

        //these lines prompt the user to choose the hashing strategy naive or sophisticated
        //then reads in the users input
        System.out.println("Choose the hash function level: ");
        System.out.println("1. Naive");
        System.out.println("2. Sophisticated");
        int choice = scanner.nextInt();

        //based on users input, a new hashtable object with an initial table size of 50
        //and the specified hashing strategy, if the users input is invalid it defaults to naive
        if(choice ==1){
            hashTable = new HashTable<>(50, HashTable.HashingStrategy.NAIVE);
        } else if (choice==2) {
            hashTable = new HashTable<>(50, HashTable.HashingStrategy.SOPHISTICATED);
        }else {
            System.out.println("Invalid choice. Using the default hash function.");
            hashTable = new HashTable<>(50, HashTable.HashingStrategy.NAIVE);
        }
        //calls readFileAndAddToHash method to read the text file and populate the hashtable with words and their
        //counts
        readFileAndAddToHash(fileReader, hashTable);
        //calls printInternalStructure method to print information about the internal structure of the hash table
        printInternalStructure(hashTable);

        //this loops through the menu and gets users choice
        while (true){
            System.out.println("\nMenu:");
            System.out.println("1.View word count for a specific word");
            System.out.println("2. View word in descending order of usage");
            System.out.println("3. Print table");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int menuChoice = scanner.nextInt();

            //based on users input uses switch case to perform the following:
            switch (menuChoice){
                case 1:
                    //view the count and linkedlist length for a specific word in the hashtable
                    System.out.println("Enter the word: ");
                    String word = scanner.next().toLowerCase();
                    if (hashTable.contains(word)){
                        int count = hashTable.get(word);
                        int linkedListLength = getLinkedListLength(hashTable, word);
                        System.out.println("Word count for '" + word + "': " + count);
                        System.out.println("Length of the linked list: " + linkedListLength);
                    } else {
                        System.out.println("Word not found in the book.");
                    }
                    break;
                case 2:
                    //prints the words in descending order of usage
                    printWordsInDescendingOrder(hashTable);
                    break;
                case 3:
                    //prints the entire hash table structure
                    hashTable.printHashTable();
                    break;
                case 4:
                    //exits the program
                    System.out.println("Exiting...");
                    return;
                default:
                    //if an error is entered it prints an error message and prompts again
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //readFileAndAddToHash method reads the text file line by line and populates the hashtable with words and their counts.
    private static void readFileAndAddToHash(Scanner fileReader, HashTable<String, Integer> hashTable) {
        //iterate through each word and populate the hash table, convert word to lowercase and remove punctuation
        while (fileReader.hasNext()) {
            String word = fileReader.next().toLowerCase().replaceAll("[^a-zA-z]", "");
            //checks if the word already exists in the hashtable if it does then it increments the count for the word
            //if it doesn't it adds the word to the hashtable with a count of 1
            if (hashTable.contains(word) && hashTable.get(word) != null) {
                int currentCount = hashTable.get(word);
                hashTable.put(word, currentCount + 1);
            } else {
                hashTable.put(word, 1);
            }
        }
    }
    //printInternalStructure method prints information about the internal of the hashtable includes the number of words
    //the size and the underlying array the number of unused array slots and the entire hashtable structure
    private static void printInternalStructure(HashTable<String, Integer> hashTable) {
        System.out.println("\nInternal Structure of the Hash Table:");
        System.out.println("Number of words: " + hashTable.getSize());
        System.out.println("Array size: " + hashTable.getTABLE_SIZE());
        System.out.println("Number of unused array slots: " + countUnusedSlots(hashTable));
        hashTable.printHashTable();
    }
    //countUnusedSlots method counts the number of unused slots in the underlying array of the hashtable
    private static int countUnusedSlots(HashTable<String, Integer> hashTable) {
        int unusedSlots = 0;
        for (int i = 0; i < hashTable.getTABLE_SIZE(); i++) {
            if (hashTable.getTable()[i] == null) {
                unusedSlots++;
            }
        }
        return unusedSlots;
    }

    //getLinkedListLength method calculates the length of the linkedlist for a given word in the hashtable
    private static int getLinkedListLength(HashTable<String, Integer> hashTable, String word) {
        //first it computes the hash value for the word and retrieves the head of the linkedlist at the index
        //in the underlying array. then iterates through the linkedlist until it finds the entry with the given word,
        //counting the number of entries it traverses
        int hash = hashTable.myhash(word);
        MapEntry<String, Integer> entry = hashTable.getTable()[hash];
        int length = 0;
        while (entry != null) {
            if (entry.key.equals(word)) {
                break;
            }
            entry = entry.next;
            length++;
        }
        return length;
    }

    //printWordsInDescendingOrder method prints the words in the hashtable in descending order of their usage.
    private static void printWordsInDescendingOrder(HashTable<String, Integer> hashTable) {
        //creates a priorityQueue with a comparator that sorts the mapEntry objects based on their value
        //in descengind order
        PriorityQueue<MapEntry<String, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        //then it iterates over the hashtable using an iterator and adds each entry to the priority queue
        Iterator<MapEntry<String, Integer>> iterator = hashTable.iterator();
        while (iterator.hasNext()) {
            MapEntry<String, Integer> entry = iterator.next();
            queue.offer(entry);
        }
        //it then dequeues the entries from the priority queue and prints them in descending order of usage
        System.out.println("\nWords in descending order of usage:");
        while (!queue.isEmpty()) {
            MapEntry<String, Integer> entry = queue.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}




