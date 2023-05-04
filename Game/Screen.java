package Game;

import javax.swing.*;
import Instances.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Currency;
import java.util.Vector;

public class Screen extends JFrame implements KeyListener {
    private boolean Left = false;
    private boolean Up = false;
    private boolean Right = false;
    private Vector<Instance> Instances = new Vector<Instance>();
    public Screen(){
        /* Set up Screen */

        super("Platformer");
        setVisible(true);
        setSize(700,500);
        setResizable(false);
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* ------------- */

    }
    public void AddInstance(Instance SelectedInstance){
        Instances.add(SelectedInstance);
    }

    private void CalculatePhysic(Instance SelectedInstance){

    }

    public void Write(){
        getContentPane().removeAll();
        setLayout(null);

        for (Instance CurrentSelectedInstance : Instances){
            Image Resized = CurrentSelectedInstance.GetSprite().getImage().getScaledInstance(CurrentSelectedInstance.GetWidth(),CurrentSelectedInstance.GetHeight(),Image.SCALE_FAST);

            JLabel InstanceOnScreen = new JLabel(new ImageIcon(Resized));
            InstanceOnScreen.setVisible(true);
            InstanceOnScreen.setLocation(CurrentSelectedInstance.GetX(),CurrentSelectedInstance.GetY());
            InstanceOnScreen.setSize(new Dimension(CurrentSelectedInstance.GetWidth(),CurrentSelectedInstance.GetHeight()));
            add(InstanceOnScreen);

            if (CurrentSelectedInstance.GetPhysicEnabled()){
                CalculatePhysic(CurrentSelectedInstance);
            }

            CurrentSelectedInstance.doAction(Left,Up,Right);
        }

        repaint();
        revalidate();
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
