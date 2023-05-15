package Instances;

import javax.swing.*;

public class WinPortal extends Instance{

    public WinPortal(int X, int Y) {
        super(X, Y, 40, 40, new ImageIcon("Images/win.png"));
    }

    @Override
    public void doAction(Boolean Left, Boolean Up, Boolean Right) {

    }

    @Override
    public Role getRole() {
        return Role.WinPoint;
    }
}
