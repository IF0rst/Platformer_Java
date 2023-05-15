package Engine;

import Instances.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameInstance extends JFrame implements KeyListener,InstanceInterface,GameDataInterface {
    InputHolder inputs = new InputHolder();
    Camera camera = new Camera();
    ArrayList<Instance> instances = new ArrayList<>();
    ArrayList<Tile> tiles = new ArrayList<>();
    GameData gameData = new GameData();
    public GameInstance(){
        /* Set up Screen */
        super("Platformer");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(false);
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(158,195,255));
        /* ------------- */

        /* Set Up vars */
        this.gameData.setDataInterface(this);
    }

    public Camera getUsedCamera(){
        return camera;
    }

    public GameData getGameData(){
        return gameData;
    }

    public JLabel CreateJLabel(int X,int Y,int Width, int Height, Icon icon){
        JLabel InstanceOnScreen = new JLabel(icon);
        InstanceOnScreen.setBounds(X,Y,Width,Height);
        InstanceOnScreen.setVisible(true);
        add(InstanceOnScreen);

        return InstanceOnScreen;
    }

    public void AddInstance(Instance SelectedInstance){
        instances.add(SelectedInstance);
        SelectedInstance.updateInstanceInterface(this);

        JLabel InstanceOnScreen = CreateJLabel(SelectedInstance.getX(),SelectedInstance.getY(),SelectedInstance.getWidth(),SelectedInstance.getHeight(),SelectedInstance.getSprite());
        SelectedInstance.setJLabel(InstanceOnScreen);
        SelectedInstance.setSprite(InstanceOnScreen.getIcon());
    }

    public void AddTile(Tile SelectedTile){
        tiles.add(SelectedTile);

        JLabel InstanceOnScreen = CreateJLabel(SelectedTile.getX(),SelectedTile.getY(),40,40,SelectedTile.getSprite());
        SelectedTile.setJLabel(InstanceOnScreen);
        SelectedTile.setSprite(InstanceOnScreen.getIcon());
    }

    public void LoadMap(String File){
        try (BufferedReader br = new BufferedReader(new FileReader(File))) {
            String Line;
            String[] Splitted = {};
            while ((Line = br.readLine()) != null) {
                Splitted = Line.split("[,:]");
                if (Splitted.length >= 3) {
                    switch(Splitted[2]){
                        case "Enemy1":
                            Instance Enemy = new Enemy(40 * Integer.parseInt(Splitted[0]),40 * Integer.parseInt(Splitted[1]));
                            AddInstance(Enemy);
                            break;
                        case "spawn":
                            gameData.setRespawnX(40 * Integer.parseInt(Splitted[0]));
                            gameData.setRespawnY(40 * Integer.parseInt(Splitted[1]));
                            break;

                        case "win":
                            Instance WinPortal = new WinPortal(40 * Integer.parseInt(Splitted[0]),40 * Integer.parseInt(Splitted[1]));
                            AddInstance(WinPortal);
                            break;

                        case "spike":
                            Instance Spike = new Spike(40 * Integer.parseInt(Splitted[0]),40 * Integer.parseInt(Splitted[1]));
                            AddInstance(Spike);
                            break;
                        default:
                            Tile CurrentTile = new Tile(40 * Integer.parseInt(Splitted[0]),40 * Integer.parseInt(Splitted[1]), "Images/Tiles/" + Splitted[2] + ".png");
                            AddTile(CurrentTile);

                            if (Splitted.length == 4){
                                if (Splitted[3].equals("n")){
                                    CurrentTile.setCollision(false);
                                }
                            }
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        repaint();
    }

    public void ClearMap(){
        // Use of iterator because we're removing elemnts while iterating.
        Iterator<Tile> tileIterator = tiles.iterator();
        while (tileIterator.hasNext()) {
            Tile CTile = tileIterator.next();
            remove(CTile.getJLabel());
            CTile.setJLabel(null);
            tileIterator.remove();
        }
        Iterator<Instance> instanceIterator = instances.iterator();
        while (instanceIterator.hasNext()) {
            Instance CInstance = instanceIterator.next();
            if (CInstance != gameData.getPlayer()) {
                remove(CInstance.getJLabel());
                CInstance.setJLabel(null);
                instanceIterator.remove();
            }
        }
    }

    // Mainly for instances

    public Instance IsTouching(Instance Selected){

        Rectangle Bounds = Selected.getJLabel().getBounds();

        for (Instance Cinstance : instances){
            if (Cinstance != Selected){
                if (Bounds.intersects(Cinstance.getJLabel().getBounds())){
                    return Cinstance;
                }
            }
        }

        return null;
    };

    public void CalculatePhysics(Instance Selected){
        Velocity InstanceVelocity = Selected.getVelocity();
        InstanceVelocity.setJump(false);
        InstanceVelocity.setWallTouched(false);

        // Y Limiter
        InstanceVelocity.setYVel(InstanceVelocity.getYVel() + 1);
        if (InstanceVelocity.getYVel() > 20){
            InstanceVelocity.setYVel(20);
        }

        // X Limiter
        if (InstanceVelocity.getXVel() > 10){
            InstanceVelocity.setXVel(10);
        }else if (InstanceVelocity.getXVel() < -10){
            InstanceVelocity.setXVel(-10);
        }
        if (!inputs.getLeft() && !inputs.getRight() && InstanceVelocity.getXVel() != 0){
            if (InstanceVelocity.getXVel() > 0){
                InstanceVelocity.setXVel(InstanceVelocity.getXVel()-1);
            }else{
                InstanceVelocity.setXVel(InstanceVelocity.getXVel()+1);
            }
        }

        Rectangle Bounds = Selected.getJLabel().getBounds();

        int DirectionX = (int) Math.signum(InstanceVelocity.getXVel());
        int DirectionY = (int) Math.signum(InstanceVelocity.getYVel());

        for (int i = 0; i < Math.abs(InstanceVelocity.getYVel());i++){
            Bounds.y += DirectionY;

            for (Tile CTile : tiles){
                if (Bounds.intersects(CTile.getJLabel().getBounds()) && CTile.getCollision()){
                    InstanceVelocity.setYVel(i * DirectionY);

                    if (DirectionY == 1){
                        InstanceVelocity.setJump(true);
                    }
                }
            }
        }

        Bounds = Selected.getJLabel().getBounds();

        for (int i = 0; i < Math.abs(InstanceVelocity.getXVel());i++){
            Bounds.x += DirectionX;

            for (Tile CTile : tiles){
                if (Bounds.intersects(CTile.getJLabel().getBounds()) && CTile.getCollision()){
                    InstanceVelocity.setXVel(i * DirectionX);
                    InstanceVelocity.setWallTouched(true);
                }
            }
        }

        Selected.setX(Selected.getX()+InstanceVelocity.getXVel() - DirectionX);
        Selected.setY(Selected.getY()+InstanceVelocity.getYVel() - DirectionY);
    }

    /*----------------------*/

    public void Write(){
        for (Instance CurrentInstance : instances){
            CurrentInstance.doAction(inputs.getLeft(),inputs.getUp(),inputs.getRight());

            CurrentInstance.getJLabel().setLocation(camera.getCameraX() + CurrentInstance.getX(),camera.getCameraY() +CurrentInstance.getY());

            if (CurrentInstance.getJLabel().getIcon() != CurrentInstance.getSprite()){
                CurrentInstance.getJLabel().setIcon(CurrentInstance.getSprite());
            }

            if (CurrentInstance.getJLabel().getWidth() != CurrentInstance.getWidth() || CurrentInstance.getJLabel().getHeight() != CurrentInstance.getHeight()){
                CurrentInstance.getJLabel().setSize(CurrentInstance.getWidth(),CurrentInstance.getHeight());
            }
        }
        for (Tile CurrentTile : tiles){
            CurrentTile.getJLabel().setLocation(camera.getCameraX() + CurrentTile.getX(),camera.getCameraY() + CurrentTile.getY());

            if (CurrentTile.getJLabel().getWidth() != 40 || CurrentTile.getJLabel().getHeight() != 40){
                CurrentTile.getJLabel().setSize(40,40);
            }
        }
        if (camera.getCameraSubject() != null){
            int PosX = camera.getCameraSubject().getX() * -1 + (getWidth()/2 - camera.getCameraSubject().getWidth());
            int PosY = camera.getCameraSubject().getY() * -1 + (getHeight()/2 - camera.getCameraSubject().getHeight());

            if (PosY < 17 * 40) {
                PosY = 17 * 40;
            }
            if (PosX > 0) {
                PosX = 0;
            }

            camera.setCameraY(PosY);
            camera.setCameraX(PosX);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Q:
                inputs.setLeft(true);
                break;
            case KeyEvent.VK_Z:
                inputs.setUp(true);
                break;
            case KeyEvent.VK_D:
                inputs.setRight(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Q:
                inputs.setLeft(false);
                break;
            case KeyEvent.VK_Z:
                inputs.setUp(false);
                break;
            case KeyEvent.VK_D:
                inputs.setRight(false);
                break;
        }
    }

    public void PrintDebug(){
        System.out.print("Number of Instances : "+instances.size()+"\n");
        System.out.print("Number of Tiles : "+tiles.size()+"\n");
    }
}
