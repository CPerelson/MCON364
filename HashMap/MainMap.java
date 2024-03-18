package Maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainMap {
    public static void main(String[] args) {
        //read the book file
        File filePath = new File("Oliver_Twist.txt");
        //create a hash table to store word counts. Initialize the hashtable with a an amount
        HashTable<String, Integer> hashTable = new HashTable<>(50);
        Scanner scanner = null;
        try {
            scanner = new Scanner(filePath);
            while (scanner.hasNext()) {
                //iterate through each word and populate the hash table
                // Convert word to lowercase and remove punctuation
                String word = scanner.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
                //check if the word is already in the hashtable
                if (hashTable.contains(word)) {
                    //if present in the hashtable update the count
                    int currentCount = hashTable.get(word);
                    hashTable.put(word, currentCount + 1);
                } else {
                    //if not in hashtable insert word with count 1
                    hashTable.put(word, 1);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Choose the hash function level: ");
        System.out.println("1. Naive");
        System.out.println("2. Sophisticated");
        int choice = scanner.nextInt();
        HashFunctionLevel level = (choice == 1) ? HashFunctionLevel.NAIVE : HashFunctionLevel.SOPHISTICATED;
        hashTable.setHashFunctionLevel(level);

        asciiHashCode(hashTable);
        char ch;
        do {
            menu(scanner);
            menuChoice(scanner, hashTable);
            System.out.println("Do you want to continue (Type y or n)\n");
            ch = scanner.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');

    }

    private static void asciiHashCode(HashTable<String, Integer> hashTable) {
        hashTable.put("a", 97);
        hashTable.put("b", 98);
        hashTable.put("c", 99);
        hashTable.put("d", 100);
        hashTable.put("e", 101);
        hashTable.put("f", 102);
        hashTable.put("g", 103);
        hashTable.put("h", 104);
        hashTable.put("i", 105);
        hashTable.put("j", 106);
        hashTable.put("k", 107);
        hashTable.put("l", 108);
        hashTable.put("m", 109);
        hashTable.put("n", 110);
        hashTable.put("o", 111);
        hashTable.put("p", 112);
        hashTable.put("q", 113);
        hashTable.put("r", 114);
        hashTable.put("s", 115);
        hashTable.put("t", 116);
        hashTable.put("u", 117);
        hashTable.put("v", 118);
        hashTable.put("w", 119);
        hashTable.put("x", 120);
        hashTable.put("y", 121);
        hashTable.put("z", 122);
    }

    private static void menu(Scanner scanner) {
        System.out.println("Choose an option from the menu\n");
        System.out.println("1. View word count and linked list length for a word");
        System.out.println("2. View words in descending order according to word count");
        System.out.println("3. View report on internal structure of the hash table");
        System.out.println("4. Exit");
    }

    private static void menuChoice(Scanner scanner, HashTable<String, Integer> hashTable) {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                wordCountAndLinkedListLength(scanner, hashTable);
                break;
            case 2:
                descendingOrder(hashTable);
                break;
            case 3:
                hashTableInternalStructure(hashTable);
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Wrong entry\n");
                break;
        }
    }

    private static void wordCountAndLinkedListLength(Scanner scanner, HashTable<String, Integer> hashTable) {
        System.out.println("Enter a word: ");
        String word = scanner.next().toLowerCase();
        if (hashTable.contains(word)) {
            int count = hashTable.get(word);
            int hash = hashTable.myhash(word);
            //implement the logic to get the length of the linked list for hte given word
            int linkedListLength = 0; //replace this with the actual linked list length
            MapEntry<String, Integer> entry = hashTable.table[hash];
            while (entry != null) {
                linkedListLength++;
                if (entry.getKey().equals(word)) {
                    break;
                }
                entry = entry.next;
            }
            System.out.println("Word: " + word + ", Count: " + count + ", Linked List Length: " + linkedListLength);
        } else {
            System.out.println("Word not found in the hash table.");
        }
    }

    private static void descendingOrder(HashTable<String, Integer> hashTable) {
        System.out.println("Words in descending order of usage:");
        // Create a list of map entries
        List<MapEntry<String, Integer>> entries = new ArrayList<>();
        Iterator<MapEntry<String, Integer>> iterator = hashTable.iterator();
        while (iterator.hasNext()) {
            entries.add(iterator.next());
        }

        // Sort the entries by value (word counts) in descending order
        Collections.sort(entries, (entry1, entry2)-> Integer.compare(entry2.getValue(), entry1.getValue()));

        //print words and their count in descending order
        for (MapEntry<String, Integer> entry: entries){
            System.out.println(entry.getKey()+ ": "+ entry.getValue());
        }
    }

    private static void hashTableInternalStructure(HashTable<String, Integer> hashTable) {
        System.out.println("Internal structure of the hash table:");
        for (int i = 0; i < hashTable.TABLE_SIZE; i++) {
            System.out.println("Bucket " + i + ": ");
            MapEntry<String, Integer> entry = hashTable.table[i];
            while (entry != null) {
                System.out.print(entry.getKey() + "(" + entry.getValue() + ") -> ");
                entry = entry.next;
            }
            System.out.println("null");
        }
    }
}
