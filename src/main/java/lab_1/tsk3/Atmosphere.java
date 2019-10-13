package lab_1.tsk3;

public class Atmosphere {

    private LightSource lightSource;
    private Halfmoon halfmoon;
    private Star star;


    public Atmosphere(LightSource lightSource, Halfmoon halfmoon, Star star) {

        if (lightSource.getFlash().getColor() == Flash.FlashColors.NONE)
            throw new IllegalStateException("Should be colorful Flash");

        this.lightSource = lightSource;

        this.lightSource.SparkleAndStartSpreading();

        if (!halfmoon.isWide())
            throw new IllegalStateException("Should be wide");

        this.halfmoon = halfmoon;

        if (star.getFireColor() != Star.FireColor.WHITE)
            throw new IllegalStateException("Star should have white color");

        this.star = star;
        this.star.startBurning();
    }

    public Halfmoon getHalfmoon() {
        return halfmoon;
    }

    public void setHalfmoon(Halfmoon halfmoon) {
        this.halfmoon = halfmoon;
    }

    public LightSource getLightSource() {
        return lightSource;
    }

    public void setLightSource(LightSource lightSource) {
        this.lightSource = lightSource;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }
}
