import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;


public class Game
{
    Vec2 gravity = new Vec2(0.0f, -9.81f);
    World world = new World(gravity);
    
    //
    BodyDef bodyDef;
    PolygonShape poly;
    CircleShape circ;
    FixtureDef fixDef;
    //body
    Body floor, ball;
    

    public static void main(String[] args)
    {
        
    }
}

