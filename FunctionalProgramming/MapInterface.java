package FunctionalProgramming;

public interface MapInterface<K, V> extends Iterable<MapEntry<K,V>>
    //MapInterface is parameterized with generic types K (for keys) and V (for values).
        // It extends the Iterable interface, which allows the implementation class to be iterated over
        // using an Iterator.
{
    V put(K k, V v);
    //put takes the key and value and either adds a new key-value pair to the map or updates the
    //value if the key already exists. the original value is returned if the key already exists or
    //null if its a new entry
    V get(K k);
    //get takes a key and returns the value associated with that key if it exists in the map
    //or null if the key is not found
    V remove(K k);
    //remove takes a key and removes the key-value pair form the map if it exists
    //returns the value associated with the removed key or null if key is not found
    boolean contains(K k);
    //contains takes a key and returns true if the key exists in the map or false otherwise
    boolean isEmpty();
    //isEmpty returns true if the map is empty or false otherwise
    boolean isFull();
    //isFull returns true if map is false otherwise returns false
    //is never full so always false
    int size();
    //size returns the number of key-value pairs currently stored in the map
}
