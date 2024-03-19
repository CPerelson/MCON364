package Maps;

public class MapEntry<K, V>
{
    protected K key;
    protected V value;
    MapEntry<K, V> next;

    MapEntry(K key, V value)
    {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {return key;}
    public V getValue(){return value;}
    public void setValue(V value){
        this.value = value;
    }
    @Override
    public String toString()
    // Returns a string representing this MapEntry.
    {
        return "Key : " + key + "\nValue: " + value;
    }

}

