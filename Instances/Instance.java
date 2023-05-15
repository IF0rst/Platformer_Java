package Instances;

import Engine.InstanceInterface;

import javax.swing.*;

public abstract class Instance {
    private int X;
    private int Y;
    private int Height;
    private int Width;
    private JLabel JLabel;
    private Icon Sprite = new ImageIcon("Images/0.png");
    private InstanceInterface GameMethods;
    private Velocity InstanceVelocity = new Velocity();
    public Instance(int Sx, int Sy, int Sh, int Sw, ImageIcon Ss) {
        this.X = Sx;
        this.Y = Sy;
        this.Height = Sh;
        this.Width = Sw;
        this.Sprite = Ss;
    }
    public void updateInstanceInterface(InstanceInterface Selected){
        this.GameMethods = Selected;
    }
    public InstanceInterface getInstanceInterface(){
        return this.GameMethods;
    }
    public abstract void doAction(Boolean Left, Boolean Up, Boolean Right);

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }
    public void setY(int y) {
        Y = y;
    }

    public int getHeight() {
        return Height;
    }
    public void setHeight(int height) {
        Height = height;
    }
    public int getWidth() {
        return Width;
    }
    public void setWidth(int width) {
        Width = width;
    }
    public JLabel getJLabel() {
        return JLabel;
    }
    public void setJLabel(JLabel jLabel) {
        JLabel = jLabel;
    }
    public Icon getSprite() {
        return Sprite;
    }

    public void setSprite(Icon sprite) {
        Sprite = sprite;
    }
    public Velocity getVelocity(){
        return InstanceVelocity;
    }
    public abstract Role getRole();
}
