package g76.View.DrawFunctions;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawMenu extends Draw {

    public DrawMenu(TextGraphics graphics) {
        super(graphics);
    }

    public void initialize() {
        graphics.disableModifiers(SGR.BOLD, SGR.BLINK);
        setColors("#000000", "#add8e6");
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 26), ' ');
        drawTitle();
        drawText();
        drawButton("Play", true);
        drawButton("Instructions", false);
        drawButton("Quit", false);
    }

    public void draw(String string) {

        if (string == "Play") {
            drawButton("Play", true);
            drawButton("Instructions", false);
        } else if (string == "Instructions") {
            drawButton("Play", false);
            drawButton("Instructions", true);
            drawButton("Quit", false);
        }
        if (string == "Quit") {
            drawButton("Quit", true);
            drawButton("Instructions", false);
        }
    }

    public void drawButton(String text, boolean isBlinking) {

        TerminalPosition positionRectangle = null, positionText = null;

        if (isBlinking)
            graphics.enableModifiers(SGR.BOLD, SGR.BLINK);
        else
            graphics.disableModifiers(SGR.BOLD, SGR.BLINK);

        setColors("#add8e6", "#000000");
        switch (text) {
            case "Play":
                positionRectangle = new TerminalPosition(50 / 2 - 7, 26 / 2 - 2);
                positionText = new TerminalPosition(50 / 2 - 2, 26 / 2 - 1);
                break;
            case "Instructions":
                positionRectangle = new TerminalPosition(50 / 2 - 7, 26 / 2 + 2);
                positionText = new TerminalPosition(50 / 2 - 6, 26 / 2 + 3);
                break;
            case "Quit":
                positionRectangle = new TerminalPosition(50 / 2 - 7, 26 / 2 + 6);
                positionText = new TerminalPosition(50 / 2 - 2, 26 / 2 + 7);
                break;
            }

        graphics.fillRectangle(positionRectangle, new TerminalSize(14, 3), ' ');
        graphics.putString(positionText, text);
    }

    public void drawText() {
        graphics.putString(new TerminalPosition(9, 24), "Press Enter to select the option");
    }

    public void drawTitle() {
        graphics.putString(new TerminalPosition(1, 3), "  ____  _              _                        ");
        graphics.putString(new TerminalPosition(1, 4), " / ___|| | ___   _    | |_   _ _ __ ___  _ __   ");
        graphics.putString(new TerminalPosition(1, 5), " \\___ \\| |/ / | | |_  | | | | | '_ ` _ \\| '_ \\  ");
        graphics.putString(new TerminalPosition(1, 6), "  ___) |   <| |_| | |_| | |_| | | | | | | |_) | ");
        graphics.putString(new TerminalPosition(1, 7), " |____/|_|\\_\\\\__, |\\___/ \\__,_|_| |_| |_| .__/  ");
        graphics.putString(new TerminalPosition(1, 8), "             |___/                      |_|     ");
    }


}
