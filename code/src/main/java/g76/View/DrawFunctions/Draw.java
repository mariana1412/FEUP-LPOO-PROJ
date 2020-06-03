package g76.View.DrawFunctions;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Draw {

    TextGraphics graphics;

    public Draw(TextGraphics graphics) {
        this.graphics = graphics;
    }

    protected void setColors(String background, String foreground) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(background));
        graphics.setForegroundColor(TextColor.Factory.fromString(foreground));
    }

}
