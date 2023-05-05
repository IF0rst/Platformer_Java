package Instances;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.security.Key;
import java.awt.event.KeyEvent;

public class Player extends Instance {
    public Player(String selected_Name, int selected_X, int selected_Y, int selected_Width, int selected_Height, String defaultSprite) {
        super(selected_Name, selected_X, selected_Y, selected_Width, selected_Height, defaultSprite,true);
        super.GetPhysicProperties().JumpRequestHeight = 17;
    }
    @Override
    public void doAction(Boolean Left,Boolean Up,Boolean Right) {
        PhysicsVariables physicsVar = super.GetPhysicProperties();
        physicsVar.JumpRequest = false;
        if (Left){
            super.SetSprite(new ImageIcon("Images/PlayerLeft.png"));
            physicsVar.XVel += -1;
            if (physicsVar.XVel < -10){
                physicsVar.XVel = -10;
            }
        }else if (Right){
            super.SetSprite(new ImageIcon("Images/PlayerRight.png"));
            physicsVar.XVel += 1;
            if (physicsVar.XVel > 10){
                physicsVar.XVel = 10;
            }

        }else{
            if (physicsVar.XVel > 0){
                physicsVar.XVel += -1;
            }else if (physicsVar.XVel < 0){
                physicsVar.XVel += 1;
            }
        }
        if (Up){
            if (physicsVar.CanJump) {
                physicsVar.JumpRequest = true;
            }
        }
    }
}
