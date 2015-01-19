import java.awt.*;
import javax.swing.*;

import java.awt.image.*;

public class Gunner extends GameSprite
{

    int hitboxX = 0;
    int hitboxY = 0;
    
    int hitboxPX = 0;
    int hitboxPY = 0;
    

    public void fire()
    {

    }

    public void left()
    {
        flipped = true;
    }

    public void right()
    {
        flipped = false;
    }
    @Override
    public void physicsOn()
    {
        BufferedImage baseSprite = (BufferedImage)getFrame();
        hitboxX = baseSprite.getWidth();
        hitboxY = baseSprite.getHeight();

        System.out.println("Gunner hitbox dim: "+ hitboxX +"x"+hitboxY);
    }
}
