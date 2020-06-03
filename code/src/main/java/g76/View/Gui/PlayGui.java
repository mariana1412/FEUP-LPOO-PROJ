package g76.View.Gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.ChangeStateCommands.GameOverCommand;
import g76.Controller.Commands.ChangeStateCommands.QuitCommand;
import g76.Controller.Commands.ChangeStateCommands.WinCommand;
import g76.Controller.Commands.Command;
import g76.Controller.Commands.DoNothingCommand;
import g76.Controller.Commands.PlayerAction.*;
import g76.Model.Arena;
import g76.Model.Coin;
import g76.Model.GameLogic;
import g76.Model.Platform;
import g76.View.DrawFunctions.*;

import java.io.IOException;

public class PlayGui {

    private boolean firstTime;
    private final Arena arena;
    private final DrawPlayer playerDraw;
    private final DrawPlatform platformDraw;
    private final DrawBackground background;
    private final DrawCoin drawCoin;
    private final DrawEnemy drawKiller;
    private final GameLogic gameLogic;

    public PlayGui(TextGraphics graphics, Arena arena, GameLogic gameLogic) {
        this.arena = arena;
        this.gameLogic = gameLogic;
        this.firstTime = true;

        int width, height;

        height = arena.getPlayer().getHeight();
        width = arena.getPlayer().getWidth();
        this.playerDraw = new DrawPlayer(graphics, height, width);

        height = arena.getPlatforms().get(0).getHeight();
        width = arena.getPlatforms().get(0).getWidth();
        this.platformDraw = new DrawPlatform(graphics, height, width);

        this.background = new DrawBackground(graphics);

        height = arena.getPlatforms().get(0).getHeight();
        width = arena.getPlatforms().get(0).getWidth();
        this.drawCoin = new DrawCoin(graphics, height, width);

        height = arena.getPlatforms().get(0).getHeight();
        width = arena.getPlatforms().get(0).getWidth();
        this.drawKiller = new DrawEnemy(graphics, height, width);
    }

    public void draw(TerminalScreen screen) throws IOException {

        if (firstTime) screen.clear();

        this.background.setHealth(arena.getPlayer().getHealth());
        this.background.setScore(arena.getPlayer().getScore());
        this.background.draw();


        for (Platform plat : arena.getPlatforms()) {
            platformDraw.draw(plat.getPosition());
        }
        for (Coin coin : arena.getCoins()) {
            drawCoin.draw(coin.getPosition());
        }
        if (!arena.getEnemy().isNotOnScreen()) {
            drawKiller.draw(arena.getEnemy().getPosition());
        }

        this.playerDraw.draw(this.arena.getPlayer().getPosition());
        screen.refresh();
    }

    public Boolean updateGame() {
        boolean updateScreen = false;

        if (!firstTime) updateScreen = gameLogic.checkGame(arena);

        if (firstTime) {
            gameLogic.setStartTime();
            firstTime = false;
        }

        return updateScreen;
    }
    public Command getNextCommand(TerminalScreen screen, KeyStroke input) throws InterruptedException, IOException {

        if (gameLogic.isLost()) return new GameOverCommand();
        if(arena.playerWin()) return new WinCommand();

        if (updateGame()) arena.step();

        if (input == null) {
            return new DoNothingCommand();
        }

        if (input.getKeyType() == KeyType.EOF || input.getKeyType() == KeyType.Escape) {
            return new QuitCommand();
        }

        if ((input.getKeyType() == KeyType.Character && input.getCharacter() == 'w') || (input.getKeyType() == KeyType.Character && input.getCharacter() == 'W')) {

            for(int i = 0; i < 9; i++){
                Thread.sleep(10);
                if (updateGame()) arena.step();
            }

            input = screen.pollInput();


            if (input == null) {
                return new JumpPlayer();
            }

            if ((input.getKeyType() == KeyType.Character && input.getCharacter() == 'a') || (input.getKeyType() == KeyType.Character && input.getCharacter() == 'A')) {
                return new JumpLeftPlayer();
            }
            if ((input.getKeyType() == KeyType.Character && input.getCharacter() == 'd') || (input.getKeyType() == KeyType.Character && input.getCharacter() == 'D')) {
                return new JumpRightPlayer();
            }

            return new JumpPlayer();
        }

        if ((input.getKeyType() == KeyType.Character && input.getCharacter() == 'a') || (input.getKeyType() == KeyType.Character && input.getCharacter() == 'A')) {
            return new MoveLeftPlayer();
        }
        if ((input.getKeyType() == KeyType.Character && input.getCharacter() == 'd') || (input.getKeyType() == KeyType.Character && input.getCharacter() == 'D')) {
            return new MoveRightPlayer();
        }

        return new DoNothingCommand();
    }

}
