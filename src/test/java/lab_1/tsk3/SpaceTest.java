package lab_1.tsk3;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SpaceTest {

    Space space;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testSpaceWithNotWideHalfmoon() {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Should be wide"));

        space = new Space(new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.RED))), new Halfmoon(false), new Star(true) );

    }

    @Test
    public void testSpaceWithWrongStarCOlor() {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Star should have white color"));

        space = new Space(new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.RED))), new Halfmoon(true), new Star(Star.FireColor.BLACK) );
    }

    @Test
    public void testSpaceWithNotShiningAtmosphereLight() {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
                CoreMatchers.equalTo("Should be bright"));

        space = new Space(new Atmosphere(new LightSource(new Flash(false, Flash.FlashColors.RED))), new Halfmoon(true), new Star(Star.FireColor.WHITE) );
    }


    @Test
    public void testSpaceIdeal(){
        space = new Space(new Atmosphere(new LightSource(new Flash(true, Flash.FlashColors.RED))), new Halfmoon(true), new Star(Star.FireColor.WHITE) );
    }


}