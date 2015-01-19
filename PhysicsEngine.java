import java.awt.*;
import javax.swing.*;
import java.util.*;

class PhysicsEngine
{
    private Gunner gunner = null;
    private ArrayList<SceneSprite> scene = null;
    private ArrayList<Bullet> bullets = null;   
    float currentTime, lastTime, interFrameTime;
    float jumpTime, fireTime;
    private GameEngine gameEngine = null;
    public PhysicsEngine()
    {
        scene = new ArrayList<SceneSprite>();
        bullets = new ArrayList<Bullet>();
    }
    
    public void setGameEngine(GameEngine ge)
    {
        gameEngine = ge;    
    }

    boolean left = false, right = false, jump = false, fire = false;

    //no timed action
    public void inputLeft(boolean b){left = b;gunner.setAnimated(b); }
    public void inputRight(boolean b){right = b;gunner.setAnimated(b);}
    
    //timed action
    public void inputJump(boolean b){jump = b; jumpTime = currentTime;}
    public void inputFire(boolean b ){fire = b; fireTime = currentTime;}


    public void setGunner(Gunner g)
    {
        gunner = g;
        gunner.setAnimated(false);
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
//        System.out.println("testing...");
        int posX = gunner.posX;
        int posY = gunner.posY;
        int hitX = gunner.hitboxX;
        int hitY = gunner.hitboxY;
        
        if(posX + x + hitX >= ss.posX && posX + x <= ss.posX + ss.hitboxX)
        {
  //          System.out.println("X boundaries");
            if(posY + y + hitY  >= ss.posY && posY + y <= ss.posY + ss.hitboxY)
            {

    //            System.out.println("Y boundaries");
                return true;
            }
        }


        return false;
    }
    
    int playerFreeFallSpeed = 666;
    int jumpSpeed = 666;
    int runSpeed = 333;
    int fireRate = 500;
    public void step()
    {
        for(int i = 0; i < bullets.size(); i++)
        {
            System.out.println("Bullet #"+i);
            bullets.get(i).posX += 700*interFrameTime;
            System.out.println(bullets.get(i).posX);
        }

  //      System.out.println("step");
        boolean freefall = true;
        if(freefall)
        {
            int displacement = distance(playerFreeFallSpeed, interFrameTime);
            boolean canFall = true;
            for(SceneSprite s : scene)
            {
                if(playerWillColide(s, 0, displacement))
                {   
     //               System.out.println("No displacemnt");
                    canFall = false;break;
                }
            }

            if(canFall)
                gunner.posY += distance(playerFreeFallSpeed, interFrameTime);
        }
        else
        {   
        }

        if(left)
        {
            boolean canRun = true;
            int displacement =-1* distance(runSpeed, interFrameTime);
            for(SceneSprite s: scene)
            {
                if(playerWillColide(s, displacement, 0))
                        {
                            canRun = false;
                            break;
                        }
            }

            if(canRun)
            {
                gunner.setAnimated(true);
                gunner.posX += displacement;
            }
        }
        else if(right)
        {
            boolean canRun = true;
            int displacement = distance(runSpeed, interFrameTime);
            for(SceneSprite s: scene)
            {
                if(playerWillColide(s, displacement, 0))
                        {
                            canRun = false;
                            break;
                        }
            }

            if(canRun)
            {
                gunner.setAnimated(true);
                gunner.posX += displacement;
            }

        }

        if(fire)
        {

            boolean canFire = false;
            if(currentTime - fireTime < 0.3) canFire = true;
            if(canFire)
            {

                System.out.println(""+fire +" "+ currentTime);
                doFire();
                fireTime -= 0.4;
            }

        }

        if(jump)
        {
            boolean canJump = false;
            for(SceneSprite s: scene)
            {
                if(playerWillColide(s, 0, +15))
                        {
                            canJump = true;
                            break;
                        }
            }
            //System.out.println(currentTime - jumpTime);
            if(currentTime - jumpTime < 0.3) canJump = true;
            else return;

            
            int displacement = -distance(jumpSpeed+playerFreeFallSpeed, interFrameTime);
            for(SceneSprite s: scene)
            {
                if(playerWillColide(s, displacement, 0))
                        {
                            canJump = false;
                            break;
                        }
            }

            if(canJump)
            {
                gunner.posY += displacement;
            }
        }


    }

    private void doFire()
    {
        if(gameEngine == null) return;
        Bullet b = new Bullet(gunner.posX+gunner.hitboxX, gunner.posY+60);
        gameEngine.addSprite(b); 
        bullets.add(b);
    }

}
