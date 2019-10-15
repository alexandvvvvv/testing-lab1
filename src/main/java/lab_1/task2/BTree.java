package lab_1.task2;


import java.util.ArrayList;
import java.util.List;

public class BTree{

    private class Node{
        private boolean isLeaf;
        private List<Integer> keys;
        private List<Node> children;

        private Node() {
            isLeaf = true;
            keys = new ArrayList<Integer>();
            children = new ArrayList<Node>();
        }
        int binarySearch(int key) {
            int leftIndex = 0;
            int rightIndex = keys.size() - 1;

            while (leftIndex <= rightIndex) {
                final int middleIndex = leftIndex + ((rightIndex - leftIndex) / 2);
                if (keys.get(middleIndex) < key) {
                    leftIndex = middleIndex + 1;
                } else if (keys.get(middleIndex) > key) {
                    rightIndex = middleIndex - 1;
                } else {
                    return middleIndex;
                }
            }
            return -1;
        }

        void shiftRightByOne() {
            if (!isLeaf) {
                children.add(null);
                children.set(keys.size() + 1, children.get(keys.size()));
            }
            int size = keys.size();
            keys.add(0);
            for (int i = size - 1; i >= 0; i--) {
                keys.set(i + 1, keys.get(i));
                if (!isLeaf) {
                    children.set(i + 1, children.get(i));
                }
            }
        }
        void remove(int index, int leftOrRightChild) {
            if (index >= 0) {
                int i;
                for (i = index; i < keys.size() - 1; i++) {
                    keys.set(i, keys.get(i + 1));
                    if (!isLeaf) {
                        if (i >= index + leftOrRightChild) {
                            children.set(i,children.get(i + 1));
                        }
                    }
                }
                keys.remove(i);
                if (!isLeaf) {
                    if (i >= index + leftOrRightChild) {
                        children.set(i,children.get(i + 1));
                    }
                    children.remove(i + 1);
                }
            }
        }

        int subtreeRootNodeIndex(int key) {
            for (int i = 0; i < keys.size(); i++) {
                if (key < keys.get(i)) {
                    return i;
                }
            }
            return keys.size();
        }
    }

    private int t;
    private  Node root;
    private static final int LEFT_CHILD_NODE = 0;
    private static final int RIGHT_CHILD_NODE = 1;

    public BTree(int t){
        this.t = t;
        root = new Node();
    }

    public void insert(int key){
        Node node = root;
        if (node.keys.size() == 2 * t - 1){
            root = new Node();
            root.isLeaf = false;
            root.children = new ArrayList<Node>();
            root.children.add(node);
            insertNonFull(root, key);
            if (root.keys.size() > 2 * t - 1)
                splitChildren(root, 0, 0);
        }
        else
            insertNonFull(node, key);
    }

    private void insertNonFull(Node node, int key) {
        int i = node.keys.size();
        if (node.isLeaf){
            node.keys.add(key);
            while (i >= 1 && key <= node.keys.get(i - 1)){
                node.keys.set(i, node.keys.get(i - 1));
                i--;
            }
            node.keys.set(i, key);
        } else {
            while (i >= 1 && key <= node.keys.get(i - 1)) {
                i--;
            }
            insertNonFull(node.children.get(i), key);
            if (node.children.get(i).keys.size() > 2 * t - 1){
                splitChildren(node, i, 1);
                if (key > node.keys.get(i))
                    i++;
            }
        }
    }

    private void splitChildren(Node node, int i, int count) {
        Node z = new Node();
        Node y = node.children.get(i);
        z.isLeaf = y.isLeaf;
        for (int j = 0; j < t - 1 + count; j++){
            z.keys.add(j, y.keys.get(j + t));
        }
        if (!y.isLeaf)
            for(int j = 0; j < t + count; j++)
                z.children.add(j, y.children.get(j + t));
        int key = y.keys.get(t - 1);
        y.keys = y.keys.subList(0, t - 1);
        if (!y.isLeaf)
            y.children = y.children.subList(0, t);

        node.children.add(null);
        for (int j = node.keys.size() ; j >= i + 1; j--)
            node.children.set(j + 1, node.children.get(j));
        node.children.set(i + 1, z);

        node.keys.add(0);
        for (int j = node.keys.size() - 2; j >= i; j--){
            node.keys.set(j + 1, node.keys.get(j));
        }
        node.keys.set(i, key);
    }



    public boolean delete(int key) {
        return delete(root, key);
    }

    private boolean delete(Node node, int key) {
        if (node.isLeaf) { // 1. If the key is in node and node is a leaf node, then delete the key from node.
            int i;
            if ((i = node.binarySearch(key)) != -1) { // key is i-th key of node if node contains key.
                node.remove(i, LEFT_CHILD_NODE);
                return true;
            } else
                return false;
        } else {
            int i;
            if ((i = node.binarySearch(key)) != -1) { // 2. If node is an internal node and it contains the key... (key is i-th key of node if node contains key)
                Node leftChildNode = node.children.get(i);
                Node rightChildNode = node.children.get(i + 1);
                if (leftChildNode.keys.size() >= t) { // 2a. If the predecessor child node has at least T keys...
                    Node predecessorNode = leftChildNode;
                    Node erasureNode = predecessorNode; // Make sure not to delete a key from a node with only T - 1 elements.
                    while (!predecessorNode.isLeaf) { // Therefore only descend to the previous node (erasureNode) of the predecessor node and delete the key using 3.
                        erasureNode = predecessorNode;
                        predecessorNode = predecessorNode.children.get(predecessorNode.keys.size());
                    }
                    node.keys.set(i, predecessorNode.keys.get(predecessorNode.keys.size() - 1));
                    return delete(erasureNode, node.keys.get(i));
                } else if (rightChildNode.keys.size() >= t) { // 2b. If the successor child node has at least T keys...
                    Node successorNode = rightChildNode;
                    Node erasureNode = successorNode; // Make sure not to delete a key from a node with only T - 1 elements.
                    while (!successorNode.isLeaf) { // Therefore only descend to the previous node (erasureNode) of the predecessor node and delete the key using 3.
                        erasureNode = successorNode;
                        successorNode = successorNode.children.get(0);
                    }
                    node.keys.set(i, successorNode.keys.get(0));
                    return delete(erasureNode, node.keys.get(i));
                } else { // 2c. If both the predecessor and the successor child node have only T - 1 keys...
                    // If both of the two child nodes to the left and right of the deleted element have the minimum number of elements,
                    // namely T - 1, they can then be joined into a single node with 2 * T - 2 elements.
                    int medianKeyIndex = mergeNodes(leftChildNode, rightChildNode);
                    moveKey(node, i, RIGHT_CHILD_NODE, leftChildNode, medianKeyIndex); // Delete i's right child pointer from node.
                    return delete(leftChildNode, key);
                }
            } else { // 3. If the key is not resent in node, descent to the root of the appropriate subtree that must contain key...
                // The method is structured to guarantee that whenever delete is called recursively on node "node", the number of keys
                // in node is at least the minimum degree T. Note that this condition requires one more key than the minimum required
                // by usual B-tree conditions. This strengthened condition allows us to delete a key from the tree in one downward pass
                // without having to "back up".
                i = node.subtreeRootNodeIndex(key);
                Node childNode = node.children.get(i); // childNode is i-th child of node.
                if (childNode.keys.size() == t - 1) {
                    Node leftChildSibling = (i - 1 >= 0) ? node.children.get(i - 1) : null;
                    Node rightChildSibling = (i  + 1 <= node.keys.size()) ? node.children.get(i + 1) : null;
                    if (leftChildSibling != null && leftChildSibling.keys.size() >= t) { // 3a. The left sibling has >= T keys...
                        // Move a key from the subtree's root node down into childNode along with the appropriate child pointer.
                        // Therefore, first shift all elements and children of childNode right by 1.
                        childNode.shiftRightByOne();
                        childNode.keys.set(0, node.keys.get(i - 1)); // i - 1 is the key index in node that is smaller than childNode's smallest key.
                        if (!childNode.isLeaf) {
                            childNode.children.set(0, leftChildSibling.children.get(leftChildSibling.keys.size()));
                        }

                        // Move a key from the left sibling into the subtree's root node.
                        node.keys.set(i - 1, leftChildSibling.keys.get(leftChildSibling.keys.size() - 1));

                        // Remove the key from the left sibling along with its right child node.
                        leftChildSibling.remove(leftChildSibling.keys.size() - 1, RIGHT_CHILD_NODE);
                    } else if (rightChildSibling != null && rightChildSibling.keys.size() >= t) { // 3a. The right sibling has >= T keys...
                        // Move a key from the subtree's root node down into childNode along with the appropriate child pointer.
                        //childNode.keys.set(childNode.keys.size() - 1, node.keys.get(i)); // i is the key index in node that is bigger than childNode's biggest key.
                        childNode.keys.add(node.keys.get(i)); // i is the key index in node that is bigger than childNode's biggest key.
                        if (!childNode.isLeaf) {
                            childNode.children.add( rightChildSibling.children.get(0));
                        }
                        // Move a key from the right sibling into the subtree's root node.
                        node.keys.set(i, rightChildSibling.keys.get(0));

                        // Remove the key from the right sibling along with its left child node.
                        rightChildSibling.remove(0, LEFT_CHILD_NODE);
                    } else { // 3b. Both of childNode's siblings have only T - 1 keys each...
                        if (leftChildSibling != null) {
                            int medianKeyIndex = mergeNodes(childNode, leftChildSibling);
                            moveKey(node, i - 1, LEFT_CHILD_NODE, childNode, medianKeyIndex); // i - 1 is the median key index in node when merging with the left sibling.
                        } else if (rightChildSibling != null) {
                            int medianKeyIndex = mergeNodes(childNode, rightChildSibling);
                            moveKey(node, i, RIGHT_CHILD_NODE, childNode, medianKeyIndex); // i is the median key index in node when merging with the right sibling.
                        }
                    }
                }
                return delete(childNode, key);
            }
        }
    }

    // Merge two nodes and keep the median key (element) empty.
    private int mergeNodes(Node dstNode, Node srcNode) {
        int medianKeyIndex;
        if (srcNode.keys.get(0) < dstNode.keys.get(dstNode.keys.size() - 1)) {
            int i;
            // Shift all elements of dstNode right by srcNode.mNumKeys + 1 to make place for the srcNode and the median key.

            for (i = 0; i <= srcNode.keys.size(); i++)
                dstNode.shiftRightByOne();

            // Clear the median key (element).
            medianKeyIndex = srcNode.keys.size() ;//- 1;
            dstNode.keys.set(medianKeyIndex,  0);

            // Copy the srcNode's elements into dstNode.
            for (i = 0; i < srcNode.keys.size(); i++) {
                dstNode.keys.set(i, srcNode.keys.get(i));
                if (!srcNode.isLeaf) {
                    dstNode.children.set(i, srcNode.children.get(i));
                }
            }
            if (!srcNode.isLeaf) {
                dstNode.children.set(i, srcNode.children.get(i));
            }
        } else {
            // Clear the median key (element).

            dstNode.keys.add(0);
            medianKeyIndex = dstNode.keys.size() - 1;

            // Copy the srcNode's elements into dstNode.
            int offset = medianKeyIndex ;//+ 1;
            int i;
            for (i = 0; i < srcNode.keys.size(); i++) {
                dstNode.keys.add(/*offset + i,*/ srcNode.keys.get(i));
                if (!srcNode.isLeaf) {
                    dstNode.children.add(/*offset + i,*/ srcNode.children.get(i));
                }
            }
            if (!srcNode.isLeaf) {
                dstNode.children.add(/*offset + i, */srcNode.children.get(i));
            }
        }

        return medianKeyIndex;
    }

    // Move the key from srcNode at index into dstNode at medianKeyIndex. Note that the element at index is already empty.
    private void moveKey(Node srcNode, int srcKeyIndex, int childIndex, Node dstNode, int medianKeyIndex) {
        dstNode.keys.set(medianKeyIndex, srcNode.keys.get(srcKeyIndex));

        srcNode.remove(srcKeyIndex, childIndex);

        if (srcNode == root && srcNode.keys.size() == 0) {
            root = dstNode;
        }
    }

    private List<String> printBTree(Node node, int level) {
        List<String> result = new ArrayList<String>(level);
        String string = "";

        if (node != null) {
            if (node.isLeaf) {
                for (int i = 0; i < node.keys.size(); i++) {
                    string += node.keys.get(i) + ", ";
                }
            } else {
                int i;
                for (i = 0; i < node.keys.size(); i++) {
                    string += node.keys.get(i) + ", ";
                    result.add(printBTree(node.children.get(i), level + 1).toString());
                }
                result.add(printBTree(node.children.get(i), level + 1).toString());
            }
            if (!string.isEmpty())
                result.add(string.substring(0, string.length() - 2));
        }
        if (result.size() > 0){
            String temp = result.get(result.size() - 1);
            for (int i = result.size() - 1; i > 0; i--) {
                result.set(i, result.get(i - 1));
            }
            result.set(0, temp);
        }
        return result;
    }

    public String toString() {
        List<String> list = printBTree(root, 0);

        return (list != null && list.size() > 0) ? list.toString() : "[]";
    }

}