package lab_1.tsk3;

public class Halfmoon extends LightSource {
    private boolean isWide;

    public Halfmoon(){
        this.isWide = false;
    }

    public Halfmoon(boolean isWide) {
        this.isWide = isWide;
    }

    public boolean isWide() {
        return isWide;
    }

    public void setWide(boolean wide) {
        isWide = wide;
    }
}
