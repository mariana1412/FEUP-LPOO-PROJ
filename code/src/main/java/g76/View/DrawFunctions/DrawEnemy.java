package g76.View.DrawFunctions;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Model.Position;

public class DrawEnemy extends DrawElement {


    public DrawEnemy(TextGraphics graphics, int height, int width) {
        super(graphics, height, width);
    }

    public void draw(Position position) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#8B0000"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#add8e6"));
        graphics.putString(position.getX(), position.getY(), ">");
    }
}
