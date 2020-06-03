package g76.Tests;


import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Commands.ChangeStateCommands.QuitCommand;
import g76.Controller.Commands.NextOptionCommand;
import g76.Controller.Commands.PreviousOptionCommand;
import g76.Controller.Game;
import g76.Controller.States.QuitState;
import g76.Model.Menu;
import g76.View.Gui.Gui;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FunctionsTest {

    @Test
    public void OptionsCommand() throws IOException, InterruptedException {
        Menu menu = new Menu();
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Gui gui = Mockito.mock(Gui.class);
        Game game = new Game(gui);
        menu.setOption(2);
        NextOptionCommand command = new NextOptionCommand(menu);
        command.execute(game, graphics);
        assertEquals(menu.getOption(), 2);

        PreviousOptionCommand command2 = new PreviousOptionCommand(menu);
        menu.setOption(0);
        command2.execute(game, graphics);
        assertEquals(menu.getOption(), 0);
    }
    @Test
    public void optionToString() {
        Menu menu = new Menu();
        assertEquals(menu.optionToString(), "Play");

        menu.setOption(1);
        assertEquals(menu.optionToString(), "Instructions");

        menu.setOption(2);
        assertEquals(menu.optionToString(), "Quit");

        menu.setOption(4);
        assertEquals(menu.optionToString(), "");
    }

    @Test
    public void game() throws IOException, InterruptedException {
        Gui gui = Mockito.mock(Gui.class);
        Game game = new Game(gui);
        QuitState quit = new QuitState();
        game.setCurrentState(quit);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(gui.getNextCommand(game.getCurrentState())).thenReturn(new QuitCommand());
        Mockito.when(gui.getGraphics()).thenReturn(graphics);
        Mockito.when(gui.isFinished(quit)).thenReturn(true);
        game.start();
        Mockito.verify(gui,Mockito.times(1)).isFinished(quit);

    }
}
