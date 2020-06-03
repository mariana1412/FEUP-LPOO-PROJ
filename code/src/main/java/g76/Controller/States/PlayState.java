package g76.Controller.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.Command;
import g76.Model.Arena;
import g76.Model.GameLogic;
import g76.Model.Observer;
import g76.View.ArenaCreator;
import g76.View.Gui.PlayGui;

import java.io.IOException;

public class PlayState extends GameState{

    PlayGui gui;
    Arena arena;
    GameLogic gameLogic;

    public PlayState(TextGraphics graphics) {
        this.arena = new ArenaCreator().createArena(50, 25);
        this.gameLogic = new GameLogic();
        this.gui = new PlayGui(graphics, this.arena, this.gameLogic);
    }

    @Override
    public void draw(TerminalScreen screen) throws IOException {
        gui.draw(screen);
    }

    @Override
    public Command getNextCommand(TerminalScreen screen, KeyStroke input) throws IOException, InterruptedException {
        return gui.getNextCommand(screen, input);
    }

    public PlayGui getGui() {
        return gui;
    }

    public Arena getArena() {
        return arena;
    }


    /*--------------------Updating Interface-------------------------*/

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
        this.arena.addObserver(this);
    }
}
