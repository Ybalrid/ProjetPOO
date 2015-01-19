import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
public class Bullet extends GameSprite
{
    int hitboxX = 0;
    int hitboxY = 0;
    
    
    public Bullet(int x, int y)
    {
        super();
        this.addFrame("./data/bullet.png");
        posX = x;
        posY = y;
    }

    
    @Override
    public void physicsOn()
    {
        System.out.println("Scene Sprite physics configuration :");
        BufferedImage baseSprite = (BufferedImage)getFrame();
        hitboxX = baseSprite.getWidth();
        hitboxY = baseSprite.getHeight();
        System.out.println("hitbox geometry: "+hitboxX+"x"+hitboxY);
        System.out.println("scene element position: "+posX+"x"+posY);
        System.out.println("--------------------------------------");

    }

}

