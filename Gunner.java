import java.awt.*;
import javax.swing.*;

import java.awt.image.*;

import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;

public class Gunner extends GameSprite
{

    BodyDef bodyDef;
    PolygonShape shape;
    FixtureDef fixtureDef;  
    
    Body gunnerBody;

    public void fire()
    {
    }
    
    @Override
    public void physicsOn()
    {
        //To see if the Overrie works
        System.out.println("gunner physics on");
        
        //Test the existance of the world
        if(gameWorld == null)
            return;
        System.out.println("We know a reference to the JBox2D World");
        
        //add the gunner representation to the dynamics world
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(
               ((posX - winW/2)/scaleFactor) 
                ,((posY - winH/2)) /scaleFactor
                );
        
        gunnerBody = gameWorld.createBody(bodyDef);
        
        BufferedImage baseSprite = (BufferedImage) getFrame();
        
        System.out.println("The Gunner base sprite size used is " + baseSprite.getWidth() + "x" + baseSprite.getHeight());

        shape = new PolygonShape();
        shape.setAsBox(baseSprite.getWidth()/scaleFactor,
                baseSprite.getHeight()/scaleFactor);

        //create and attach fixture
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        gunnerBody.createFixture(fixtureDef);
        physicsOk = true;

    }

    @Override
    public void physicsRun()
    {
        if(!physicsOk) return;
        //System.out.println("Update gunner position");

        Vec2 phyPosition = gunnerBody.getPosition();

        int drawX, drawY;

        //convert coordinates to drawint coordinates;
        
        drawX = Math.round(phyPosition.x * scaleFactor) + (winW/2);
        drawY = Math.round(phyPosition.y * scaleFactor) + (winH/2);
        
        System.out.println("pos : " + drawX + "x" + drawY);

        posX = drawX;
        posY = drawY;

    }

}
