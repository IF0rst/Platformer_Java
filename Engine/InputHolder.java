package Engine;

public class InputHolder {
    private Boolean Right = false;
    private Boolean Left = false;
    private Boolean Up = false;
    private Boolean Down = false;

    public Boolean getRight() {
        return Right;
    }

    public void setRight(Boolean right) {
        Right = right;
    }

    public Boolean getLeft() {
        return Left;
    }

    public void setLeft(Boolean left) {
        Left = left;
    }

    public Boolean getUp() {
        return Up;
    }

    public void setUp(Boolean up) {
        Up = up;
    }

    public Boolean getDown() {
        return Down;
    }

    public void setDown(Boolean down) {
        Down = down;
    }
}