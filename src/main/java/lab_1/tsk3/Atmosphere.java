package lab_1.tsk3;

public class Atmosphere {

    private LightSource lightSource;


    public Atmosphere(LightSource lightSource) {

        if (lightSource.getFlash().getColor() == Flash.FlashColors.NONE)
            throw new IllegalStateException("Should be colorful Flash");

        this.lightSource = lightSource;

        this.lightSource.SparkleAndStartSpreading();


    }

    public LightSource getLightSource() {
        return lightSource;
    }

    public void setLightSource(LightSource lightSource) {
        this.lightSource = lightSource;
    }
}
