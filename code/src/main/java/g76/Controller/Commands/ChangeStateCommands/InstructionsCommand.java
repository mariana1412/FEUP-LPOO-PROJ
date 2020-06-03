package g76.Controller.Commands.ChangeStateCommands;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Commands.Command;
import g76.Controller.Game;
import g76.Controller.States.GameState;
import g76.Controller.States.InstructionsState;


public class InstructionsCommand implements Command {

    @Override
    public void execute(Game game, TextGraphics graphics)  {
        GameState nextState = new InstructionsState(graphics);
        game.setCurrentState(nextState);
        game.gameChanged();
    }

}
