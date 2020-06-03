package g76.View.DrawFunctions;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class DrawElement extends Draw {

    protected int height, width;

    public DrawElement(TextGraphics graphics, int height, int width) {
        super(graphics);
        this.height = height;
        this.width = width;
    }

}
