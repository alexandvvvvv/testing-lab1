package lab_1.task2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class BTreeTest {
    private static BTree tree;// = new BTree(2);

    @Before
    public void initBTree(){
        tree = new BTree(2);
    }

    @Test
    public void insertOneValue() {
        tree.insert(22);
        assertEquals(null, tree.toString(), "[22]");
    }

    @Test
    public void insertTwoValues() {
        for (int key: List.of(5, 9)   ) {
            tree.insert(key);
        }
        assertEquals(null, tree.toString(), "[5, 9]");
    }

    @Test
    public void insertThreeValues() {
        for (int key: List.of(5, 10, 22)   ) {
            tree.insert(key);
        }
        assertEquals(null, tree.toString(), "[5, 10, 22]");
    }

    @Test
    public void insertFourValues() {
        for (int key: List.of(5, 9, 10, 22))
            tree.insert(key);
        assertEquals(null, tree.toString(), "[9, [5], [10, 22]]");
    }

    @Test
    public void insertFourValuesInAnotherOrder() {
        for (int key: List.of(22, 5, 10, 9))
            tree.insert(key);
        assertEquals(null, tree.toString(), "[9, [5], [10, 22]]");
    }

    @Test
    public void insertManyValues() {
        for (int key: List.of(10, 3, 5, 1000, 48, 64, 2, 9, 10, 22))
            tree.insert(key);
        assertEquals(null, tree.toString(), "[5, 10, 48, [2, 3], [9], [10, 22], [64, 1000]]");
    }

    @Test
    public void insertVeryManyValues() {
        for (int key: List.of(10, 3, 5, 1000, 48, 64, 2, 9, 10, 22, 999, 88, 4, 7, 3))
            tree.insert(key);
        assertEquals(null, tree.toString(), "[10, [3, 5, [2], [3, 4], [7, 9]], [48, 88, [10, 22], [64], [999, 1000]]]");
    }

    @Test
    public void insertAVeryLotOfValues() {
        for (int key: List.of(10, 3, 5, 1000, 48, 64, 2, 9, 10, 22, 999, 88, 4, 7, 3, 1001, 11, 101, 1010, 127, 121, 111))
            tree.insert(key);
        assertEquals(null, tree.toString(), "[10, 88, [3, 5, [2], [3, 4], [7, 9]], [48, [10, 11, 22], [64]], [111, 999, [101], [121, 127], [1000, 1001, 1010]]]");
    }

    @Test
    public void insertValues() {
        for (int key: List.of(10, 3, 5, 1000, 48, 64, 2, 9, 10, 22, 999, 88, 4, 7, 3, 1001, 11, 101, 1010, 127, 121, 111, 24, 25, 26, 27, 28, 29, 30, 31, 32 ,33))
            tree.insert(key);
        assertEquals(null, tree.toString(), "[24, " +
                "[10, " +
                    "[3, 5, [2], [3, 4], [7, 9]], " +
                    "[11, [10], [22]]], " +
                "[28, 88, " +
                    "[26, [25], [27]], " +
                    "[30, 48, [29], [31, 32, 33], [64]], " +
                    "[111, 999, [101], [121, 127], [1000, 1001, 1010]]]]");
    }

    @Test
    public void deleteKeyFromLeafNotExpectingMerging(){
        for (int key: List.of(5, 9, 10, 22))
            tree.insert(key);
        tree.delete(10);
        assertEquals(null, tree.toString(), "[9, [5], [22]]");
    }

    @Test
    public void deleteKeyFromLeafExpectingMerging(){
        for (int key: List.of(5, 9, 22))
            tree.insert(key);
        tree.delete(22);
        assertEquals(null, tree.toString(), "[5, 9]");
    }

    @Test
    public void deleteKeyFromRootExpectingMerging(){
        for (int key: List.of(5, 9, 22))
            tree.insert(key);
        tree.delete(9);
        assertEquals(null, tree.toString(), "[5, 22]");
    }

    @Test
    public void deleteKeyFromLeafExpectingMovingKey(){
        for (int key: List.of(5, 9, 22, 100))
            tree.insert(key);
        tree.delete(5);
        assertEquals(null, tree.toString(), "[22, [9], [100]]");
    }

    @Test
    public void deleteAllKeys(){
        List<Integer> list = List.of(66,12, 34, 110, 8, 34,75,887, 2, 12,442, 2,11, 45, 21, 45, 75,23,57,22);
        for (int key: list)
            tree.insert(key);
        for (int key: list)
            tree.delete(key);
        assertEquals(null, tree.toString(), "[]");
    }

    @Test
    public void insertRandomKeysAndDeleteAll(){
        int range = ThreadLocalRandom.current().nextInt(1070, 1080);
        List<Integer> list = new ArrayList<>(range);
        for (int i = 0; i < range; i++)
            list.add(ThreadLocalRandom.current().nextInt(-50, 50));
        for (int key: list)
            tree.insert(key);
        for (int key: list)
            tree.delete(key);
        assertEquals(null, tree.toString(), "[]");

    }


}