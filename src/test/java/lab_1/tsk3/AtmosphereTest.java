package lab_1.tsk3;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class AtmosphereTest {
    Atmosphere atmosphere;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAtmosphereWithNoneColorfulFlash() {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Should be colorful Flash"));

        atmosphere = new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.NONE)));//, new Halfmoon(), new Star(true) );

    }



}