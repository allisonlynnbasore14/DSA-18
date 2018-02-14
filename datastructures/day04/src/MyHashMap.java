import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per map before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per map before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;
    private int numBuckets = 0;
    public int count = 0;
    public int outcount = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;
        //private Entry next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {

        int index = key.hashCode() % numBuckets;

        return buckets[index];

    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {

        for (Map.Entry entry : entrySet()) {
            if(entry.getKey()==key){
                return true;
            }
        }
        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        // find the key that would correspond
        for (Map.Entry entry : entrySet()) {
            if(entry.getValue()==value){
                return true;
            }
        }
        return false;
    }


    @Override
    public V get(Object key){
        LinkedList<Entry> b = chooseBucket(key);
        for (Map.Entry entry : b) {
            if ( key.equals(entry.getKey())) {
                V e = (V) entry.getValue();
                return e;
            }

        }
        return null;
    }


    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {

        size ++;
        if(size > numBuckets * ALPHA){
            rehash(GROWTH_FACTOR);
            size ++;
        }

        Entry newE = new Entry(key, value);
        V prevE = null;
        LinkedList<Entry> b = chooseBucket(key);
        for (Map.Entry entry : b) {

            if(entry != null){
                prevE = (V) entry.getValue();
                if(entry.getKey() == newE.getKey()){
                    remove( key);
                    b.add(newE);
                    return prevE;
                }

            }

        }

        b.add(newE);
        return null;
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {


        LinkedList<Entry> b = chooseBucket(key);
        for (Map.Entry entry : b) {

            Object val = entry.getValue();

            b.remove();
            size --;
            if(size <= numBuckets * BETA && numBuckets * SHRINK_FACTOR >= MIN_BUCKETS){
                rehash(SHRINK_FACTOR);
            }
            return (V) val;
        }


        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {

            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {

        LinkedList<MyHashMap<K, V>.Entry>[] bucketsOld  = buckets;
        // hint: once you have removed all values from the buckets, use put(k, v) to add them back in the correct place
        initBuckets( (int) ((double) numBuckets * growthFactor));
        //numBuckets = numBuckets * (int) growthFactor;
        size = 0;

        for (LinkedList<MyHashMap<K, V>.Entry> b : bucketsOld) {
            for (Map.Entry entry : b) {
                put((K) entry.getKey(), (V) entry.getValue());
            }
        }

    }

    private void initBuckets(int size) {

        numBuckets = 0;

        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            numBuckets  = numBuckets + 1;
            buckets[i] = new LinkedList<>();
        }

    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
            e.getValue();
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}
