package g76.Controller.States;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g76.Controller.Commands.Command;
import g76.Model.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameState implements Observer {

    protected final List<Observer> observers;

    public GameState() {
        this.observers = new ArrayList<>();;
    }

    abstract public void draw(TerminalScreen screen) throws IOException;

    abstract public Command getNextCommand(TerminalScreen screen, KeyStroke input) throws InterruptedException, IOException;

    public boolean isFinished(){
        return false;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    private void notifyObservers() throws IOException, InterruptedException {
        for (Observer observer : observers) observer.gameChanged();
    }

    @Override
    public void gameChanged() throws IOException, InterruptedException {
        this.notifyObservers();
    }
}
