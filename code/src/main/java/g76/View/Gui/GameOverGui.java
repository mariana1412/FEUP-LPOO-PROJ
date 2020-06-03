package g76.View.Gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.MenuCommand;
import g76.Controller.Commands.ChangeStateCommands.QuitCommand;
import g76.Controller.Commands.Command;
import g76.Controller.Commands.DoNothingCommand;
import g76.View.DrawFunctions.DrawGameOver;

import java.io.IOException;

public class GameOverGui {

    private int score;
    private final DrawGameOver drawGameOver;

    public GameOverGui(TextGraphics graphics) {
        this.drawGameOver = new DrawGameOver(graphics);
    }

    public void draw(TerminalScreen screen) throws IOException {
        screen.clear();
        this.drawGameOver.setScore(score);
        this.drawGameOver.draw();
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
    public DrawGameOver getDrawGameOver(){return drawGameOver;}
}
