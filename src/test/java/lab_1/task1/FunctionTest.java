package lab_1.task1;

import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionTest {

    private static final double EPS = 1E-6;
    private static final double DELTA = 1E-5;

    @Test
    public void testFuncNaN(){
        double x = Double.NaN;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusOne(){
        double x = -1;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusOneDelta(){
        double x = -1 + DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusOneMDelta(){
        double x = -1 - DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusBorder(){
        double x = -0.96;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusBorderDelta(){
        double x = -1 + DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusBorderMDelta(){
        double x = -0.96 - DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncZero(){
        double x = 0;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncZeroDelta(){
        double x = DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncZeroMDelta(){
        double x = -DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncBorder(){
        double x = 0.96;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncBorderDelta(){
        double x = 0.96 + DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncBorderMDelta(){
        double x = 0.96 - DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncOne(){
        double x = 1;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncOneDelta(){
        double x = 1 + DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFuncOneMDelta(){
        double x = 1 - DELTA;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc1Zone1(){
        double x = Double.NEGATIVE_INFINITY;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc1Zone2(){
        double x = -14.88;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc2Zone1(){
        double x = -0.9894;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc2Zone2(){
        double x = -0.96344;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc3Zone1(){
        double x = -0.55;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc3Zone2(){
        double x = -0.02;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc4Zone1(){
        double x = Math.PI - 3;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc4Zone2(){
        double x = 0.9514882281703;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc5Zone1(){
        double x = 0.965544332211;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc5Zone2(){
        double x = 0.99;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc6Zone1(){
        double x = Double.POSITIVE_INFINITY;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }

    @Test
    public void testFunc6Zone2(){
        double x = 2.28;
        assertEquals(Math.acos(x), Function.arccos(x, EPS), DELTA);
    }
}
