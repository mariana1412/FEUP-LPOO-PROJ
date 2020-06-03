package g76.Controller;


import g76.Controller.Commands.Command;
import g76.Controller.States.GameState;
import g76.Controller.States.StartState;
import g76.Model.Observer;
import g76.View.Gui.Gui;

import java.io.IOException;

public class Game implements Observer {
    private GameState currentState;
    private final Gui gui;

    public Game(Gui gui){
        this.currentState = new StartState();
        this.gui = gui;
    }


    public void start() throws IOException, InterruptedException {
        Command command;

        while (!gui.isFinished(currentState)) {
            command = gui.getNextCommand(currentState);
            command.execute(this, gui.getGraphics());
        }

    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    @Override
    public void gameChanged() {
        try {
            gui.draw(currentState);
        } catch (IOException | InterruptedException e) {
            //do nothing
        }
    }
}
