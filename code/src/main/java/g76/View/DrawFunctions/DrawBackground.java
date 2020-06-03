package g76.View.DrawFunctions;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawBackground extends Draw {

    private int score, health;
    private boolean first = true;

    public DrawBackground(TextGraphics graphics) {
        super(graphics);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore(){
        return score;
    }

    public int getHealth(){
        return health;
    }

    public void draw() {
        drawArena();
        if (first) {
            drawFootnote();
            first = false;
        }
        drawScore();
        drawHealth();
    }

    private void drawArena() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#add8e6"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(50, 25), ' ');
    }

    private void drawFootnote() {
        setColors("#000000", "#708090");
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, 25), " Score: 0");
        graphics.putString(new TerminalPosition(29, 25), "Health: # # #");
    }

    private void drawScore() {
        setColors("#000000", "#708090");
        graphics.putString(new TerminalPosition(8, 25), String.valueOf(score));
    }

    private void drawHealth() {
        setColors("#000000", "#708090");
        graphics.putString(new TerminalPosition(37, 25), "      ");

        String sHealth = "";

        for (int i = 0; i < health; i++) {
            sHealth += "# ";
        }

        graphics.putString(new TerminalPosition(37, 25), sHealth);
    }

}
