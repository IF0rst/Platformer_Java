package Instances;

import javax.swing.*;

public class Spike extends Instance {
    public Spike(int X, int Y) {
        super(X, Y, 40, 40, new ImageIcon("Images/Spike.png"));
    }

    @Override
    public void doAction(Boolean Left, Boolean Up, Boolean Right) {

    }

    @Override
    public Role getRole() {
        return Role.Enemy;
    }
}
