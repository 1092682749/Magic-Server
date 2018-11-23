package com.example.demo;

import java.util.*;

public class MyHashMap<K, V> extends AbstractMap<K, V> {
    int SIZE = 900;
    List<Map.Entry<K, V>>[] buckets = new LinkedList[SIZE];
    @Override
    public int size() {
        int size = 0;
        for (List<Map.Entry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            size += bucket.size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        Boolean isEmpty = true;
        for (List<Map.Entry<K ,V>> bucket: buckets) {
            if (bucket != null) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    @Override
    public boolean containsKey(Object key) {
        Boolean isContain = false;
        for (List<Map.Entry<K ,V>> bucket : buckets) {
            if (bucket.contains(key)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

//    @Override
//    public boolean containsValue(Object value) {
//        return false;
//    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = key.hashCode()%SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<Entry<K, V>>();
        }
        buckets[index].add(new MyEntry(key, value));
        return null;
    }

    @Override
    public V remove(Object key) {
        int keyHash = key.hashCode();
        HashMap.Entry returnHash = null;
        for (HashMap.Entry entry : buckets[keyHash]) {
            if (entry.getKey().equals(key)) {
                buckets[keyHash].remove(entry);
                returnHash = entry;
            }
        }
        return (V)returnHash.getValue();
    }

    @Override
    public void clear() {
        buckets = null;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
    class MyEntry implements Map.Entry<K, V>{
        private K key;
        private V value;
        MyEntry(K key, V value) {
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
        public V setValue(V value) {
            V oldValue = null;
            if (value != null) {
                oldValue = this.value;
            }
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MyHashMap.MyEntry)) {
                return false;
            }
            MyHashMap.MyEntry entry = (MyEntry) o;
            return this.hashCode() == entry.hashCode();
        }

        @Override
        public int hashCode() {
            return key.hashCode() + value.hashCode();
        }
    }
    public static void main(String[] args) {
        MyHashMap<String, String> hashMap = new MyHashMap<>();
        System.out.println(hashMap.isEmpty());
        hashMap.put("111", "aaa");
        System.out.println(hashMap.isEmpty());
        System.out.println(hashMap.get("111"));
        System.out.println(hashMap.size());
    }
}
