package lab_1.tsk3;

public class Star extends Halfmoon {

    public FireColor getFireColor() {
        return fireColor;
    }

    public void setFireColor(FireColor fireColor) {
        this.fireColor = fireColor;
    }

    public enum FireColor{
        WHITE, BLACK
    }

    private FireColor fireColor;
    private boolean burnsAllAround;

    public Star(FireColor fireColor){
        this.setFlash(new Flash(true, Flash.FlashColors.NONE));
        this.setWide(true);
        if (fireColor == null)
            throw new NullPointerException("null fireColor");
        this.fireColor = fireColor;
    }
    void startBurning(){
        if (this.isWide()){
            if (!this.isSpreading()){
                if (this.getFlash().isBright()){
                    this.burnsAllAround = true;
                } else throw new IllegalStateException("Should have bright flash");
            } else throw new IllegalStateException("Should have stopped spreading");
        }else throw new IllegalStateException("Shoulb be wide");
    }

    public Star(boolean isWide){
        this.setWide(isWide);
    }
    public Star(boolean isWide, boolean isSpreading, Flash flash){
        this.setWide(isWide);
        this.setSpreading(isSpreading);
        this.setFlash(flash);
    }

    public boolean isBurnsAllAround() {
        return burnsAllAround;
    }

    public void setBurnsAllAround(boolean burnsAllAround) {
        this.burnsAllAround = burnsAllAround;
    }
}
