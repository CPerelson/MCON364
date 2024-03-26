package Maps;

public class MapEntry<K, V>
{
    //MapEntry class is generic types.
    //has 3 instances key - keu of the entry, value - value associated with the key,
    //next - a reference to the next MapEntry in the linkedlist used for handling collisions
    protected K key;
    protected V value;
    MapEntry<K, V> next;

    //MapEntry constructor initialized the key and value with the provided arguments and sets next
    //to null - which indicates that it's the end of the linkedlist
    MapEntry(K key, V value)
    {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    //getters and setters for the key and value instance variables
    public K getKey() {return key;}
    public V getValue(){return value;}
    public void setValue(V value){
    }

    @Override
    public String toString()
    // Returns a string representing this MapEntry.
    {
        return "Key : " + key + "\nValue: " + value;
    }

}
