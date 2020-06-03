package g76.Tests;


import g76.Model.Player;
import g76.Model.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    //Testing the class Position
    @Test
    public void addToX() {
        Position pos = new Position(10,10);
        pos=pos.addToX(3);

        assertEquals(13,pos.getX());
    }
    @Test
    public void addToY() {
        Position pos = new Position(10,10);
        pos= pos.addToY(3);

        assertEquals(13,pos.getY());
    }

    @Test
    public void equalOperator(){
        Position pos = new Position(10,10);
        Boolean a = pos.equals(new Position(5,10));
        Boolean b = pos.equals(pos);
        Boolean c = pos.equals(new Player(10,10,10,10));
        Boolean d = pos.equals(new Position(10,5));
        assertFalse(a);
        assertTrue(b);
        assertFalse(c);
        assertFalse(d);
    }
}
