package g76.Controller.Commands.ChangeStateCommands;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Commands.Command;
import g76.Controller.Game;
import g76.Controller.States.GameState;
import g76.Controller.States.PlayState;
import g76.Controller.States.WinState;


public class WinCommand implements Command {

    @Override
    public void execute(Game game, TextGraphics graphics) {
        int score = ((PlayState) game.getCurrentState()).getArena().getPlayer().getScore();
        GameState nextState = new WinState(graphics);
        game.setCurrentState(nextState);
        ((WinState) game.getCurrentState()).setScore(score);
        game.gameChanged();
    }
}
