import Engine.*;
import Instances.Enemy;
import Instances.Instance;
import Instances.Player;
import Instances.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Program {
    public static void main(String[] Args){
        GameInstance Engine = new GameInstance();

        // Utilisation d'un thread.sleep car sinon un bug apparait et le setSize() du constructeur s'execute sur une instance ajoutée.. (oui c'est très étrange)
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Instance Player1 = new Player(40,-150,40,40,"Images/PlayerRight.png");
        Engine.AddInstance(Player1);
        Engine.getGameData().setPlayer(Player1);

        ArrayList<String> UsedLevels = new ArrayList<>();
        UsedLevels.add("Levels/Data/Level1");

        Engine.getGameData().setLevelOrder(UsedLevels);
        Engine.getGameData().LoadNextLevel();

        while (true){
            try {
                Thread.sleep(20);
                Engine.Write();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
