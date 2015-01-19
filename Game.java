public class Game
{

       public static GameEngine engine = new GameEngine("game");
       public static  PhysicsEngine physicsEngine = new PhysicsEngine();
    public static void addPlatform(int x, int y)
    {
     
        SceneSprite platform = new SceneSprite();

        platform.addFrame("./data/platform.png");
        //platform.setAnimated(false);

        engine.addSprite(platform);
        physicsEngine.addSceneElement(platform);        
        platform.posY = y ;
        platform.posX = x ;
        platform.physicsOn();
    }

    public static void addBadGuy(int x, int y)
    {
        BadGuy bg = new BadGuy();
        bg.posX =x;
        bg.posY = y;
        bg.addFrame("./data/badguy.png");
        engine.addSprite(bg);
        bg.physicsOn();
        physicsEngine.addBadGuy(bg);

    }

    public static void main(String[] args)
    {

        
        engine.setPEngine(physicsEngine);

        GameSprite background = new GameSprite();
        background.addFrame("./data/background.png");
        engine.addSprite(background);
        
        for(int i = 0; i < 5; i++)
        {
            addPlatform(i*133, 768-120);
        }


        addPlatform(200, 768-240);
        Gunner s = new Gunner();

        s.addFrame("./data/gunner/gunner0.png");
        physicsEngine.setGunner(s);
        s.addFrame("./data/gunner/gunner1.png");
        s.addFrame("./data/gunner/gunner2.png");
        s.addFrame("./data/gunner/gunner3.png");
        
        s.setFramerate(6);
//        s.setAnimated(true);
//
//
        addBadGuy(1024/2-20, 768/2);
        addBadGuy(1024/4, 768/2);
        
        addPlatform(400, 768-240);

        addPlatform(500, 768- 480);
        addPlatform(700, 768 - 480);
        
        addBadGuy(550, 150);
        addBadGuy(750, 150);

        engine.addSprite(s);
        s.physicsOn();

        while(true)
            engine.refresh();
    }
}

