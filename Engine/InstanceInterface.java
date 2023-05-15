package Engine;

import Instances.Instance;

public interface InstanceInterface {
    public void CalculatePhysics(Instance Selected);
    public Instance IsTouching(Instance Selected);
    public GameData getGameData();
}
