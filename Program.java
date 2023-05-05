import Game.*;
import Instances.*;

import javax.swing.*;

public class Program {
    public static void main(String[] args){
        Screen GameInstance = new Screen();

        Instance Player = new Player("Player1",0,0,40,40, "Images/PlayerRight.png");
        Instance Enemy = new MovingEnemy("Enemy",380,70,40,40,"Images/EnemyA1.png");

        GameInstance.AddInstance(Player);
        GameInstance.AddInstance(Enemy);
        GameInstance.SetCameraSubject(Player);

        GameInstance.LoadMap("Levels/Data/Level1");

        while (true){
            GameInstance.Write();
            GameInstance.CameraX += 1;
           try{
               Thread.sleep(30);
           } catch (InterruptedException e){

           }
        }

    }
}
