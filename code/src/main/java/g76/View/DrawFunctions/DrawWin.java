package g76.View.DrawFunctions;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawWin extends Draw {

    private final int width = 50;
    private final int height = 26;
    private int score = 0;

    public DrawWin(TextGraphics graphics) {
        super(graphics);
    }

    public void draw() {
        setColors("#000000", "#C4970B");
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.putString(new TerminalPosition(0, height / 2 - 5), " __   _____  _   _  __        _____  _   _   _ ");
        graphics.putString(new TerminalPosition(0, height / 2 - 4), " \\ \\ / / _ \\| | | | \\ \\      / / _ \\| \\ | | | |");
        graphics.putString(new TerminalPosition(0, height / 2 - 3), "  \\ V / | | | | | |  \\ \\ /\\ / / | | |  \\| | | |");
        graphics.putString(new TerminalPosition(0, height / 2-2),   "   | || |_| | |_| |   \\ V  V /| |_| | |\\  | |_|");
        graphics.putString(new TerminalPosition(0, height / 2 - 1), "   |_| \\___/ \\___/     \\_/\\_/  \\___/|_| \\_| (_)");
        setColors("#000000", "#ffffff");
        graphics.putString(new TerminalPosition(3, height / 2 + 2), "Score: " + score);

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
