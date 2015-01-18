import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;

class Game
{
    public static void main(String[] args)
    {
        GameEngine engine;
        engine = new GameEngine("My Game!");
        
        SceneSprite platform = new SceneSprite();

        platform.addFrame("./data/PlatformTest.png");
        //platform.setAnimated(false);

        engine.addSprite(platform);
        
        platform.posY+= 768-120;
        platform.physicsOn();

        Gunner s = new Gunner();
        s.addFrame("./data/gunner/gunner0.png");
        s.addFrame("./data/gunner/gunner1.png");
        s.addFrame("./data/gunner/gunner2.png");
        s.addFrame("./data/gunner/gunner3.png");
        
        s.setFramerate(6);
        s.setAnimated(true);

        engine.addSprite(s);
        s.physicsOn();

        while(true)
            engine.refresh();
    }
}

