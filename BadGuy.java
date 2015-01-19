import java.awt.*;
import javax.swing.*;

import java.awt.image.*;

public class BadGuy extends GameSprite
{
    
    int life = 3;

    int hitboxX = 0;
    int hitboxY = 0;
    
    int hitboxPX = 0;
    int hitboxPY = 0;


    
    @Override
    public void physicsOn()
    {
        BufferedImage baseSprite = (BufferedImage)getFrame();
        hitboxX = baseSprite.getWidth();
        hitboxY = baseSprite.getHeight();

        System.out.println("Enemy hitbox dim: "+ hitboxX +"x"+hitboxY);
    }
}
