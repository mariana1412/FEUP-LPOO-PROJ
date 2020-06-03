package g76.Controller.States;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.MenuCommand;
import g76.Controller.Commands.Command;


public class StartState extends GameState {

    public void draw(TerminalScreen screen) {}

    public Command getNextCommand(TerminalScreen screen, KeyStroke input) {
        return new MenuCommand();
    }

}
