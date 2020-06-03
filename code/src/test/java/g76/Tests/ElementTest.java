package g76.Tests;


import g76.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElementTest {
    //Testing the elements of the game


    @Test
    public void ElementGetHeight(){
        Element e = new Player(10,10,2,1);
        assertEquals(1,e.getHeight());
    }
    @Test
    public void ElementGetWidth(){
        Element e = new Player(10,10,2,1);
        assertEquals(2,e.getWidth());
    }

    @Test
    public void ConstructingPlayer(){
        Player p = new Player(10,10,2,1);
        Element e = new Player(10,10,2,1);
        Position ePos = new Position(10,10);

        assertEquals(ePos, e.getPosition());
        assertEquals(ePos, p.getPosition());
        assertEquals(p.getHealth(),3);
        assertEquals(p.getCanJump(),true);
        assertEquals(p.getHasJumped(),false);
        assertEquals(p.getScore(),0);

    }
    @Test
    public void ConstructingPlatform(){
        Platform p = new Platform(10,10,2,1);
        Element e = new Platform(10,10,2,1);
        Position pos = new Position(10,10);

        assertEquals(pos, e.getPosition());
        assertEquals(pos, p.getPosition());

    }
    @Test
    public void ConstructingCoin(){
        Coin c = new Coin(10,10,2,1);
        Element e = new Coin(10,10,2,1);
        Position pos = new Position(10,10);

        assertEquals(pos, e.getPosition());
        assertEquals(pos, c.getPosition());

    }
    @Test
        public void ConstructingKiller(){
        Enemy s = new Enemy(10,10,1,1);
        Element e = new Enemy(10,10,2,1);
        Position pos = new Position(10,10);

        assertEquals(pos,s.getPosition());
        assertEquals(pos,e.getPosition());
        assertEquals(s.isNotOnScreen(),true);
        assertEquals(s.getTouched(),false);
    }

    @Test
    public void resetPositionKiller(){
        Enemy s = new Enemy(10,10,1,1);
        Position pos = new Position(0, 10);
        s.resetPosition();
        assertEquals(s.getPosition(), pos);
        assertEquals(s.getTouched(), false);
    }

    @Test
    public void addScore(){
        Player p = new Player(22,24,2,1);
        p.addScore(9);
        p.addScore(8);
        assertEquals(p.getScore(),17);
    }
    @Test
    public void reduceHealth(){
        Player p = new Player(22,24,2,1);
        assertEquals(p.getHealth(),3);
        p.reduceHealth();
        assertEquals(p.getHealth(),2);
    }
    @Test
    public void moveElement(){
        Player p = new Player(22,24,2,1);
        Position pos = new Position(p.getPosition().getX(), p.getPosition().getY() + 1);
        p.moveElement();
        
        assertEquals(p.getPosition(),pos);
    }
    @Test
    public void moveKiller(){
        Enemy s = new Enemy(10,10,1,1);
        Position pos = new Position(s.getPosition().getX()+1, s.getPosition().getY());
        s.moveEnemy();

        assertEquals(s.getPosition(),pos);
    }
    @Test
    public void killerSetIsNotOnScreeen(){
        Enemy s = new Enemy(10,10,1,1);
        s.setIsNotOnScreen(true);
        assertTrue(s.isNotOnScreen());
        assertEquals(s.getPosition(), new Position(0,0));

    }



}
