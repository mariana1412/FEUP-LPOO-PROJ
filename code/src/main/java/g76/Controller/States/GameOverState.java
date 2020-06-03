package g76.Controller.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.Command;
import g76.View.Gui.GameOverGui;

import java.io.IOException;

public class GameOverState extends GameState {

    GameOverGui gui;

    public GameOverState(TextGraphics graphics) {
        this.gui = new GameOverGui(graphics);
    }


    public void draw(TerminalScreen screen) throws IOException {
        gui.draw(screen);
    }

    public Command getNextCommand(TerminalScreen screen, KeyStroke input) {
        return gui.getNextCommand(input);
    }

    public void setScore(int score){
        gui.setScore(score);
    }

    public GameOverGui getGui() {
        return gui;
    }

}
