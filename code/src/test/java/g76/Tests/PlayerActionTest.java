package g76.Tests;

import g76.Controller.Commands.PlayerAction.*;
import g76.Model.Arena;
import g76.Model.Player;
import g76.Model.Position;
import g76.View.ArenaCreator;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class PlayerActionTest {

    private ArenaCreator ar = new ArenaCreator();
    private Arena arena = ar.createArena(50,25);

    //All tests from the controller
    @Test
    public void MoveLeft() throws IOException, InterruptedException {
        Position pos_t = new Position(arena.getPlayer().getPosition().getX()-2,arena.getPlayer().getPosition().getY());
        MoveLeftPlayer j = new MoveLeftPlayer();
        j.executeAction(arena);
        assertEquals(arena.getPlayer().getPosition(),pos_t);
        Player p = new Player(24, 25 , 2, 1);
        Arena arn = Mockito.spy(new Arena(p, 50,26));
        j.executeAction(arn);
        Mockito.verify(arn, Mockito.times(1)).step();
    }
    @Test
    public void MoveRight() throws IOException, InterruptedException {
        Position pos_t = new Position(arena.getPlayer().getPosition().getX()+2,arena.getPlayer().getPosition().getY());
        MoveRightPlayer j = new MoveRightPlayer();
        j.executeAction(arena);
        assertEquals(arena.getPlayer().getPosition(), pos_t);
        Player p = new Player(24, 25 , 2, 1);
        Arena arn = Mockito.spy(new Arena(p, 50,26));
        j.executeAction(arn);
        Mockito.verify(arn, Mockito.times(1)).step();
    }
    @Test
    public void PlayerJump() throws IOException, InterruptedException {
        Player p = new Player(23,24,2,1);
        Arena arena = new Arena(p, 50,25);

        Position pos_t = new Position(arena.getPlayer().getPosition().getX(),arena.getPlayer().getPosition().getY()-5);
        Action j = new JumpPlayer();
        j.executeAction(arena);
        assertEquals(arena.getPlayer().getPosition(), pos_t);

        Player pl = new Player(23,24,2,1);
        Arena arn = Mockito.spy(new Arena(pl, 50,26));
        j.executeAction(arn);
        Mockito.verify(arn, Mockito.times(5)).step();
    }


    @Test
    public void JumpLeft() throws IOException, InterruptedException {
        Position pos = new Position(20,24);
        arena.movePlayerTo(pos);

        Position pos_t = new Position(arena.getPlayer().getPosition().getX()-12,arena.getPlayer().getPosition().getY()-7);
        Action j = new JumpLeftPlayer();
        j.executeAction(arena);
        assertEquals(arena.getPlayer().getPosition(), pos_t);

        Player p = new Player(20,24,2,1);
        Arena arn = Mockito.spy(new Arena(p, 50,26));
        j.executeAction(arn);
        Mockito.verify(arn, Mockito.times(6)).step();
    }
    @Test
    public void JumpRight() throws IOException, InterruptedException {
        //testing when it actually can fully complete the movement

        Position pos = new Position(17,24);
        arena.movePlayerTo(pos);

        Position pos_t = new Position(arena.getPlayer().getPosition().getX()+12,arena.getPlayer().getPosition().getY()-7);
        Action j = new JumpRightPlayer();
        j.executeAction(arena);
        assertEquals(arena.getPlayer().getPosition(),pos_t);

        Player p = new Player(17,24,2,1);
        Arena arn = Mockito.spy(new Arena(p, 50,26));
        j.executeAction(arn);
        Mockito.verify(arn, Mockito.times(6)).step();
    }
    @Test
    public void CantJump() throws IOException, InterruptedException {
        //testing when it can't fully complete the jump

        Position pos_t = new Position(30,24 );
        arena.movePlayerTo(pos_t);

        Action j = new JumpPlayer();
        j.executeAction(arena);

        Position pos = new Position(30,21);
        assertEquals(arena.getPlayer().getPosition(),pos);
    }
    @Test
    public void CantJumpLeft() throws IOException, InterruptedException {
        //testing when it cant because of the x
        Arena arena_1 = ar.createArena(50,25);
        Position p_1 = new Position(4,24 );
        arena_1.movePlayerTo(p_1);

        Position pos_t1 = new Position(p_1.getX()-4,p_1.getY()-4);
        Action j1 = new JumpLeftPlayer();
        j1.executeAction(arena_1);
        assertEquals(arena_1.getPlayer().getPosition(),pos_t1);

        //testing when it cant because of the y
        ArenaCreator ar2 = new ArenaCreator();
        Arena arena_2 = ar2.createArena(50,25);
        Position p_2 = new Position(38,24 );
        arena_2.movePlayerTo(p_2);

        Position pos_t2 = new Position(arena_2.getPlayer().getPosition().getX()-4, arena_2.getPlayer().getPosition().getY()-3);
        Action j2 = new JumpLeftPlayer();
        j2.executeAction(arena_2);
        assertEquals(arena_2.getPlayer().getPosition(),pos_t2);
    }

    @Test
    public void CantJumpRight() throws IOException, InterruptedException {
        //testing when it cant because of the x
        Arena arena_1 = ar.createArena(50,25);
        Position p_1 = new Position(42,24 );
        arena_1.movePlayerTo(p_1);

        Position pos_t1 = new Position(p_1.getX()+6,p_1.getY()-4);
        Action j1 = new JumpRightPlayer();
        j1.executeAction(arena_1);
        assertEquals(arena_1.getPlayer().getPosition(),pos_t1);

        //testing when it cant because of the y
        ArenaCreator ar2 = new ArenaCreator();
        Arena arena_2 = ar2.createArena(50,25);
        Position p_2 = new Position(22,24 );
        arena_2.movePlayerTo(p_2);

        Position pos_t2 = new Position(arena_2.getPlayer().getPosition().getX()+4, arena_2.getPlayer().getPosition().getY()-3);
        Action j2 = new JumpRightPlayer();
        j2.executeAction(arena_2);
        assertEquals(arena_2.getPlayer().getPosition(),pos_t2);
    }
}
