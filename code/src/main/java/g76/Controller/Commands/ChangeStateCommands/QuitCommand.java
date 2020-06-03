package g76.Controller.Commands.ChangeStateCommands;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Commands.Command;
import g76.Controller.Game;
import g76.Controller.States.GameState;
import g76.Controller.States.QuitState;


public class QuitCommand implements Command {

    @Override
    public void execute(Game game, TextGraphics graphics){
        GameState nextState = new QuitState();
        game.setCurrentState(nextState);
        game.gameChanged();
    }

}
