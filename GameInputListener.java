import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.*;
import javax.swing.*;
import java.util.*;
public class GameInputListener implements KeyListener
{
 
    //X11 key repeat workaround:
    float time = 0;
    float lastTime = 0;
    float threshold = 100;
    boolean canRelease = true;

    PhysicsEngine pEngine = null;
    @Override
    public void keyTyped(KeyEvent e) 
    {
        lastTime = time;
        time = System.currentTimeMillis();
        if(time - lastTime < threshold) canRelease = false;
        canRelease = true;
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {



        if(pEngine==null)return;
                System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        //
        String key = KeyEvent.getKeyText(e.getKeyCode());

        if(key.equals("Right")||key.equals("→"))
            pEngine.inputRight(true);
        if(key.equals("Left")||key.equals("←"))
            pEngine.inputLeft(true);
        if(key.equals("Space")||key.equals("␣"))
            pEngine.inputJump(true);
        if(key.equals("F"))
            pEngine.inputFire(true);
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if(pEngine==null)return;
        if(!canRelease)return; 
        
        String key = KeyEvent.getKeyText(e.getKeyCode());
        if(key.equals("Right") || key.equals("→"))
            pEngine.inputRight(false);
        if(key.equals("Left")||key.equals("←"))
            pEngine.inputLeft(false);
        if(key.equals("Space") || key.equals("␣"))
            pEngine.inputJump(false);
        if(key.equals("F"))
            pEngine.inputFire(false);
    }


    public void setPEngine(PhysicsEngine pe)
    {
        pEngine = pe;
    }
}
