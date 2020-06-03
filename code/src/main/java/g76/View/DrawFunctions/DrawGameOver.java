package g76.View.DrawFunctions;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawGameOver extends Draw {

    private final int width = 50;
    private final int height = 26;
    private int score = 0;

    public DrawGameOver(TextGraphics graphics) {
        super(graphics);
    }

    public void draw() {
        setColors("#000000", "#add8e6");
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.putString(new TerminalPosition(0, height / 2 - 5), "  _____                       ____                 ");
        graphics.putString(new TerminalPosition(0, height / 2 - 4), " / ____|                     / __ \\                ");
        graphics.putString(new TerminalPosition(0, height / 2 - 3), "| |  __  __ _ _ __ ___   ___| |  | |_   _____ _ _ ");
        graphics.putString(new TerminalPosition(0, height / 2-2),   "| | |_ |/ _` | '_ ` _ \\ / _ \\ |  | \\ \\ / / _ \\ '_|");
        graphics.putString(new TerminalPosition(0, height / 2 - 1), "| |__| | (_| | | | | | |  __/ |__| |\\ V /  __/ |   ");
        graphics.putString(new TerminalPosition(0, height / 2 ),    " \\_____|\\__,_|_| |_| |_|\\___|\\____/  \\_/ \\___|_|   ");
        graphics.putString(new TerminalPosition(1, height / 2 + 2), "Score: " + score);

        graphics.enableModifiers(SGR.BLINK);
        graphics.putString(new TerminalPosition(5, 24), "Press Enter to go back to the menu...");
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return score;
    }
}
