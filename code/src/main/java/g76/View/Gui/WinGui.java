package g76.View.Gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.MenuCommand;
import g76.Controller.Commands.ChangeStateCommands.QuitCommand;
import g76.Controller.Commands.Command;
import g76.Controller.Commands.DoNothingCommand;
import g76.View.DrawFunctions.DrawWin;

import java.io.IOException;

public class WinGui {

    private int score;
    private final DrawWin drawWin;

    public WinGui(TextGraphics graphics) {
        this.drawWin = new DrawWin(graphics);
    }

    public void draw(TerminalScreen screen) throws IOException {
        screen.clear();
        this.drawWin.setScore(score);
        this.drawWin.draw();
        screen.refresh();
    }

    public Command getNextCommand(KeyStroke input){

        if (input.getKeyType() == KeyType.EOF) return new QuitCommand();
        if (input.getKeyType() == KeyType.Enter) return new MenuCommand();
        return new DoNothingCommand();
    }

    public void setScore(int score) {
        this.score = score;
    }
    public DrawWin getDrawWin(){ return drawWin;}
}
