package g76.Controller.States;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.Command;
import g76.Controller.Commands.DoNothingCommand;

import java.io.IOException;

public class QuitState extends GameState {

    public void draw(TerminalScreen screen) throws IOException { screen.close(); }

    public Command getNextCommand(TerminalScreen screen, KeyStroke input) {
        return new DoNothingCommand();
    }

    @Override
    public boolean isFinished(){ return true; }
}
