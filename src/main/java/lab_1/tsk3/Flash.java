package lab_1.tsk3;

public class Flash {

    private boolean IsBright;
    private FlashColors color;
    public Flash(boolean isBright, FlashColors color) {
        IsBright = isBright;
        this.color = color;
    }

    public boolean isBright() {
        return IsBright;
    }

    public void setBright(boolean bright) {
        IsBright = bright;
    }

    public FlashColors getColor() {
        return color;
    }

    public void setColor(FlashColors color) {
        this.color = color;
    }

    public enum FlashColors{
        RED, GREEN, BLUE, NONE
    }

}
