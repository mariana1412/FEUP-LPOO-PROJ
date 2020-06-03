package g76.Tests;


import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Model.*;
import g76.View.DrawFunctions.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class ViewTest {
    //All tests from View

    @Test
    public void drawPlayer(){
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(new Position(10,20));

        TextGraphics screen = Mockito.mock(TextGraphics.class);

        DrawPlayer dpl = new DrawPlayer(screen, player.getHeight(), player.getWidth());
        dpl.draw(player.getPosition());

        Mockito.verify(screen, Mockito.times(1)).drawRectangle(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), new TerminalSize(player.getWidth(),player.getHeight()), ' ');

    }

    @Test
    public void drawPlatform(){
        Platform platform = Mockito.mock(Platform.class);
        Mockito.when(platform.getPosition()).thenReturn(new Position(10,20));

        TextGraphics screen = Mockito.mock(TextGraphics.class);

        DrawPlatform pl = new DrawPlatform(screen, platform.getHeight(),platform.getWidth());
        pl.draw(platform.getPosition());

        Mockito.verify(screen, Mockito.times(1)).drawRectangle(new TerminalPosition(platform.getPosition().getX(), platform.getPosition().getY()), new TerminalSize(platform.getWidth(),platform.getHeight()), ' ') ;

    }

    @Test
    public void drawCoin(){
        Coin coin = Mockito.mock(Coin.class);
        Mockito.when(coin.getPosition()).thenReturn(new Position(10,20));

        TextGraphics screen = Mockito.mock(TextGraphics.class);

        DrawCoin pl = new DrawCoin(screen, coin.getHeight(),coin.getWidth());
        pl.draw(coin.getPosition());

        Mockito.verify(screen, Mockito.times(1)).putString(coin.getPosition().getX(), coin.getPosition().getY(), "C");

    }
    @Test
    public void drawKiller(){
        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(new Position(10,20));

        TextGraphics screen = Mockito.mock(TextGraphics.class);

        DrawEnemy pl = new DrawEnemy(screen, enemy.getHeight(), enemy.getWidth());
        pl.draw(enemy.getPosition());

        Mockito.verify(screen, Mockito.times(1)).putString(enemy.getPosition().getX(), enemy.getPosition().getY(), ">");

    }
    @Test
    public void setInBackground(){
        TextGraphics screen = Mockito.mock(TextGraphics.class);
        DrawBackground background = new DrawBackground(screen);

        assertEquals(background.getHealth(),0);
        background.setHealth(3);
        assertEquals(background.getHealth(),3);

        assertEquals(background.getScore(),0);
        background.setScore(5);
        assertEquals(background.getScore(),5);

    }

    @Test
    public void drawBackground(){
        TextGraphics screen = Mockito.mock(TextGraphics.class);
        DrawBackground background = new DrawBackground(screen);
        background.setHealth(2);
        background.draw();


        Mockito.verify(screen, Mockito.times(1)).fillRectangle(new TerminalPosition(0,0), new TerminalSize(50,25),' ');
        Mockito.verify(screen, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 25), " Score: 0");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(29, 25), "Health: # # #");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(8, 25), String.valueOf(background.getScore()));
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(37, 25), "      ");


        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(37, 25), "# # ");
        Mockito.verify(screen, Mockito.times(3)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(screen, Mockito.times(3)).setForegroundColor(TextColor.Factory.fromString("#708090"));

    }


    @Test
    public void drawGameOver(){
        TextGraphics screen = Mockito.mock(TextGraphics.class);
        DrawGameOver gameOver = new DrawGameOver(screen);
        gameOver.setScore(5);
        assertEquals(gameOver.getScore(), 5);
        gameOver.draw();
        Mockito.verify(screen, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 26), ' ');
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 5), "  _____                       ____                 ");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 4), " / ____|                     / __ \\                ");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 3), "| |  __  __ _ _ __ ___   ___| |  | |_   _____ _ _ ");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 2), "| | |_ |/ _` | '_ ` _ \\ / _ \\ |  | \\ \\ / / _ \\ '_|");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 1), "| |__| | (_| | | | | | |  __/ |__| |\\ V /  __/ |   ");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 ), " \\_____|\\__,_|_| |_| |_|\\___|\\____/  \\_/ \\___|_|   ");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(1, 26 / 2 + 2), "Score: " + gameOver.getScore());
        Mockito.verify(screen, Mockito.times(1)).enableModifiers(SGR.BLINK);
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(5, 24), "Press Enter to go back to the menu...");
        Mockito.verify(screen, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(screen, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#add8e6"));

    }
    @Test
    public void drawInstructions(){
        TextGraphics screen = Mockito.mock(TextGraphics.class);
        DrawInstructions instructions = new DrawInstructions(screen);

        instructions.draw();

        Mockito.verify(screen, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(screen, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#add8e6"));
        Mockito.verify(screen, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 26), ' ');
        Mockito.verify(screen, Mockito.times(1)).disableModifiers(SGR.BLINK);
        Mockito.verify(screen, Mockito.times(3)).setForegroundColor(TextColor.Factory.fromString("#ffffff"));// draw e draw Coin
        Mockito.verify(screen, Mockito.times(3)).disableModifiers(SGR.BOLD);
        Mockito.verify(screen, Mockito.times(1)).enableModifiers(SGR.BLINK);

        //drawTitle
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 0), "  ___         _               _   _             ");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 1), " |_ _|_ _  __| |_ _ _ _  _ __| |_(_)___ _ _  ___");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 2), "  | || ' \\(_-<  _| '_| || / _|  _| / _ \\ ' \\(_-<");
        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(0, 3), " |___|_||_/__/\\__|_|  \\_,_\\__|\\__|_\\___/_||_/__/");

        Mockito.verify(screen, Mockito.times(2)).enableModifiers(SGR.BOLD);
        Mockito.verify(screen, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#8B0000"));
        Mockito.verify(screen, Mockito.times(1)).putString(7, 18, " __  ");
        Mockito.verify(screen, Mockito.times(1)).putString(7, 19, " \\ \\ ");
        Mockito.verify(screen, Mockito.times(1)).putString(7, 20, "  \\ \\");
        Mockito.verify(screen, Mockito.times(1)).putString(7, 21, "  / /");
        Mockito.verify(screen, Mockito.times(1)).putString(7, 22, " /_/ ");

        Mockito.verify(screen, Mockito.times(1)).putString(new TerminalPosition(15, 20), "Collide with it and lose one life.");
    }

    @Test
    public void drawMenu() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        DrawMenu menu = Mockito.spy(new DrawMenu(graphics));
        doNothing().when(menu).drawButton(Mockito.anyString(), Mockito.anyBoolean());
        menu.draw("Play");
        verify(menu).drawButton("Play", true);
        verify(menu).drawButton("Instructions", false);



        DrawMenu menu2 = Mockito.spy(new DrawMenu(graphics));
        doNothing().when(menu2).drawButton(Mockito.anyString(), Mockito.anyBoolean());
        menu2.draw("Instructions");

        verify(menu2).drawButton("Play", false);
        verify(menu2).drawButton("Instructions", true);
        verify(menu2).drawButton("Quit", false);

        DrawMenu menu3 = Mockito.spy(new DrawMenu(graphics));
        doNothing().when(menu3).drawButton(Mockito.anyString(), Mockito.anyBoolean());
        menu3.draw("Quit");
        verify(menu3).drawButton("Quit", true);
        verify(menu3).drawButton("Instructions", false);


        DrawMenu menu4 = Mockito.spy(new DrawMenu(graphics));
        menu4.initialize();

        Mockito.verify(graphics,Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(menu4, Mockito.times(1)).drawTitle();
        Mockito.verify(menu4, Mockito.times(1)).drawText();
        Mockito.verify(menu4, Mockito.times(1)).drawButton("Play", true);
        Mockito.verify(menu4, Mockito.times(1)).drawButton("Instructions", false);
        Mockito.verify(menu4, Mockito.times(1)).drawButton("Quit", false);

        DrawMenu menu5 = new DrawMenu(graphics);
        menu5.drawButton("Play", true);
        Mockito.verify(graphics, Mockito.times(2)).enableModifiers(SGR.BOLD, SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(4)).setBackgroundColor(TextColor.Factory.fromString("#add8e6"));
    }

    @Test
    public void drawWin(){
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        DrawWin win = new DrawWin(graphics);

        win.setScore(2);
        win.draw();

        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 26), ' ');
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 5), " __   _____  _   _  __        _____  _   _   _ ");
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 4), " \\ \\ / / _ \\| | | | \\ \\      / / _ \\| \\ | | | |");
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 3), "  \\ V / | | | | | |  \\ \\ /\\ / / | | |  \\| | | |");
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2-2),   "   | || |_| | |_| |   \\ V  V /| |_| | |\\  | |_|");
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 26 / 2 - 1), "   |_| \\___/ \\___/     \\_/\\_/  \\___/|_| \\_| (_)");

        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 26 / 2 + 2), "Score: " + win.getScore());

        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(5, 24), "Press Enter to go back to the menu...");
        Mockito.verify(graphics,Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#C4970B"));
        Mockito.verify(graphics,Mockito.times(2)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics,Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#ffffff"));

    }
}
