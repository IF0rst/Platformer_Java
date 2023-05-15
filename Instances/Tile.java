package Instances;

import javax.swing.*;

public class Tile {
    private int X;
    private int Y;
    private JLabel JLabel;
    private Boolean Collision = true;
    private Icon Sprite = new ImageIcon("Images/Tiles/0.png");

    public Tile(int x, int y, String Sprite) {
        this.X = x;
        this.Y = y;
        this.Sprite = new ImageIcon(Sprite);
    }
    public void setCollision(Boolean State){
        this.Collision = false;
    }

    public Boolean getCollision(){
        return this.Collision;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public Icon getSprite() {
        return this.Sprite;
    }

    public void setSprite(Icon model) {
        this.Sprite = model;
    }

    public JLabel getJLabel() {
        return this.JLabel;
    }

    public void setJLabel(JLabel element) {
        this.JLabel = element;
    }
}