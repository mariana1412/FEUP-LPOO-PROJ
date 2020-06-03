package g76;

import g76.Controller.Game;
import g76.View.Gui.Gui;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            new Game(new Gui()).start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
