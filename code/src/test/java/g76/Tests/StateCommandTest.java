package g76.Tests;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.*;
import g76.Controller.Commands.DoNothingCommand;
import g76.Controller.Commands.NextOptionCommand;
import g76.Controller.Commands.PlayerAction.*;
import g76.Controller.Commands.PreviousOptionCommand;
import g76.Controller.Game;
import g76.Controller.States.*;
import g76.Model.Menu;
import g76.View.Gui.Gui;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StateCommandTest {

    private Game game;
    private TextGraphics graphics;
    private TerminalScreen terminalScreen;
    private Gui gui;

    @Before
    public void createMocks() {
        this.graphics = mock(TextGraphics.class);
        this.gui = mock(Gui.class);
        this.game = new Game(this.gui);
        this.terminalScreen = mock(TerminalScreen.class);

    }

    @Test
    public void changeToGameOver() throws IOException, InterruptedException {
        PlayState playState = new PlayState(graphics);
        game.setCurrentState(playState);
        GameOverCommand command = new GameOverCommand();
        command.execute(game, graphics);
        assertTrue(game.getCurrentState() instanceof GameOverState);
        verify(gui).draw(game.getCurrentState());
    }

    @Test
    public void changeToInstructions(){
        InstructionsCommand command = new InstructionsCommand();
        command.execute(game, graphics);
        assertTrue(game.getCurrentState() instanceof InstructionsState);
    }

    @Test
    public void changeToMenu(){
        MenuCommand command = new MenuCommand();
        command.execute(game, graphics);
        assertTrue(game.getCurrentState() instanceof MenuState);
    }

    @Test
    public void changeToPlay() {
        PlayCommand command = new PlayCommand();
        command.execute(game, graphics);
        assertEquals(game.getCurrentState() instanceof PlayState, true);
    }

    @Test
    public void changeToQuit() {
        QuitCommand command = new QuitCommand();
        command.execute(game, graphics);
        assertTrue(game.getCurrentState() instanceof QuitState);
    }

    @Test
    public void changeToWin() throws IOException, InterruptedException {
        PlayState playState = new PlayState(graphics);
        game.setCurrentState(playState);
        WinCommand command = new WinCommand();
        command.execute(game,graphics);
        assertTrue(game.getCurrentState()instanceof WinState);
        verify(gui).draw(game.getCurrentState());

    }
    @Test
    public void isFinished(){
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        WinState win = new WinState(graphics);
        assertFalse(win.isFinished());

        StartState start = new StartState();
        assertFalse(win.isFinished());

        QuitState quit = new QuitState();
        assertTrue(quit.isFinished());

        PlayState play = new PlayState(graphics);
        assertFalse(play.isFinished());

        MenuState menu = new MenuState(graphics);
        assertFalse(menu.isFinished());

        InstructionsState instructions = new InstructionsState(graphics);
        assertFalse(instructions.isFinished());

        GameOverState gameOver = new GameOverState(graphics);
        assertFalse(gameOver.isFinished());
    }

    @Test
    public void constructorStates(){
        GameOverState gameOverState = new GameOverState(graphics);
        assertNotNull(gameOverState.getGui());

        InstructionsState instructionsState = new InstructionsState(graphics);
        assertNotNull(instructionsState.getGui());

        MenuState menuState = new MenuState(graphics);
        assertNotNull(menuState.getGui());

        PlayState playState = new PlayState(graphics);
        playState.getArena().getEnemy().setIsNotOnScreen(false);
        assertNotNull(playState.getGui());
        assertNotNull(playState.getArena());

        QuitState stateQuit = new QuitState();
        assertTrue(stateQuit.getNextCommand(terminalScreen, null) instanceof DoNothingCommand);
        StartState stateStart = new StartState();
        assertTrue(stateStart.getNextCommand(terminalScreen, null) instanceof MenuCommand);
    }

    @Test
    public void nextOpCommand() throws IOException, InterruptedException {
        Menu menu = Mockito.mock(Menu.class);
        Game game = Mockito.mock(Game.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        NextOptionCommand nop = new NextOptionCommand(menu);
        nop.execute(game, graphics);
        Mockito.verify(menu, Mockito.times(1)).setOption(1);
        Mockito.verify(menu, Mockito.times(1)).step();
    }

    @Test
    public void prevOpCommand() throws IOException, InterruptedException {
        Menu menu = Mockito.spy(new Menu());
        menu.setOption(2);
        Game game = Mockito.mock(Game.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        PreviousOptionCommand pop = new PreviousOptionCommand(menu);
        pop.execute(game, graphics);
        Mockito.verify(menu, Mockito.times(1)).setOption(1);
        Mockito.verify(menu, Mockito.times(1)).step();
    }
    @Test
    public void executeAction() throws IOException, InterruptedException {
        Gui gui = Mockito.mock(Gui.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Game game = new Game(gui);
        game.setCurrentState(new PlayState(graphics));
        MoveRightPlayer mv = Mockito.spy(new MoveRightPlayer());
        mv.execute(game, graphics);
        Mockito.verify(mv, Mockito.times(1)).executeAction(((PlayState)game.getCurrentState()).getArena());

        MoveLeftPlayer ml = Mockito.spy(new MoveLeftPlayer());
        ml.execute(game, graphics);
        Mockito.verify(ml, Mockito.times(1)).executeAction(((PlayState)game.getCurrentState()).getArena());


        JumpPlayer jp = Mockito.spy(new JumpPlayer());
        jp.execute(game, graphics);
        Mockito.verify(jp, Mockito.times(1)).executeAction(((PlayState)game.getCurrentState()).getArena());

        JumpLeftPlayer jl = Mockito.spy(new JumpLeftPlayer());
        jl.execute(game, graphics);
        Mockito.verify(jl, Mockito.times(1)).executeAction(((PlayState)game.getCurrentState()).getArena());

        JumpRightPlayer jr = Mockito.spy(new JumpRightPlayer());
        jr.execute(game, graphics);
        Mockito.verify(jr, Mockito.times(1)).executeAction(((PlayState)game.getCurrentState()).getArena());

    }
}
