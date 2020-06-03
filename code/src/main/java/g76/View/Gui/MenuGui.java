package g76.View.Gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.InstructionsCommand;
import g76.Controller.Commands.ChangeStateCommands.PlayCommand;
import g76.Controller.Commands.ChangeStateCommands.QuitCommand;
import g76.Controller.Commands.Command;
import g76.Controller.Commands.DoNothingCommand;
import g76.Controller.Commands.NextOptionCommand;
import g76.Controller.Commands.PreviousOptionCommand;
import g76.Model.Menu;
import g76.View.DrawFunctions.DrawMenu;

import java.io.IOException;

public class MenuGui{

    private Menu menu;
    private DrawMenu drawMenu;
    boolean firstTime;

    public MenuGui(TextGraphics graphics, Menu menu) {
        this.drawMenu = new DrawMenu(graphics);
        this.menu = menu;
        this.firstTime = true;
    }

    public void draw(TerminalScreen screen) throws IOException {

        if (firstTime) {
            screen.clear();
            drawMenu.initialize();
            firstTime = false;
        }
        else drawMenu.draw(menu.optionToString());

        screen.refresh();
    }

    public Command getNextCommand(KeyStroke input){

        if (input.getKeyType() == KeyType.EOF) return new QuitCommand();
        if (input.getKeyType() == KeyType.ArrowUp) return new PreviousOptionCommand(menu);
        if (input.getKeyType() == KeyType.ArrowDown) return new NextOptionCommand(menu);

        if (input.getKeyType() == KeyType.Enter) {
            switch (menu.getOption()) {
                case 0: //button play
                    return new PlayCommand();
                case 1: //button instructions
                    return new InstructionsCommand();
                case 2: //button quit
                    return new QuitCommand();
                default:
                    break;
            }
        }

        return new DoNothingCommand();
    }
}
