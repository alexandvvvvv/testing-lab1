package lab_1.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class BTreeTest {
    private static BTree tree = new BTree(2);

    @Test
    public void insert() {
        tree.insert(10);
        tree.insert(2);
        tree.insert(5);
        tree.insert(9);
        tree.insert(3);
        tree.insert(1000);
        tree.insert(71);
        tree.insert(14);
        tree.insert(4);
        tree.insert(58);
        tree.insert(15);
        tree.insert(15);
        tree.insert(33);
        tree.insert(15);
        tree.insert(11);
        tree.insert(7);
        tree.insert(63);
        tree.insert(58);
        tree.insert(22);
        tree.insert(88);
        tree.insert(44);
        tree.insert(15);
        tree.insert(115);
        tree.insert(5);
        tree.insert(20);
        tree.insert(3);
        tree.insert(122);
        tree.insert(1222);
        tree.insert(1);tree.insert(10);
        tree.insert(2);
        tree.insert(5);
        tree.insert(9);
        tree.insert(3);
        tree.insert(1000);
        tree.insert(71);
        tree.insert(14);
        tree.insert(4);
        tree.insert(58);
        tree.insert(15);
        tree.insert(15);
        tree.insert(33);
        tree.insert(15);
        tree.insert(11);
        tree.insert(7);
        tree.insert(63);
        tree.insert(58);
        tree.insert(22);
        tree.insert(88);
        tree.insert(44);
        tree.insert(15);
        tree.insert(115);
        tree.insert(5);
        tree.insert(20);
        tree.insert(3);
        tree.insert(122);
        tree.insert(1222);
        tree.insert(1);
    }

    @Test
    public void splitChildren() {
    }
}