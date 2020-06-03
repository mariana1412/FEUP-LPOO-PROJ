package g76.View.DrawFunctions;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Model.Position;

public class DrawCoin extends DrawElement {

    public DrawCoin(TextGraphics graphics, int height, int width) {
        super(graphics, height, width);
    }

    public void draw(Position position) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#add8e6"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#DAA520"));
        graphics.putString(position.getX(), position.getY(), "C");
    }
}
