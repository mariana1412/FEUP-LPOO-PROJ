package g76.Controller.Commands.PlayerAction;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Game;
import g76.Controller.States.PlayState;
import g76.Model.Arena;
import g76.Model.Position;

import java.io.IOException;

public class MoveRightPlayer implements Action {

    public void executeAction(Arena arena) throws IOException, InterruptedException {
        Position position = new Position(arena.getPlayer().getPosition().getX(), arena.getPlayer().getPosition().getY());
        position = position.addToX(1);

        for (int i = 0; i < 2; i++) {
            if (arena.movePlayerTo(position)) {
                position = position.addToX(1);
            }
            else break;
        }
        arena.step();
    }

    @Override
    public void execute(Game game, TextGraphics graphics) throws IOException, InterruptedException {
        executeAction(((PlayState)game.getCurrentState()).getArena());
    }
}