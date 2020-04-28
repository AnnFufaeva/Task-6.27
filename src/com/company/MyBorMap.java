package com.company;

import java.util.*;
import java.lang.String;

public class MyBorMap<V> implements Map<String, V> {
    private class Node implements Comparable<Node>{
        public V value;
        public List<Node> children;
        public boolean leaf;
        public char edge;
        public Node parent;

        public Node(){
            leaf = false;
            children = new ArrayList();
            value = null;
            parent = null;

        }
        public Node(Node p){
            leaf = false;
            children = new ArrayList();
            value = null;
            parent = p;
        }
        @Override
        public int compareTo(Node n) {
            return (this.edge - n.edge);
        }

    }

    Node root;
    int size = 0;

    public MyBorMap(){
        root = new Node();
    }


    public int size(){ return size;}

    public boolean isEmpty() {
        if (root.children.size() == 0) return true;
        return false;
    }

    public boolean containsKey(Object key) {
        Node cur = root;
        for (char ch : key.toString().toCharArray()){
            int i = checkList(cur.children, ch);
            if (i == -1) return false;
            cur = cur.children.get(i);
        }
        return true;
    }

    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public V get(Object key) {
        Node cur = root;
        V value;
        for (char ch : key.toString().toCharArray()){
            int i = checkList(cur.children, ch);
            if (i == -1) return null;
            cur = cur.children.get(i);
        }
        value = cur.value;
        return value;
    }

    public V put(String key, V value) {
        Node cur = root;
        for (char ch : key.toCharArray()) {
            int i = checkList(cur.children, ch);
            if (i == -1) {
                Node n = new Node(cur);
                n.edge = ch;
                cur.children.add(n);
                cur = n;
            }
            else cur = cur.children.get(i);

        }
        V last = cur.value;
        if (last == null){
            cur.leaf = true;
            size++;
        }
        cur.value = value;
        return last;
    }

    public V remove(Object key) {
        Node cur = root;
        V value = null;
        for (char ch : key.toString().toCharArray()){
            int i = checkList(cur.children, ch);
            if (i == -1) return null;
            cur = cur.children.get(i);
        }
        if (cur.leaf) {
            value = cur.value;
            cur.value = null;
            cur.leaf = false;
            if (cur.children.size() == 0) {
                while (cur.children.size() == 0) {
                    char ed = cur.edge;
                    cur = cur.parent;
                    if (cur == null) return value;
                    int i = checkList(cur.children, ed);
                    cur.children.remove(i);
                }
            }
            size--;
        }
        return value;
    }

    public void putAll(Map<? extends String, ? extends V> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        root.children = new ArrayList<>();
    }

    public Set<String> keySet() {
        Set<String> keys = new TreeSet<>();
        String str = "";
        sortBor(root);
        getKeys(str, root, keys);
        return keys;
    }

    public Set<String> keySet(String prefix) {
        Set<String> keys = new TreeSet<>();
        Node cur = root;
        String str = "";
        sortBor(root);
        for (char ch : prefix.toCharArray()){
            int i = checkList(cur.children, ch);
            if (i == -1) return null;
            str += ch;
            cur = cur.children.get(i);
        }
        if (str.length() != 0) str = str.substring(0, str.length() - 1);
        getKeys(str, cur, keys);
        return keys;
    }

    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Entry<String, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private int checkList(List<Node> lst, char ch){
        if (lst != null)
            for (int i = 0; i < lst.size(); i++){
                if (lst.get(i).edge == ch) return i;
            }
        return -1;
    }

    private void sortBor(Node cur){
        if(cur != null)
            if (cur.children.size() != 0){
                cur.children.sort(Node::compareTo);
                for (int i = 0; i < cur.children.size(); i++)
                    cur = cur.children.get(i);
                sortBor(cur);
            }
    }

    private Set<String> getKeys(String str, Node n, Set<String> keys){
        String s = str;
        s += n.edge;
        if(n.leaf == true) keys.add(s);

        if (n.children.size() != 0){
            for (int i = 0; i < n.children.size(); i++) {
                getKeys(s, n.children.get(i), keys);
            }
        }
        return keys;
    }




}
