package Engine;

import Instances.Instance;

import java.util.ArrayList;

public class GameData {
    private Instance player;
    private int respawnX;
    private int respawnY;
    private ArrayList<String> levelOrder;
    private int currentLevel = 0;
    private GameDataInterface dataInterface;

    public void RespawnCharacter(){
        if (player != null){
            player.setX(respawnX);
            player.setY(respawnY);
        }
    }
    public void LoadNextLevel(){
        if (currentLevel < levelOrder.size()) {
            dataInterface.ClearMap();
            dataInterface.LoadMap(levelOrder.get(currentLevel));
            currentLevel++;
            RespawnCharacter();
        }else{
            currentLevel = 0;
            LoadNextLevel();
        }
    }
    public void setDataInterface(GameDataInterface dataInterface) {
        this.dataInterface = dataInterface;
    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    public int getCurrentLevel() {
        return currentLevel;
    }

    public Instance getPlayer() {
        return player;
    }
    public void setPlayer(Instance player) {
        this.player = player;
        dataInterface.getUsedCamera().setCameraSubject(player);
    }

    public int getRespawnX() {
        return respawnX;
    }

    public void setRespawnX(int respawnX) {
        this.respawnX = respawnX;
    }

    public int getRespawnY() {
        return respawnY;
    }

    public void setRespawnY(int respawnY) {
        this.respawnY = respawnY;
    }

    public ArrayList<String> getLevelOrder() {
        return levelOrder;
    }

    public void setLevelOrder(ArrayList<String> levelOrder) {
        this.levelOrder = levelOrder;
    }
}