package Instances;

import javax.swing.*;

public abstract class Instance {
    private String Name;
    private int X = 0;
    private int Y = 0;
    private int Height = 50;
    private int Width = 50;
    private Boolean PhysicEnabled = false;
    private PhysicsVariables physicsVar;
    ImageIcon Sprite = new ImageIcon("Images/default.png");
    public Instance(String Selected_Name,int Selected_X, int Selected_Y, int Selected_Width, int Selected_Height,String DefaultSprite,Boolean PhysicEnabled){
        this.Name = Selected_Name;
        this.X = Selected_X;
        this.Y = Selected_Y;
        this.Width = Selected_Width;
        this.Height = Selected_Height;
        this.Sprite = new ImageIcon(DefaultSprite);
        this.physicsVar = new PhysicsVariables();
        this.PhysicEnabled = true;
    }
    public void SetX(int nX){
        this.X = nX;
    }

    public void SetY(int nY){
        this.Y = nY;
    }
    public int GetX(){
        return this.X;
    }

    public int GetY(){
        return this.Y;
    }

    public int GetHeight(){
        return this.Height;
    }

    public int GetWidth(){
        return this.Width;
    }

    public ImageIcon GetSprite(){
        return Sprite;
    }

    public Boolean GetPhysicEnabled(){
        return PhysicEnabled;
    }

    public void doAction(Boolean Left,Boolean Up,Boolean Right){};

    public PhysicsVariables GetPhysicProperties(){
        return this.physicsVar;
    }
}
