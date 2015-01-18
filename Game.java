
class Game
{
    public static void main(String[] args)
    {
        GameEngine engine;
        engine = new GameEngine("My Game!");
        PhysicsEngine physicsEngine = new PhysicsEngine();
        
        engine.setPEngine(physicsEngine);

        GameSprite background = new GameSprite();
        background.addFrame("./data/background.png");
        engine.addSprite(background);

        SceneSprite platform = new SceneSprite();

        platform.addFrame("./data/PlatformTest.png");
        //platform.setAnimated(false);

        engine.addSprite(platform);
        physicsEngine.addSceneElement(platform);        
        platform.posY+= 768-120;
        platform.physicsOn();

        Gunner s = new Gunner();

        s.addFrame("./data/gunner/gunner0.png");
        physicsEngine.setGunner(s);
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

