package lab_1.tsk3;

public class LightSource extends SpaceObject {
    private Flash flash;

    private boolean isSpreading;

    public LightSource(){
        this.flash = new Flash(false, Flash.FlashColors.NONE);
        this.isSpreading = false;
    }
    public LightSource(Flash flash) {
        if (flash == null)
            throw new NullPointerException("Flash expected");
        this.flash = flash;
        this.isSpreading = false;
    }

    void SparkleAndStartSpreading(){
        if (!flash.isBright())
            throw new IllegalStateException("Should be bright");
        flash.setBright(false);

        if (this.isSpreading())
            throw new IllegalStateException("Should not be spreading");
        isSpreading = true;
    }



    public Flash getFlash() {
        return flash;
    }

    public void setFlash(Flash flash) {
        this.flash = flash;
    }
    public boolean isSpreading() {
        return isSpreading;
    }

    public void setSpreading(boolean spreading) {
        isSpreading = spreading;
    }
}
