package Instances;

import javax.swing.*;

public class Tile {
    private int X;
    private int Y;
    private int Model;
    private JLabel Element;

    private Boolean Collision = true;

    public Tile(int x, int y, int model) {
        this.X = x;
        this.Y = y;
        this.Model = model;
    }

    public void SetCollision(Boolean State){
        this.Collision = false;
    }

    public Boolean GetCollision(){
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

    public int getModel() {
        return Model;
    }

    public void setModel(int model) {
        this.Model = model;
    }

    public JLabel getElement() {
        return Element;
    }

    public void setElement(JLabel element) {
        this.Element = element;
    }
}