package Engine;

import Instances.Instance;

public class Camera {
    private int CameraX = 0;
    private int CameraY = 0;
    private Instance Subject;
    public int getCameraX() {
        return CameraX;
    }
    public void setCameraX(int cameraX) {
        CameraX = cameraX;
    }
    public int getCameraY() {
        return CameraY;
    }
    public void setCameraY(int cameraY) {
        CameraY = cameraY;
    }

    public Instance getCameraSubject(){
        return this.Subject;
    }
    public void setCameraSubject(Instance Selected){
        this.Subject = Selected;
    }
}
