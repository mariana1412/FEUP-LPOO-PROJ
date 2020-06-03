package g76.Controller.Commands;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Game;

import java.io.IOException;

public interface Command {

    void execute(Game game, TextGraphics graphics) throws IOException, InterruptedException;

}
