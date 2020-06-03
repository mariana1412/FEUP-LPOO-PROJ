package g76.View.Gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.MenuCommand;
import g76.Controller.Commands.ChangeStateCommands.QuitCommand;
import g76.Controller.Commands.Command;
import g76.Controller.Commands.DoNothingCommand;
import g76.View.DrawFunctions.DrawInstructions;

import java.io.IOException;

public class InstructionsGui{

    private DrawInstructions instructions;

    public InstructionsGui(TextGraphics graphics) {
        this.instructions = new DrawInstructions(graphics);
    }

    public void draw(TerminalScreen screen) throws IOException {
        screen.clear();
        instructions.draw();
        screen.refresh();
    }

    public Command getNextCommand(KeyStroke input){
        if (input.getKeyType() == KeyType.EOF) return new QuitCommand();
        if (input.getKeyType() == KeyType.Enter) return new MenuCommand();
        return new DoNothingCommand();
    }
}
