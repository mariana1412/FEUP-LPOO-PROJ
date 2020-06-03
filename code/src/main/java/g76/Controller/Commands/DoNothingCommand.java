package g76.Controller.Commands;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Game;

public class DoNothingCommand implements Command {

    @Override
    public void execute(Game game, TextGraphics graphics) {}

}
