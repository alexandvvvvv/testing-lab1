package lab_1.task2;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.List;

public class BTree{

    private class Node{
        private boolean isLeaf;
        private List<Integer> keys;
        private List<Node> children;

        private Node(int n) {
            isLeaf = true;
            keys = new ArrayList<Integer>();
            children = new ArrayList<Node>();
        }


        public boolean isLeaf() {
            return isLeaf;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }
    }

    private int t;
    private  Node root;

    public BTree(int t){
        this.t = t;
        root = new Node(t - 1);
    }

    public void insert(int key){
        Node node = root;
        if (node.keys.size() == 2 * t - 1){
            root = new Node(t - 1);
            root.isLeaf = false;
            root.children = new ArrayList<Node>();
            root.children.add(node);
            splitChildren(root,0);
            insertNonFull(root, key);
        }
        else
            insertNonFull(node, key);
    }

    private void insertNonFull(Node node, int key) {
        int i = node.keys.size();
        if (node.isLeaf){
            node.keys.add(key);
            while (i >= 1 && key < node.keys.get(i - 1)){
                node.keys.set(i, node.keys.get(i - 1));
                i--;
            }
            node.keys.set(i, key);
        } else {
            while (i >= 1 && key < node.keys.get(i - 1)) {
                i--;
            }
            i++;
            if (node.children.get(i - 1).keys.size() == 2 * t - 1){
                splitChildren(node, i - 1);
                if (key > node.keys.get(i - 1))
                    i++;
            }
            insertNonFull(node.children.get(i - 1), key);
        }
    }

    public void splitChildren(Node node, int i) {
        Node z = new Node(t - 1);
        Node y = node.children.get(i);
        z.isLeaf = y.isLeaf;
        for (int j = 0; j < t - 1; j++){
            z.keys.add(j, y.keys.get(j + t));
        }
        if (!y.isLeaf)
            for(int j = 0; j < t; j++)
                z.children.add(j, y.children.get(j + t));
        int key = y.keys.get(t - 1);
        y.keys = y.keys.subList(0, t - 1);
        if (!y.isLeaf)
            y.children = y.children.subList(0, t);
        for (int j = node.keys.size() ; j > i + 1; j--)
            node.children.add(j + 1, node.children.get(j));
        node.children.add(i + 1, z);
        for (int j = node.keys.size() - 1; j > i; j--){
            node.keys.add(j + 1, node.keys.get(j));
        }
        node.keys.add(i, key);


    }


}