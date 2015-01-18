import java.awt.*;
import javax.swing.*;
import java.util.*;

class PhysicsEngine
{
    private Gunner gunner = null;
    private ArrayList<SceneSprite> scene = null;

    
    float currentTime, lastTime, interFrameTime;

    public PhysicsEngine()
    {
        scene = new ArrayList<SceneSprite>();
    }
    
    public void inputLeft(){}
    public void inputRight(){}
    public void inputJump(){}
    public void inputFire(){}

    public void setGunner(Gunner g)
    {
        gunner = g;
    }

    public void addSceneElement(SceneSprite ss)
    {
        scene.add(ss);
    }

    public void simulationTime(float time)
    {
        lastTime = currentTime;
        currentTime = time;
        interFrameTime = currentTime - lastTime;
    ///    System.out.println("simulationTime " + interFrameTime);
    }


    private int distance(int speed, float time)
    {
        return Math.round(speed*time);
    }

    public boolean playerWillColide(SceneSprite ss, int x, int y)
    {
        return false;
    }
    
    int playerFreeFallSpeed = 300;

    public void step()
    {
  //      System.out.println("step");
        boolean freefall = true;
        if(freefall)
        {
            int displacement = distance(playerFreeFallSpeed, interFrameTime);
            gunner.posY += distance(playerFreeFallSpeed, interFrameTime);
        }
        else
        {
            
        }
    }

}
