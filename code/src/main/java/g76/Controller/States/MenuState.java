package g76.Controller.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.Command;
import g76.Model.Menu;
import g76.Model.Observer;
import g76.View.Gui.MenuGui;

import java.io.IOException;

public class MenuState extends GameState {

    MenuGui gui;
    Menu menu;

    public MenuState(TextGraphics graphics) {
        this.menu = new Menu();
        this.gui = new MenuGui(graphics, menu);
    }


    public void draw(TerminalScreen screen) throws IOException {
        gui.draw(screen);
    }


    public Command getNextCommand(TerminalScreen screen, KeyStroke input){
        return gui.getNextCommand(input);
    }

    public MenuGui getGui() {
        return gui;
    }


    /*--------------------Updating Interface-------------------------*/

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
        this.menu.addObserver(this);
    }

}
