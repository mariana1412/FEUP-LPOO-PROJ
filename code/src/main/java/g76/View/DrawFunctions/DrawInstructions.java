package g76.View.DrawFunctions;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawInstructions extends Draw {

    public DrawInstructions(TextGraphics graphics) {
        super(graphics);
    }

    public void draw() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#add8e6"));

        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 26), ' ');
        graphics.disableModifiers(SGR.BLINK);

        drawTitle();
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));

        drawKeys();
        drawCoin();
        drawEnemy();
        drawText();
    }

    private void drawKeys() {
        graphics.putString(new TerminalPosition(6, 5), " ____ ");
        graphics.putString(new TerminalPosition(6, 6), "||W ||");
        graphics.putString(new TerminalPosition(6, 7), "||__||");
        graphics.putString(new TerminalPosition(6, 8), "|/__\\|");


        graphics.putString(new TerminalPosition(1, 9), " ____ ");
        graphics.putString(new TerminalPosition(1, 10), "||A ||");
        graphics.putString(new TerminalPosition(1, 11), "||__||");
        graphics.putString(new TerminalPosition(1, 12), "|/__\\|");


        graphics.putString(new TerminalPosition(11, 9), " ____ ");
        graphics.putString(new TerminalPosition(11, 10), "||D ||");
        graphics.putString(new TerminalPosition(11, 11), "||__||");
        graphics.putString(new TerminalPosition(11, 12), "|/__\\|");

        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(18, 8), "Use these to move the player.");
    }

    private void drawCoin() {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#DAA520"));
        graphics.putString(6, 13, "  ___ \n");
        graphics.putString(6, 14, " / __|");
        graphics.putString(6, 15, "| (__ ");
        graphics.putString(6, 16, " \\___|");

        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(14, 15), "Catch coins to increase your score.");
    }

    private void drawText() {
        graphics.enableModifiers(SGR.BLINK);
        graphics.putString(new TerminalPosition(5, 24), "Press Enter to go back to the menu...");
    }

    private void drawTitle() {
        graphics.putString(new TerminalPosition(0, 0), "  ___         _               _   _             ");
        graphics.putString(new TerminalPosition(0, 1), " |_ _|_ _  __| |_ _ _ _  _ __| |_(_)___ _ _  ___");
        graphics.putString(new TerminalPosition(0, 2), "  | || ' \\(_-<  _| '_| || / _|  _| / _ \\ ' \\(_-<");
        graphics.putString(new TerminalPosition(0, 3), " |___|_||_/__/\\__|_|  \\_,_\\__|\\__|_\\___/_||_/__/");
    }

    private void drawEnemy() {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#8B0000"));
        graphics.putString(7, 18, " __  ");
        graphics.putString(7, 19, " \\ \\ ");
        graphics.putString(7, 20, "  \\ \\");
        graphics.putString(7, 21, "  / /");
        graphics.putString(7, 22, " /_/ ");

        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(15, 20), "Collide with it and lose one life.");
    }
}
