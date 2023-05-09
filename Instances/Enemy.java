package Instances;

import javax.swing.*;

public class Enemy extends Instance {

    int Direction = -1;
    int TickPassed = 0;
    public Enemy(int X,int Y,int Height,int Width,String Sprite){
        super(X,Y,Height,Width,new ImageIcon(Sprite));
    }

    @Override
    public void doAction(Boolean Left, Boolean Up, Boolean Right) {

        Velocity InstanceVelocity = super.getVelocity();

        if (TickPassed == 10) {
            super.setSprite(new ImageIcon("Images/EnemyA2.png"));
        }

        if (TickPassed == 20){
            TickPassed = 0;
            super.setSprite(new ImageIcon("Images/EnemyA1.png"));
        }

        TickPassed++;

        if (InstanceVelocity.getWallTouched()){
            if (Direction == -1){
                Direction = 1;
            }else{
                Direction = -1;
            }
        }

        InstanceVelocity.setXVel(5 * Direction);

        super.getInstanceInterface().CalculatePhysics(this);
    }
}
