package g76.View.Gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g76.Controller.Commands.Command;
import g76.Controller.States.GameState;
import g76.Controller.States.PlayState;
import g76.Controller.States.StartState;

import java.io.IOException;

public class Gui {

    private final TerminalScreen screen;
    private final TextGraphics graphics;

    public Gui() throws IOException {
        TerminalSize terminalSize = new TerminalSize(50, 26);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();
        this.graphics = this.screen.newTextGraphics();

    }

    public void draw(GameState state) throws IOException, InterruptedException{
        state.draw(this.screen);
    }

    public Command getNextCommand(GameState state) throws InterruptedException, IOException{

        if(state instanceof PlayState || (state instanceof StartState))
            return state.getNextCommand(this.screen, screen.pollInput());

        return state.getNextCommand(this.screen, screen.readInput());
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public boolean isFinished(GameState state){
        return state.isFinished();
    }
}
