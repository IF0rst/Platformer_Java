package Instances;

public class Velocity {
    private int XVel = 0;
    private int YVel = 0;

    private Boolean CanJump = false;

    private Boolean WallTouched = false;

    public void setJump(Boolean State){
        this.CanJump = State;
    }

    public Boolean getJump(){
        return this.CanJump;
    }

    public void setWallTouched(Boolean State){
        this.WallTouched = State;
    }

    public Boolean getWallTouched(){
        return this.WallTouched;
    }

    public int getXVel() {
        return XVel;
    }

    public void setXVel(int xVel) {
        XVel = xVel;
    }

    public int getYVel() {
        return YVel;
    }

    public void setYVel(int yVel) {
        YVel = yVel;
    }
}
