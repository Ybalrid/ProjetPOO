import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GameEngine extends JPanel
{
    int pixelToMetterFactor = 100;
    World world;
    Vec2 gravity = new Vec2(0.0f, -9.81f);
    private JFrame frame;
    
    private int w, h;
    
    private ArrayList<GameSprite> renderQueue;
    
    long start;

    public GameEngine(String windowName)
    {
        start = System.currentTimeMillis();
        System.out.println("Custom java GameEngine constructed!");
        w = 1024;
        h = 768;
        renderQueue = new ArrayList<GameSprite>();
        this.createWindow();

        world = new World(gravity);
    }

    public float milliToSec(long milli)
    {
        return (float)milli/1000.0f;
    }

    public long secToMilli(long sec)
    {
        return sec*1000;
    }

    private void createWindow()
    {
        frame = new JFrame("GameEngine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(w,h));

        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void addSprite(GameSprite gs)
    {
        renderQueue.add(gs);
        gs.setWorld(world);
        gs.setGeometryInformation(pixelToMetterFactor,w,h);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        Graphics2D g2 = (Graphics2D) g; //cast to Graphics2D to acces 2D specific stuff...
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //draw with antialiasing

        
        //blank the window with green
        g2.setColor(Color.GREEN);
        g2.fillRect(0,0,w,h);

        int nb = 0;
        for(GameSprite gs : renderQueue)
        {
            gs.update(milliToSec(System.currentTimeMillis() - start));
            gs.physicsRun();
            g2.drawImage(gs.getFrame(), gs.posX, gs.posY, null);
        }
    }

    public void stepPhysics()
    {

    }

    public void refresh() 
   {    
        stepPhysics();
        repaint();
    }
}

