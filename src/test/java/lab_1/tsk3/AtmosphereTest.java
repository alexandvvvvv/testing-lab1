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

        atmosphere = new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.NONE)), new Halfmoon(), new Star(true) );

    }

    @Test
    public void testAtmosphereWithNonWideHalfmoon() {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Should be wide"));

        atmosphere = new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.RED)), new Halfmoon(false), new Star(true) );

    }

    @Test
    public void testAtmosphereWithWrongStarCOlor() {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Star should have white color"));

        atmosphere = new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.RED)), new Halfmoon(true), new Star(Star.FireColor.BLACK) );

    }

    @Test
    public void testAtmosphereIdeal(){
        atmosphere = new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.RED)), new Halfmoon(true), new Star(Star.FireColor.WHITE) );
    }


    @Test
    public void getHalfmoon() throws Exception {
    }

    @Test
    public void setHalfmoon() throws Exception {
    }

    @Test
    public void getLightSource() throws Exception {
    }

    @Test
    public void setLightSource() throws Exception {
    }

    @Test
    public void getStar() throws Exception {
    }

    @Test
    public void setStar() throws Exception {
    }

}