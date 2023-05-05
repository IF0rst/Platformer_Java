package Game;

import javax.swing.*;
import Instances.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Vector;

public class Screen extends JFrame implements KeyListener {
    private boolean Left = false;
    private boolean Up = false;
    private boolean Right = false;

    public int CameraX;
    private int CameraY;
    private Instance CameraSubject;
    private ArrayList<Instance> Instances = new ArrayList<>();
    private ArrayList<Tile> Tiles = new ArrayList<>();
    public Screen(){
        /* Set up Screen */

        super("Platformer");
        setVisible(true);
        setSize(700,500);
        setResizable(false);
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(158,195,255));

        /* ------------- */

    }
    public void AddInstance(Instance SelectedInstance){
        Image Resized = SelectedInstance.GetSprite().getImage().getScaledInstance(SelectedInstance.GetWidth(),SelectedInstance.GetHeight(),Image.SCALE_AREA_AVERAGING);
        JLabel InstanceOnScreen = new JLabel(new ImageIcon(Resized));
        InstanceOnScreen.setVisible(true);
        InstanceOnScreen.setLocation(SelectedInstance.GetX(),SelectedInstance.GetY());
        InstanceOnScreen.setSize(new Dimension(SelectedInstance.GetWidth(),SelectedInstance.GetHeight()));

        add(InstanceOnScreen);

        SelectedInstance.SetJlabel(InstanceOnScreen);
        Instances.add(SelectedInstance);
    }

    public void SetCameraSubject(Instance Selected){
        CameraSubject = Selected;
    }

    public void AddTile(String[] Info){
        Tile NewTile = new Tile(Integer.parseInt(Info[0]),Integer.parseInt(Info[1]),Integer.parseInt(Info[2]));

        Image Resized = new ImageIcon("Levels/Tiles/"+NewTile.getModel()+".png").getImage().getScaledInstance(40,40,Image.SCALE_FAST);
        JLabel InstanceOnScreen = new JLabel(new ImageIcon(Resized));
        InstanceOnScreen.setVisible(true);
        InstanceOnScreen.setLocation(NewTile.getX() * 40,NewTile.getY() * 40);
        InstanceOnScreen.setSize(new Dimension(40,40));

        if (Info.length == 4){
            if (Info[3].equals("n")){
                NewTile.SetCollision(false);
            }
        }

        add(InstanceOnScreen);
        NewTile.setElement(InstanceOnScreen);
        Tiles.add(NewTile);
    }

    private void CalculatePhysic(Instance SelectedInstance){
        PhysicsVariables Physic = SelectedInstance.GetPhysicProperties();
        Physic.YVel += 1;

        Physic.TouchingWall = false;

        if (Physic.YVel > 20){
            Physic.YVel = 20;
        }

            if (Physic.YVel > 0) {
                for (int i = 0; i < Math.abs(Physic.YVel); i++) {
                    Rectangle Bounds = SelectedInstance.GetJlabel().getBounds();
                    Bounds.y += i;

                    for (Tile CTile : Tiles) {
                        if (CTile.GetCollision()) {
                            if (Bounds.intersects(CTile.getElement().getBounds())) {
                                Physic.YVel = i;
                                Physic.CanJump = true;
                            }
                        }
                    }
                }
                SelectedInstance.SetY( (SelectedInstance.GetY() + Physic.YVel)-1);
            } else if (Physic.YVel < 0) {
                for (int i = 0; i < Math.abs(Physic.YVel); i++) {
                    Rectangle Bounds = SelectedInstance.GetJlabel().getBounds();
                    Bounds.y += i * -1;

                    for (Tile CTile : Tiles) {
                        if (CTile.GetCollision()) {
                            if (Bounds.intersects(CTile.getElement().getBounds())) {
                                Physic.YVel = i * -1;
                            }
                        }
                    }
                }
                Physic.CanJump = false;
                SelectedInstance.SetY( (SelectedInstance.GetY() + Physic.YVel)+1);
            }

        if (Physic.XVel > 0) {
            for (int i = 0; i < Math.abs(Physic.XVel); i++) {
                Rectangle Bounds = SelectedInstance.GetJlabel().getBounds();
                Bounds.x += i;

                for (Tile CTile : Tiles) {
                    if (CTile.GetCollision()) {
                        if (Bounds.intersects(CTile.getElement().getBounds())) {
                            Physic.XVel = i;
                            Physic.TouchingWall = true;
                        }
                    }
                }
            }
            SelectedInstance.SetX( (SelectedInstance.GetX() + Physic.XVel)-1);
        } else if (Physic.XVel < 0) {
            for (int i = 0; i < Math.abs(Physic.XVel); i++) {
                Rectangle Bounds = SelectedInstance.GetJlabel().getBounds();
                Bounds.x += i * -1;

                for (Tile CTile : Tiles) {
                    if (CTile.GetCollision()) {
                        if (Bounds.intersects(CTile.getElement().getBounds())) {
                            Physic.XVel = i * -1;
                            Physic.TouchingWall = true;
                        }
                    }
                }
            }
            SelectedInstance.SetX( (SelectedInstance.GetX() + Physic.XVel)+1);
        }

        if (Physic.CanJump && Physic.JumpRequest){
            Physic.YVel -= Physic.JumpRequestHeight;
        }
    }

    public void LoadMap(String File){
        try (BufferedReader br = new BufferedReader(new FileReader(File))) {
            String Line;
            String[] Splitted = {};
            while ((Line = br.readLine()) != null) {
                Splitted = Line.split("[,:]");
                AddTile(Splitted);
            }
            repaint();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){

        }
    }

    public void Write(){
        for (Instance CurrentSelectedInstance : Instances){
            JLabel InstanceOnScreen = CurrentSelectedInstance.GetJlabel();

            InstanceOnScreen.setLocation(CameraX + CurrentSelectedInstance.GetX(),CameraY + CurrentSelectedInstance.GetY());
            InstanceOnScreen.setSize(new Dimension(CurrentSelectedInstance.GetWidth(),CurrentSelectedInstance.GetHeight()));
            InstanceOnScreen.setIcon(CurrentSelectedInstance.GetSprite());

            CurrentSelectedInstance.doAction(Left,Up,Right);

            if (CameraSubject != null){
                CameraX = CameraSubject.GetX() * -1 + (getWidth()/2 - CameraSubject.GetWidth());
                CameraY = CameraSubject.GetY() * -1 + (getHeight()/2 - CameraSubject.GetHeight());
            }

            if (CurrentSelectedInstance.GetPhysicEnabled()){
                CalculatePhysic(CurrentSelectedInstance);
            }
        }

        for (Tile CTile : Tiles){
            CTile.getElement().setLocation(CameraX + CTile.getX() * 40,CameraY + CTile.getY() * 40);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Q:
                Left = true;
                break;
            case KeyEvent.VK_Z:
                Up = true;
                break;
            case KeyEvent.VK_D:
                Right = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Q:
                Left = false;
                break;
            case KeyEvent.VK_Z:
                Up = false;
                break;
            case KeyEvent.VK_D:
                Right = false;
                break;
        }
    }
}
