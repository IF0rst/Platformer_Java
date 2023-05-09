package Instances;

import javax.swing.*;

public class Player extends Instance{
    public Player(int X,int Y,int Height,int Width,String Sprite){
        super(X,Y,Height,Width,new ImageIcon(Sprite));
    }
    @Override
    public void doAction(Boolean Left, Boolean Up, Boolean Right) {

        Velocity InstanceVelocity = super.getVelocity();

        if(Right){
            InstanceVelocity.setXVel(InstanceVelocity.getXVel() + 1);
            this.setSprite(new ImageIcon("Images/PlayerRight.png"));
        }else if(Left){
            InstanceVelocity.setXVel(InstanceVelocity.getXVel() - 1);
            this.setSprite(new ImageIcon("Images/PlayerLeft.png"));
        }

        if (Up && InstanceVelocity.getJump()){
            InstanceVelocity.setYVel(-16);
        }

        super.getInstanceInterface().CalculatePhysics(this);
    }
}
