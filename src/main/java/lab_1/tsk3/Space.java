package lab_1.tsk3;

public class Space {

    private Halfmoon halfmoon;
    private Star star;
    private Atmosphere atmosphere;

    public Space(Atmosphere atmosphere, Halfmoon halfmoon, Star star){

        if (!halfmoon.isWide())
            throw new IllegalStateException("Should be wide");

        this.halfmoon = halfmoon;

        if (star.getFireColor() != Star.FireColor.WHITE)
            throw new IllegalStateException("Star should have white color");

        this.star = star;
        this.star.startBurning();

        if (!atmosphere.getLightSource().isSpreading())
            throw new IllegalStateException("Atmosphere's light is not spreading");
        if (!atmosphere.getLightSource().getFlash().isBright())
            throw new IllegalStateException("Atmosphere's light is not bright");
        this.atmosphere = atmosphere;
    }
}
