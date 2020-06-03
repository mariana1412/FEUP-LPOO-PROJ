package g76.View.DrawFunctions;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Model.Position;

public class DrawPlayer extends DrawElement {

    public DrawPlayer(TextGraphics graphics, int height, int width) {
        super(graphics, height, width);
    }

    public void draw(Position position) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.drawRectangle(
                new TerminalPosition(position.getX(), position.getY()), new TerminalSize(width, height), ' ');

    }
}
