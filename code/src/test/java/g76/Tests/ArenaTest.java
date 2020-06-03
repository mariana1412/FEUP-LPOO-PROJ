package g76.Tests;


import g76.Model.*;
import g76.View.ArenaCreator;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ArenaTest {
    //Testing the Arena class

    @Test
    public void addElement(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50,25);
        Player p = new Player(20,20,2,1);
        arena.addElement(p);

        assertEquals(arena.getPlayer(),p);

        Platform plat = new Platform(15,15,8,1);
        int num = arena.getNumberPlatforms();
        arena.addElement(plat);
        assertEquals(arena.getNumberPlatforms(), num+1);
        assertEquals(arena.getPlatforms().get(arena.getPlatforms().size()-1), plat);

        Coin coin = new Coin (19,10,1,1);
        arena.addElement(coin);
        assertEquals(arena.getCoins().get(arena.getCoins().size()-1),coin);
    }

    @Test
    public void addObserver(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50,25);
        Observer observer = Mockito.mock(Observer.class);
        arena.addObserver(observer);

        assertEquals(arena.getObservers().get(arena.getObservers().size()-1), observer);
    }

    @Test
    public void addNewEnemy(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);
        arena.addNewEnemy();
        Enemy enemy = arena.getEnemy();
        assertEquals(enemy.isNotOnScreen(), true);
        assertEquals(enemy.getPosition(), new Position(0,0));

        arena.updateObjects();
        enemy = arena.getEnemy();
        assertEquals(enemy.isNotOnScreen(), false);

    }

    @Test
    public void removePlatform(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50,25);
        Platform plat = Mockito.mock(Platform.class);

        arena.addElement(plat);
        assertEquals(arena.getAllElements().get(arena.getNumberPlatforms()), plat);

        arena.removePlatform(plat);
        assertNotEquals(arena.getAllElements().get(arena.getNumberPlatforms()-1),plat);
    }
    @Test
    public void removeCoin(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50,25);
        Coin coin = Mockito.mock(Coin.class);

        arena.addElement(coin);
        assertEquals(arena.getCoins().get(arena.getCoins().size()-1), coin);

        arena.removeCoin(coin);
        assertNotEquals(arena.getCoins().get(arena.getCoins().size()-1),coin);

    }

    @Test
    public void removeKiller(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);
        arena.updateObjects();
        Enemy enemy = arena.getEnemy();
        arena.removeEnemy();
        assertEquals(enemy.isNotOnScreen(), true);
    }

    @Test
    public void catchedCoin(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50,25);

        int score = arena.getPlayer().getScore();
        Position secondPlatform = arena.getAllElements().get(2).getPosition();
        Position coinPos = new Position(secondPlatform.getX() + 4, secondPlatform.getY() - 1);
        arena.movePlayerTo(coinPos);

        assertEquals(arena.getCoins().isEmpty(), true);
        assertEquals(arena.getPlayer().getScore(), score+5);
    }

    @Test
    public void moveElementTo(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50,25);
        Coin testCoin = new Coin (15,16,1,1);

        arena.getCoins().get(arena.getCoins().size()-1).moveElement();
        Coin coin = arena.getCoins().get(arena.getCoins().size()-1);
        assertEquals(coin.getPosition().getX(), testCoin.getPosition().getX());
        assertEquals(coin.getPosition().getY(), testCoin.getPosition().getY());
    }


    @Test
    public void fallingExecute(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        assertEquals(arena.executeFall(), false);

        Position pos = arena.getPlayer().getPosition();
        arena.movePlayerTo(new Position(pos.getX(), pos.getY() - 1));
        arena.getPlayer().setCanJump(false);

        arena.executeFall();
        Position newPos = arena.getPlayer().getPosition();
        assertEquals(newPos, pos);
    }

    @Test
    public void checkCollisions(){

        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        arena.movePlayerTo(new Position(30, 19));

        assertEquals(arena.getPlayer().getHasJumped(), true);
        assertEquals(arena.getPlayer().getCanJump(), true);
    }

    @Test
    public void updateKiller(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        assertEquals(arena.updateEnemy(), false);
        arena.updateObjects();

        Position pos = arena.getEnemy().getPosition();
        arena.updateEnemy();
        Position newpos = arena.getEnemy().getPosition();
        pos = pos.addToX(1);
        assertEquals(newpos, pos);

        arena.getEnemy().setPosition(new Position(50, 20));
        arena.updateEnemy();
        newpos = arena.getEnemy().getPosition();
        assertEquals(newpos, new Position(0, 20));
    }

    @Test
    public void updateElements(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        int plat = arena.getNumberPlatforms();
        arena.updateObjects();
        arena.updateObjects();
        arena.updateObjects();
        arena.updateObjects();
        assertEquals(arena.getNumberPlatforms(), plat+1);

        int platSize = arena.getPlatforms().size();
        arena.updateObjects();
        int newPlatSize = arena.getPlatforms().size();

        assertEquals(newPlatSize, platSize - 1);
    }
    @Test
    public void lostPlayer(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        assertEquals(arena.playerLost(), false);

        arena.getPlayer().reduceHealth();
        arena.getPlayer().reduceHealth();
        arena.getPlayer().reduceHealth();
        assertEquals(arena.playerLost(), true);

        arena.getPlayer().setHasJumped();
        arena.movePlayerTo(new Position(arena.getPlayer().getPosition().getX(), 25));
        assertEquals(arena.playerLost(), true);
    }

    @Test
    public void winPlayer(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        assertFalse(arena.playerWin());
        arena.setNumberPlatforms(600);
        assertFalse(arena.playerWin());
        arena.updateObjects();
        arena.movePlayerTo(new Position(17,0));
        assertTrue(arena.playerWin());
    }

    @Test
    public void vulnerable(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        arena.movePlayerTo(new Position(31,19));
        assertEquals(arena.getEnemy().getTouched(), false);

        arena.movePlayerTo(arena.getEnemy().getPosition());//vulnerable is called and returns true
        Boolean vulnerable = arena.vulnerable(arena.getPlayer().getPosition());
        assertEquals(arena.getEnemy().getTouched(), true);
        assertEquals(arena.getPlayer().getHealth(),2);
        assertFalse(vulnerable);

    }
    @Test
    public void speed(){
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50, 25);

        arena.setNumberPlatforms(99);
        arena.updateObjects();
        arena.updateObjects();
        arena.updateObjects();
        arena.updateObjects();
        assertEquals(arena.getSpeed(),1000-150);

    }
}
