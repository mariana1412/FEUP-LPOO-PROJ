package g76.Controller.Commands.PlayerAction;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Game;
import g76.Controller.States.PlayState;
import g76.Model.Arena;
import g76.Model.Position;

import java.io.IOException;

public class JumpRightPlayer implements Action {

    public void executeAction(Arena arena) throws IOException, InterruptedException {
        if (arena.getPlayer().getCanJump()) {
            Position position = new Position(arena.getPlayer().getPosition().getX(), arena.getPlayer().getPosition().getY());
            position = position.addToY(-1);

            for (int i = 0; i < 3; i++) {
                if (arena.movePlayerTo(position)) {
                    arena.step();

                    position = position.addToY(-1);

                    if (!arena.movePlayerTo(position))
                        return;

                    for (int j = 0; j < 4; j++) {
                        position = position.addToX(1);

                        if (!arena.movePlayerTo(position))
                            return;
                    }
                    arena.step();
                    position = position.addToY(-1);

                    if (!arena.movePlayerTo(position))
                        return;
                }
                else break;
            }
        }
    }

    @Override
    public void execute(Game game, TextGraphics graphics) throws IOException, InterruptedException {
        executeAction(((PlayState)game.getCurrentState()).getArena());
    }
}
