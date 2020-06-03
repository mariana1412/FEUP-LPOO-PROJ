package g76.Controller.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.Command;
import g76.View.Gui.InstructionsGui;

import java.io.IOException;

public class InstructionsState extends GameState {

    InstructionsGui gui;

    public InstructionsState(TextGraphics graphics) {
        this.gui = new InstructionsGui(graphics);
    }

    public void draw(TerminalScreen screen) throws IOException {
        gui.draw(screen);
    }

    public Command getNextCommand(TerminalScreen screen, KeyStroke input) {
        return gui.getNextCommand(input);
    }

    public InstructionsGui getGui() {
        return gui;
    }


}