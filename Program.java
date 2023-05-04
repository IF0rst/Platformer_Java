import Game.*;
import Instances.*;

import java.awt.*;

public class Program {
    public static void main(String[] args){
        Screen GameInstance = new Screen();

        Instance Player = new Player("Player1",0,0,50,50,"Images/default.png");
        Instance TestHitbox = new TerrainHitbox(50, 300, 520, 500, "Images/Levels/Block.png") {};

        GameInstance.AddInstance(Player);
        GameInstance.AddInstance(TestHitbox);

        while (true){
            GameInstance.Write();
           try{
               Thread.sleep(20);
           } catch (InterruptedException e){

           }
        }

    }
}
