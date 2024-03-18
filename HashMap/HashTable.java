package Maps;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<K, V> implements MapInterface<K, V>, Iterable<MapEntry<K, V>>{
    public int TABLE_SIZE;
    private int size;
    public MapEntry<K, V>[] table;
    private HashFunctionLevel hashFunctionLevel;

    public HashTable(int ts) {
        this.TABLE_SIZE = ts;
        this.size = 0;
        this.hashFunctionLevel = HashFunctionLevel.NAIVE;//initiate to naive by default
        table = new MapEntry[TABLE_SIZE];
        for (int i = 0; i<TABLE_SIZE; i++){
            table[i]=null;
        }
    }

    //function to get the number of key-value pairs
    public int getSize(){
        return size;
    }

    //function to clear hash table
    public void makeEmpty(){
        for (int i = 0; i<TABLE_SIZE; i++){
            table[i]=null;
        }
    }

    //function to get value of a key
    public V get(K key){
        int hash = (myhash(key)% TABLE_SIZE);
        if (table[hash]==null){
            return null;
        }
        else {
            MapEntry<K, V> entry = table[hash];
            while (entry != null && !entry.key.equals(key)){
                entry = entry.next;
            }
            if (entry==null){
                return null;
            }
            else {
                return entry.value;
            }
        }
    }

    //function to insert a key value pair
    public void insert(K key, V value){
        int hash = (myhash(key)% TABLE_SIZE);
        if (hashFunctionLevel == hashFunctionLevel.NAIVE){
            hash = naiveHash(key);
        }
        else {
            hash = sophisticatedHash(key);
        }
        if (table[hash]==null){
            table[hash]=new MapEntry<>(key, value);
        }
        else {
            MapEntry<K, V> entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)){
                entry = entry.next;
            }
            if (entry.key.equals(key)){
                entry.value = value;
            }
            else {
                entry.next = new MapEntry<>(key, value);
            }
            size++;
        }
    }

    private int sophisticatedHash(K key) {
        //implement sophisticated hash here double hash function
    }

    private int naiveHash(K key) {
        //implement naive hash code here
        return key.hashCode() % TABLE_SIZE;
    }

    public V remove(K key){
        int hash = (myhash(key)% TABLE_SIZE);
        if (table[hash]!=null){
            MapEntry<K, V> prevEntry = null;
            MapEntry<K, V> entry = table[hash];
            while (entry.next!=null && !entry.key.equals(key)){
                prevEntry = entry;
                entry = entry.next;
            }
            if (entry.key.equals(key)){
                V value = entry.value; //store the value before removing the entry
                if (prevEntry==null){
                    table[hash]= entry.next;
                }
                else {
                    prevEntry.next = entry.next;
                }
                size--;
                return value; //return the value of the removed entry
            }
        }
        return null; //return null if the key is not found
    }

    //function myhash which gives a hash value for a given string
    public int myhash(K x) {
        int hashVal = x.hashCode();
        hashVal %= TABLE_SIZE;
        if (hashVal<0){
            hashVal += TABLE_SIZE;
        }
        return  hashVal;
    }

    //function to print hash table
    public void printHashTable(){
        for (int i = 0; i<TABLE_SIZE; i++){
            System.out.println("\nBucket "+(i+1)+" : ");
            MapEntry<K, V> entry = table[i];
            while (entry!=null){
                System.out.println(entry.value+" ");
                entry = entry.next;
            }
        }
    }

    public void setHashFunctionLevel(HashFunctionLevel level) {
        this.hashFunctionLevel = level;
    }

    @Override
    public V put(K k, V v) {
    // If an entry in this map with key k already exists then the value
    // associated with that entry is replaced by value v and the original
    // value is returned; otherwise, adds the (k, v) pair to the map and
    // returns null.
        if (k == null)
            throw new IllegalArgumentException("Maps do not allow null keys.");
        MapEntry<K, V> entry = new MapEntry<K, V>(k, v);
        MapEntry<K,V> temp;
        Iterator<MapEntry<K,V>> search = table.iterator(); // Arraylist iterator
        while (search.hasNext())
        {
            temp = search.next();
            if (temp.getKey().equals(k))
            {
                search.remove();
                table.add(entry);
                return temp.alue(); // k found, exits method
            }
        }
        // No entry is associated with k.
        table.add(entry);
        return null;
    }

    public boolean contains(K k)
    // Returns true if an entry in this map with key k exists;
    // Returns false otherwise.
    {
        if (k == null)
            throw new IllegalArgumentException("Maps do not allow null keys.");
        for (MapEntry<K,V> temp: table)
            if (temp != null && temp.getKey().equals(k))
                return true; // k found, exits method
        // No entry is associated with k.
        return false;
    }
    public boolean isEmpty()
    // Returns true if this map is empty; otherwise, returns false.
    {
        return size == 0; // uses ArrayList size
    }
    public boolean isFull()
    // Returns true if this map is full; otherwise, returns false.
    {
        return false; // An ArrayListMap is never full
    }
    public int size()
    // Returns the number of entries in this map.
    {
        return size; // uses ArrayList size
    }
    private class HashTableIterator implements Iterator<MapEntry<K, V>> {
        private int currentIndex;
        private MapEntry<K, V> currentEntry;
        private MapEntry<K, V> nextEntry;

        public HashTableIterator() {
            currentIndex = -1;
            nextEntry = null;
            advanceToNextEntry();
        }

        @Override
        public boolean hasNext() {
            return nextEntry != null;
        }

        @Override
        public MapEntry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            currentEntry = nextEntry;
            advanceToNextEntry();
            return currentEntry;
        }

        private void advanceToNextEntry() {
            if (nextEntry != null && nextEntry.next != null) {
                nextEntry = nextEntry.next;
            } else {
                currentIndex++;
                while (currentIndex < TABLE_SIZE) {
                    if (table[currentIndex] != null) {
                        nextEntry = table[currentIndex];
                        return;
                    }
                    currentIndex++;
                }
                nextEntry = null;
            }
        }
    }
    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        return new HashTableIterator();
    }
}
