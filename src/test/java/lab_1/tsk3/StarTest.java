package lab_1.tsk3;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class StarTest {
    private Star star;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testStarNotWide() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Shoulb be wide"));

        star = new Star(false);
        star.startBurning();
    }
    @Test
    public void testStarStillSpreading() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Should have stopped spreading"));

        star = new Star(true, true, new Flash(true, Flash.FlashColors.NONE));
        star.startBurning();
    }
    @Test
    public void testStarIsNotBright() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Should have bright flash"));

        star = new Star(true, false, new Flash(false, Flash.FlashColors.NONE));
        star.startBurning();
    }
}