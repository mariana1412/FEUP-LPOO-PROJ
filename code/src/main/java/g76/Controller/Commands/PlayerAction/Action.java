package g76.Controller.Commands.PlayerAction;

import g76.Controller.Commands.Command;
import g76.Model.Arena;

import java.io.IOException;

public interface Action extends Command {

    void executeAction(Arena arena) throws IOException, InterruptedException;
}
