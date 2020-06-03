package g76.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int option = 0;
    private final List<Observer> observers;

    public Menu() {
        this.observers = new ArrayList<>();
    }

    public String optionToString() {
        switch (option) {
            case 0:
                return "Play";

            case 1:
                return "Instructions";

            case 2:
                return "Quit";
        }
        return "";
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /*--------------------Updating Interface-------------------------*/

    public void step() throws IOException, InterruptedException {
        this.notifyObservers();
    }

    private void notifyObservers() throws IOException, InterruptedException {
        for (Observer observer : observers) observer.gameChanged();
    }
}
