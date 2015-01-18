import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class GameSprite
{
    int currentFrame;
    float fps;
    
    boolean animated;

    float currentTime;
    float lastTime;
    float timeSinceLastFrameChange;
    ArrayList<Image> frames;

    int posX, posY;
    
    //window dimention
    int winW, winH;
    
    boolean physicsOk = false;

    public void setGeometryInformation(int w, int h)
    {
        winW = w;
        winH = h;
    }

    public GameSprite()
    {
        animated = false;
        currentFrame = 0;
        currentTime = 0;
        lastTime = 0;
        timeSinceLastFrameChange = 0;

        fps = 0;

        frames = new ArrayList<Image>();

        posX = 0; posY = 0;
    }


    public void setAnimated(boolean anim)
    {
        animated = anim;   
    }

    public void setFramerate(float framerate)
    {
        fps = framerate;
    }
    
    public void addFrame(String imagePath)
    {
        try
        {
            BufferedImage image;
            image = ImageIO.read(new File(imagePath));
            frames.add(image);
        }
        catch(IOException e)
        {
            return;
        }
    }


    ///update (time) in seconds: only used if animated
    public void update(float time)
    {
        //System.out.println("New timecode : " +time);
        if(!animated) return;
        lastTime = currentTime;
        currentTime = time;


        timeSinceLastFrameChange += currentTime - lastTime;
        
        //System.out.println("Current frame is n°" + currentFrame);

        if(timeSinceLastFrameChange > 1/fps)
        {
            timeSinceLastFrameChange = 0;
            frameAdvance();
        }
    }

    private void frameAdvance()
    {
        currentFrame++;
        if(currentFrame > frames.size()-1)
            currentFrame = 0;
    }


    public Image getFrame()
    {
        if(!animated) return frames.get(0);
        return frames.get(currentFrame);
    }
    
    //method to call to set up 
    public void physicsOn()
    {
    }
    
    //method to call each frame
    public void physicsRun()
    {
    }

}
