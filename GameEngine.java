import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.KeyListener;
public class GameEngine extends JPanel
{

    private PhysicsEngine pEngine = null;

    private JFrame frame;
    
    private int w, h;
    
    private ArrayList<GameSprite> renderQueue;
    
    long start;

    GameInputListener listener = new GameInputListener();
    public GameEngine(String windowName)
    {
        start = System.currentTimeMillis();
        System.out.println("Custom java GameEngine constructed!");
        w = 1024;
        h = 768;
        renderQueue = new ArrayList<GameSprite>();
        this.createWindow();
        addKeyListener(listener);
            setFocusable(true);
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
        gs.setGeometryInformation(w,h);
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
        pEngine.simulationTime(milliToSec(System.currentTimeMillis() - start));
        pEngine.step();
    }

    public void refresh() 
    {    
        stepPhysics();
        repaint();
        //wait some time
        try {
            Thread.sleep(5);    
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void setPEngine(PhysicsEngine e)
    {
        e.setGameEngine(this);
        pEngine = e;
        listener.setPEngine(e);
    }

}

