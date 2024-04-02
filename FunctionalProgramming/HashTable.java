package FunctionalProgramming;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<K, V> implements MapInterface<K, V>, Iterable<MapEntry<K, V>>{
//this class parameterized the generic types k for key and v for value it implements MapInterface and Iterable
    //which allows it to be iterated over using and iterator

    //these lines declare the instance variable for the hashtable
    private int TABLE_SIZE;//size of underlying array
    private int size;//number of key-value pairs currently stored in hashtable
    private MapEntry<K, V>[] table;//underlying array that stores the MapEntry objects
    private final HashingStrategy hashingStrategy;//hashing strategy to be used

    //constant representing the max load factor its the ratio of the size to table size before resizing is triggered
    private static final double MAX_LOAD_FACTOR = 0.75;


    //constuctor for the hashtable class takes 2 arguments ts - initial table size and hashingstrategy - the hashing
    //strategy to be used. it initializes the table size and size variables, creates a new array of mapentry object
    //with the specified size and initializes all elements to null it also assigns the provided hashingstrtegy
    //to the instance variable
    public HashTable(int ts, HashingStrategy hashingStrategy) {
        this.TABLE_SIZE = ts;
        this.size = 0;
        table = new MapEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++){
            table[i] = null;
        }
        this.hashingStrategy=hashingStrategy;
    }

    //getLoadFactor method calculates and returns the current load factor of the hashtable
    //the ratio of the number of key-value pairs to the table size
    private double getLoadFactor() {
        return (double) size / TABLE_SIZE;
    }

    //these are public getter methods that return table size and the underlying table array
    public int getTABLE_SIZE() {
        return TABLE_SIZE;
    }

    public MapEntry<K, V>[] getTable() {
        return table;
    }

    //naiveHash method implements a simpel hashing function. it takes a string key and the tabl size
    //as input it sums up the character values of the key and returns the sum modulo the table size
    private static int naiveHash(String key, int tableSize){
        int hashVal = 0;
        for (int i = 0; i< key.length(); i++){
            hashVal+=key.charAt(i);
        }
        return hashVal % tableSize;
    }
    //sophisticatedHash method implements the FNV-1a hashing function. it takes a string key and the table size
    //as input it performs a series of bitwise operations on the characters of the key string
    //to compute a hash value and then returns the absolute value of the hash value modulo the table size
    private static int sophisticatedHash(String key, int tableSize) {
        int hashVal = 0x811c9dc5; // FNV-1a offset basis
        for (int i = 0; i < key.length(); i++) {
            hashVal ^= key.charAt(i);
            hashVal += (hashVal << 1) + (hashVal << 4) + (hashVal << 7) + (hashVal << 8) + (hashVal << 24);
        }
        return Math.abs(hashVal % tableSize);
    }

    //function to get the number of key-value pairs currently stored in the hashtable
    public int getSize() {
        return size;
    }
    //function to clear hashtable by setting all elements of the table array to null and resetting the size to 0
    public void makeEmpty(){
        for (int i = 0; i< TABLE_SIZE; i++){
            table[i]=null;
        }
        size = 0;
    }
    //function to insert a key-value pair put is implemented from MapInterface
    @Override
    public V put(K key, V value) {
        //put takes a key and value as input.
        //computes the hash value of the key using the specified hashing strategy
        int hash = hashingStrategy.hash((String) key, TABLE_SIZE);
        //if the slot at the computed hash index is null create a new mapEntry with the key-value pair
        // and store it there
        if (table[hash] == null) {
            table[hash] = new MapEntry<>(key, value);
        } else {
            //otherwise it traverses the linkedlist at that index until it finds an entry with the same key
            //or reaches the end of the list
            MapEntry<K, V> entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) {
                entry = entry.next;
            }
            //if an entry is found with the same key it updates the value and returns the old value
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            } else {
                //if no such entry is found it appends a new mapEntry at the end of the linkedlist
                entry.next = new MapEntry<>(key, value);
            }
        }
        //after inserting the entry it increments the size
        size++;

        //and then checks if the load factor exceeds the MAX_LOAD_FACTOR if so it calls resize method to resize the
        //hashtable
        if (getLoadFactor() > MAX_LOAD_FACTOR) {
            resize();
        }
        //if key not found in the hash table it returns null
        return null;
    }
    //resize method used to increase the size of the hashtable when the load factor exceeds MAX_LOAD_FACTOR
    private void resize() {
        //creates a new mapEntry array with a size that is twice the current table size
        int newTableSize = TABLE_SIZE * 2; // Double the table size
        MapEntry<K, V>[] newTable = new MapEntry[newTableSize];

        //then it iterates through the existing table array and rehashes all entries into the new table
        //using the specified hashing strategy and the new table size
        for (int i = 0; i < TABLE_SIZE; i++) {
            MapEntry<K, V> entry = table[i];
            while (entry != null) {
                int newHash = hashingStrategy.hash((String) entry.key, newTableSize);
                MapEntry<K, V> nextEntry = entry.next;
                entry.next = newTable[newHash];
                newTable[newHash] = entry;
                entry = nextEntry;
            }
        }
        //then it updates the table size and table vairables with the new values
        TABLE_SIZE = newTableSize;
        table = newTable;
    }

    //function to get value of a key implemented from the mapInterface
    @Override
    public V get(K key) {
        //takes a key as input and returns the associated value
        //computes the hash value of the key using specified hashing strategy
        int hash = hashingStrategy.hash(key.toString(), TABLE_SIZE);
        //if slot at computed hash index is null return null
        if (table[hash] == null) {
            return null;
        } else {
            //otherwise traverse the linkedlist at that index until finds an entry with the given key
            //or reaches th end of the list
            MapEntry<K, V> entry = table[hash];
            while (entry != null && !entry.key.equals(key)) {
                entry = entry.next;
            }
            //if an entry with the given key is found it returns the associated value
            //if not return null
            return (entry == null) ? null : entry.value;
        }
    }

    //remove is implemented form the mapInterface
    @Override
    public V remove(K key) {
        //takes a key as input and removes the associated entry from the hashtable
        //it computes the hash value of the key using the specified hashing strategy
        int hash = hashingStrategy.hash(key.toString(), TABLE_SIZE);
        //if the slot at the computed hash index is null return null
        if (table[hash] != null) {
            MapEntry<K, V> prevEntry = null;
            MapEntry<K, V> entry = table[hash];
            while (entry != null && !entry.key.equals(key)) {
                prevEntry = entry;
                entry = entry.next;
            }
            //otherwise it traverses the linkedlist at that index until it finds an entry with the given key
            //is found it removes the entry from the linkedlist by updating the pointers appropriately
            if (entry != null) {
                V value = entry.value;
                if (prevEntry == null) {
                    table[hash] = entry.next;
                } else {
                    prevEntry.next = entry.next;
                }
                //it then decrements the size and returns the value associated with the removed entry
                size--;
                return value;
            }
        }
        //if no such entry is found returns null
        return null;
    }


    //myhash takes a key as input and returns a hash value for that key
    public int myhash(K key){
        //calls the hash code method on the key object to get the hash code
        int hashVal = key.hashCode();
        //then takes the modulus of the hashcode with the table size to ensure that the hash value
        //falls within the range of the table indices
        hashVal %= TABLE_SIZE;
        //if the resulting hash value is negative it adds the table size to make it positive
        if (hashVal<0){
            hashVal+= TABLE_SIZE;
        }
        return hashVal;
    }

    //function to print hash tables contents
    public void printHashTable(){
        //iterates through the table array and for each non-null slot it prints the bucket#
        //followed by the key-value pairs in that bucket(linkedlist)
        //it traverses the linkedlist and prints each mapentry object in the format key-value
        for (int i = 0; i < TABLE_SIZE; i++){
            System.out.println("\nBucket "+(i+1)+" : ");
            MapEntry<K, V> entry = table[i];
            while (entry!=null){
                System.out.println(entry.key+ " "+ entry.value+" ");
                entry = entry.next;
            }
        }
    }

    //implemented from mapInterface
    @Override
    public boolean contains(K key) {
        //takes a key as input and returns true if the key exists in the hashtable
        //otherwise it returns false
        //computes the hash value of the key using the specified hashing strategy
        int hash = hashingStrategy.hash(key.toString(), TABLE_SIZE);
        //then it traverses the linkedlist at the computed hash index
        MapEntry<K, V> entry = table[hash];
        //checks if any entry in the list has a key that equals the given key if the entry is found returns true
        while (entry != null) {
            if (entry.key.equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        //otherwise returns false
        return false;
    }

    //returns true if map is empty otherwise returns false implemented from MapInterface
    @Override
    public boolean isEmpty() {
        return size == 0;//uses arraylist size
    }

    //returns true if map is full otherwise returns false implemented from MapInterface
    @Override
    public boolean isFull() {
        return false;//array list is never full so is always false
    }

    //returns the number of entries in map implemented from MapInterface
    @Override
    public int size() {
        return size;//uses arraylist size
    }
    //implemented form the iterable interface
    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        //returns an instance of the inner class HashTableIterator which implements the iterator interface
        //and allows for iterating over the key-value pairs in the hashtable
        return new HashTableIterator();
    }
    //this is a private class that implements the iterator interface for the hashtable class it allows for
    //iterating over the key-value pairs in the hashtable
    private class HashTableIterator implements Iterator<MapEntry<K, V>>{
        //the class has 3 instance variables currentIndex - the index of the current bucket in the table array
        //currentEntry - the current mapEntry being processed nextIndex - the next mapEntry to be processed
        private int currentIndex =0;
        private MapEntry<K, V> currentEntry = null;
        private MapEntry<K, V> nextEntry = null;
        //constructor initializes the instance variable and calls the findNextEntry method to set the nextEntry
        //to the first non-null mapEntry in the table array
        private HashTableIterator(){
            findNextEntry();
        }
        //hasNext returns true if there is a nextEntry to be processed and false if not
        @Override
        public boolean hasNext() {
            return nextEntry!= null;
        }
        //next returns the currentEntry which is initially set to nextEntry if there is no nextEntry throws a
        //NoSuchElementException
        @Override
        public MapEntry<K, V> next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            currentEntry = nextEntry;
            //calls findNextEntry to update nextEntry to the next non-null mapEntry in the table array
            findNextEntry();
            return currentEntry;
        }
        //findNextEntry iterates through the table array starting from currentIndex
        private void findNextEntry(){
            nextEntry = null;

            //traverse the entire array and linked lists to find the next entry
            for (int i = currentIndex; i < TABLE_SIZE; i++){
                //if finds the next non-null mapEntry sets nextEntry to the head of the linkedlist at that index
                //and updates currentIndex
                if (table[i] != null){
                    nextEntry = table[i];
                    currentIndex = i +1;
                    break;//breaks loop if next entry found
                }
            }
            //check if nextEntry not null then iterates through the linkedlist
            if (nextEntry!=null){
                while (nextEntry.next!=null){
                    //updates nextEntry to the next MapEntry in the linkedlist
                    nextEntry = nextEntry.next;
                }
            }
        }
    }
    //enum HashingStrategy 2 different implementations of the hash method naive and sophisticated
    enum HashingStrategy{
        NAIVE{
            //naive implements using a simple sum of character values and modulo operationto compute the hash value
            @Override
            public int hash(String key, int tableSize){
                int hashVal = 0;
                String keyStr = key.toString();
                for (int i = 0; i < keyStr.length(); i++) {
                    hashVal += keyStr.charAt(i);
                }
                return hashVal % tableSize;
            }
        },
        SOPHISTICATED{
            //implements using FNV-1a hash fuction which is a more complex and efficient hashing algorithm
            @Override
            public int hash(String key, int tableSize) {
                int hashVal = 0x811c9dc5; // FNV-1a offset basis
                String keyStr = key.toString();
                for (int i = 0; i < keyStr.length(); i++) {
                    hashVal ^= keyStr.charAt(i);
                    hashVal += (hashVal << 1) + (hashVal << 4) + (hashVal << 7) + (hashVal << 8) + (hashVal << 24);
                }
                return Math.abs(hashVal % tableSize);
            }
        };
        public abstract int hash(String key, int tableSize);
    }
}
