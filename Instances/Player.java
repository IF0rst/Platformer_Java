package Instances;

import javax.swing.*;

public class Player extends Instance{
    public Player(int X,int Y){
        super(X,Y,35,35,new ImageIcon("Images/PlayerRight.png"));
    }

    private Boolean DeathAnimation = false;
    private int Acceleration = 20;
    private int PassedTick = 0;

    private void doDeath(){
        PassedTick++;

        if (PassedTick < 12){
            this.setSprite(new ImageIcon("Images/PlayerDead.png"));
            super.setY(super.getY()-Acceleration);
            Acceleration -= 2;
            if (Acceleration < 0){
                Acceleration = 0;
            }
        }else{
            super.setY(super.getY()+Acceleration);
            Acceleration += 2;
        }

        if (super.getY() > 1000){
            DeathAnimation = false;
            Acceleration = 20;
            PassedTick = 0;
            this.setSprite(new ImageIcon("Images/PlayerRight.png"));
        }
    }

    @Override
    public void doAction(Boolean Left, Boolean Up, Boolean Right) {

        if (DeathAnimation){
            doDeath();
            return;
        }

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

        Instance Toucher = super.getInstanceInterface().IsTouching(this);

        if (Toucher != null){
            switch (Toucher.getRole()) {
                case Enemy -> DeathAnimation = true;
                case WinPoint -> super.getInstanceInterface().getGameData().requestNextLevel();
            }
        }

        if (super.getY() > 0){
            super.getInstanceInterface().getGameData().respawnCharacter();
        }

        super.getInstanceInterface().CalculatePhysics(this);
    }

    @Override
    public Role getRole() {
        return null;
    }
}
