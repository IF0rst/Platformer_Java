package Instances;

import java.awt.*;
import java.awt.event.KeyListener;
import java.security.Key;
import java.awt.event.KeyEvent;

public class Player extends Instance {
    public Player(String selected_Name, int selected_X, int selected_Y, int selected_Width, int selected_Height, String defaultSprite) {
        super(selected_Name, selected_X, selected_Y, selected_Width, selected_Height, defaultSprite,true);
    }
    @Override
    public void doAction(Boolean Left,Boolean Up,Boolean Right) {
        PhysicsVariables physicsVar = super.GetPhysicProperties();
        if (Left){
            physicsVar.XVel += -1;
            if (physicsVar.XVel < -20){
                physicsVar.XVel = -20;
            }
        }else if (Right){
            physicsVar.XVel += 1;
            if (physicsVar.XVel > 20){
                physicsVar.XVel = 20;
            }

        }else{
            if (physicsVar.XVel > 0){
                physicsVar.XVel += -1;
            }else if (physicsVar.XVel < 0){
                physicsVar.XVel += 1;
            }
        }
        if (Up){
            physicsVar.YVel = -15;
        }
    }
}
