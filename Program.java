import Engine.*;
import Instances.Instance;
import Instances.Player;
import java.util.ArrayList;

public class Program {
    public static void main(String[] Args){
        GameInstance Engine = new GameInstance();
        GameData EngineData = Engine.getGameData();

        Instance Player1 = new Player(40,-150);
        Engine.AddInstance(Player1);
        EngineData.setPlayer(Player1);

        ArrayList<String> UsedLevels = new ArrayList<>();
        UsedLevels.add("Levels/Data/Level1");
        UsedLevels.add("Levels/Data/Level2");
        UsedLevels.add("Levels/Data/Level3");
        UsedLevels.add("Levels/Data/Level4");

        EngineData.setLevelOrder(UsedLevels);
        EngineData.loadNextLevel();

        while (true){
            if (EngineData.getNextLevelRequestState()){
                EngineData.nextLevelRequestExecuted();
                EngineData.loadNextLevel();
                System.out.print("Engine : Level changed.\n");
                Engine.PrintDebug();
            }else{
                Engine.Write();
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
