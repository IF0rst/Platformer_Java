package Instances;

import javax.swing.*;

public class MovingEnemy extends Instance {
    private int TickCount = 0;
    public MovingEnemy(String selected_Name, int selected_X, int selected_Y, int selected_Width, int selected_Height, String defaultSprite){
        super(selected_Name, selected_X, selected_Y, selected_Width, selected_Height, defaultSprite,true);
        super.GetPhysicProperties().XVel = -4;
    }
    @Override
    public void doAction(Boolean Left, Boolean Up, Boolean Right) {
        PhysicsVariables physicsVar = super.GetPhysicProperties();
        TickCount++;

        if (physicsVar.TouchingWall){
            if (physicsVar.XVel < 0){
                physicsVar.XVel = 4;
            }else{
                physicsVar.XVel = -4;
            }
        }

        if (TickCount > 10 ){
            super.SetSprite(new ImageIcon("Images/EnemyA2.png"));
            if (TickCount > 20){
                TickCount = 0;
            }
        }else{
            super.SetSprite(new ImageIcon("Images/EnemyA1.png"));
        }
    }
}
