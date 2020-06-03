package g76.Tests;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.InstructionsCommand;
import g76.Controller.Commands.ChangeStateCommands.MenuCommand;
import g76.Controller.Commands.ChangeStateCommands.PlayCommand;
import g76.Controller.Commands.ChangeStateCommands.QuitCommand;
import g76.Controller.Commands.DoNothingCommand;
import g76.Controller.Commands.NextOptionCommand;
import g76.Controller.Commands.PlayerAction.JumpPlayer;
import g76.Controller.Commands.PlayerAction.MoveLeftPlayer;
import g76.Controller.Commands.PlayerAction.MoveRightPlayer;
import g76.Controller.Commands.PreviousOptionCommand;
import g76.Model.Arena;
import g76.Model.GameLogic;
import g76.Model.Menu;
import g76.View.ArenaCreator;
import g76.View.Gui.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GuiClassesTest {

    @Test
    public void gameOverGui() throws IOException {

        TextGraphics graphics = mock(TextGraphics.class);
        KeyStroke input = mock(KeyStroke.class);

        GameOverGui gui = new GameOverGui(graphics);
        gui.setScore(5);
        when(input.getKeyType()).thenReturn(KeyType.EOF);
        assertTrue(gui.getNextCommand(input) instanceof QuitCommand);
        when(input.getKeyType()).thenReturn(KeyType.Enter);
        assertTrue(gui.getNextCommand(input) instanceof MenuCommand);
        when(input.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertTrue(gui.getNextCommand(input) instanceof DoNothingCommand);

        TerminalScreen screen = mock(TerminalScreen.class);
        gui.draw(screen);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen,Mockito.times(1)).refresh();
        assertEquals(gui.getDrawGameOver().getScore(),5);

    }

    @Test
    public void instructionsGui() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        KeyStroke input = mock(KeyStroke.class);
        InstructionsGui gui = new InstructionsGui(graphics);

        when(input.getKeyType()).thenReturn(KeyType.EOF);
        assertTrue(gui.getNextCommand(input) instanceof QuitCommand);

        when(input.getKeyType()).thenReturn(KeyType.Enter);
        assertTrue(gui.getNextCommand(input) instanceof MenuCommand);

        when(input.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertTrue(gui.getNextCommand(input) instanceof DoNothingCommand);

        TerminalScreen screen = mock(TerminalScreen.class);
        gui.draw(screen);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen,Mockito.times(1)).refresh();
    }

    @Test
    public void menuGui() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        KeyStroke input = mock(KeyStroke.class);
        Menu menu = new Menu();
        MenuGui gui = new MenuGui(graphics, menu);

        when(input.getKeyType()).thenReturn(KeyType.EOF);
        assertTrue(gui.getNextCommand(input) instanceof QuitCommand);

        when(input.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertTrue(gui.getNextCommand(input) instanceof NextOptionCommand);

        when(input.getKeyType()).thenReturn(KeyType.ArrowUp);
        assertTrue(gui.getNextCommand(input) instanceof PreviousOptionCommand);

        when(input.getKeyType()).thenReturn(KeyType.Enter);
        menu.setOption(0);
        assertTrue(gui.getNextCommand(input) instanceof PlayCommand);
        menu.setOption(1);
        assertTrue(gui.getNextCommand(input) instanceof InstructionsCommand);
        menu.setOption(2);
        assertTrue(gui.getNextCommand(input) instanceof QuitCommand);

        when(input.getKeyType()).thenReturn(KeyType.Backspace);
        assertTrue(gui.getNextCommand(input) instanceof DoNothingCommand);

        TerminalScreen screen = mock(TerminalScreen.class);
        gui.draw(screen);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen,Mockito.times(1)).refresh();


    }
    @Test
    public void playGui() throws IOException, InterruptedException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = screen.newTextGraphics();
        TextGraphics graph = Mockito.mock(TextGraphics.class);
        KeyStroke input = mock(KeyStroke.class);
        ArenaCreator ar = new ArenaCreator();
        Arena arena = ar.createArena(50,25);
        GameLogic gameLogic = new GameLogic();
        GameLogic game = Mockito.mock(GameLogic.class);
        PlayGui gui = new PlayGui(graphics,arena,gameLogic);
        PlayGui playGui = new PlayGui(graph,arena,game);


        assertTrue(gui.getNextCommand(screen,null) instanceof DoNothingCommand);


        when(input.getKeyType()).thenReturn(KeyType.EOF);
        assertTrue(gui.getNextCommand(screen,input) instanceof QuitCommand);
        when(input.getKeyType()).thenReturn(KeyType.Escape);
        assertTrue(gui.getNextCommand(screen,input) instanceof QuitCommand);

        when(input.getKeyType()).thenReturn(KeyType.Character);
        when(input.getCharacter()).thenReturn('A');
        assertTrue(gui.getNextCommand(screen,input) instanceof MoveLeftPlayer);
        when(input.getCharacter()).thenReturn('a');
        assertTrue(gui.getNextCommand(screen,input) instanceof MoveLeftPlayer);

        when(input.getCharacter()).thenReturn('D');
        assertTrue(gui.getNextCommand(screen,input) instanceof MoveRightPlayer);
        when(input.getCharacter()).thenReturn('d');
        assertTrue(gui.getNextCommand(screen,input) instanceof MoveRightPlayer);

        when(input.getCharacter()).thenReturn('W');
        assertTrue(gui.getNextCommand(screen,input) instanceof JumpPlayer);
        when(input.getCharacter()).thenReturn('w');
        assertTrue(gui.getNextCommand(screen,input) instanceof JumpPlayer);


        when(input.getCharacter()).thenReturn('k');
        assertTrue(gui.getNextCommand(screen,input) instanceof DoNothingCommand);

        playGui.draw(screen);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();

        Boolean output = playGui.updateGame();
        assertFalse(output);
        Mockito.verify(game, Mockito.times(1)).setStartTime();
        Boolean s = playGui.updateGame();
        Mockito.verify(game, Mockito.times(1)).checkGame(arena);
        assertFalse(s);

    }

    @Test
    public void winGui() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        WinGui gui = new WinGui(graphics);
        gui.setScore(5);
        KeyStroke input = mock(KeyStroke.class);

        when(input.getKeyType()).thenReturn(KeyType.EOF);
        assertTrue(gui.getNextCommand(input) instanceof QuitCommand);
        when(input.getKeyType()).thenReturn(KeyType.Enter);
        assertTrue(gui.getNextCommand(input) instanceof MenuCommand);
        when(input.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertTrue(gui.getNextCommand(input) instanceof DoNothingCommand);

        

        TerminalScreen screen = mock(TerminalScreen.class);
        gui.draw(screen);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen,Mockito.times(1)).refresh();
        assertEquals(gui.getDrawWin().getScore(),5);

    }

}
