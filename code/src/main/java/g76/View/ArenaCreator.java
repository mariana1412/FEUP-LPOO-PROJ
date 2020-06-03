package g76.View;

import g76.Model.*;

public class ArenaCreator {
    public Arena createArena(int width, int height) {
        int posX= width / 2 - 2;
        int posY = height - 1;
        Player player = new Player(posX, posY , 2, 1);
        Arena arena = new Arena(player, width, height);
        int x = width / 2 + 2;
        int y = player.getPosition().getY() - 4;
        arena.addElement(new Platform(x, y, 8, 1));

        while (true) {
            if (!arena.addNewPlatform()) break;
        }
        Position secondPlatform = arena.getAllElements().get(2).getPosition();

        Coin coin = new Coin(secondPlatform.getX() + 4, secondPlatform.getY() - 1, 1, 1);

        arena.addElement(coin);

        return arena;
    }
}
