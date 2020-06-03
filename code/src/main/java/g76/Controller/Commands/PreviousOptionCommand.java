package g76.Controller.Commands;

import com.googlecode.lanterna.graphics.TextGraphics;
import g76.Controller.Game;
import g76.Model.Menu;

import java.io.IOException;

public class PreviousOptionCommand implements Command {

    private final Menu menu;

    public PreviousOptionCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(Game game, TextGraphics graphics) throws IOException, InterruptedException {
        if (this.menu.getOption() == 0) return;

        this.menu.setOption(menu.getOption() - 1);
        this.menu.step();
    }
}
