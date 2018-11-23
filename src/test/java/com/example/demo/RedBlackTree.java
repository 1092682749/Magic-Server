package com.example.demo;

import javax.swing.tree.TreeNode;
import java.util.Map;
import java.util.TreeMap;

public class RedBlackTree {
    public static final Boolean RED = false;
    public static final Boolean BLACK = true;
    class TreeNode<K, V> implements Map.Entry<K, V> {
        private Boolean color = BLACK;
        K key;
        V value;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;
        private TreeNode<K, V> parent;
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
            V oldValue = this.value;
            if (value != null) {
                oldValue = this.value;
            }
            this.value = value;
            return oldValue;
        }
    }
}
