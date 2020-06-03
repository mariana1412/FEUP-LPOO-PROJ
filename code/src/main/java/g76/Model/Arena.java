package g76.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;

public class Arena {
    private final int width;
    private final int height;

    private Player player;
    private final List<Platform> platforms;
    private final List<Coin> coins;
    private Enemy enemy;
    private final List<Observer> observers;

    private int numberPlatforms = 0;
    private int speed = 1000;

    public Arena(Player player, int width, int height) {
        this.width = width;
        this.height = height;

        this.player = player;

        this.platforms = new ArrayList<>();

        this.coins = new ArrayList<>();

        this.observers = new ArrayList<>();

        this.enemy = new Enemy(0, 0, 1, 1);
    }
    public void setNumberPlatforms(int numberPlatforms){
        this.numberPlatforms = numberPlatforms;
    }

    /*--------------------Gets-------------------------*/
    public int getSpeed(){ return speed;}

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.add(player);

        elements.addAll(platforms);

        elements.addAll(coins);


        if (!enemy.isNotOnScreen()) elements.add(enemy);

        return elements;
    }

    public int getNumberPlatforms() { return numberPlatforms; }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    /*--------------------Adding Elements-------------------------*/

    public void addElement(Element element) {
        if (element instanceof Player) player = (Player) element;

        if (element instanceof Platform) {
            platforms.add((Platform) element);
            this.numberPlatforms++;
            if((numberPlatforms%100) == 0){
                if((numberPlatforms%500 != 0))
                    this.speed = this.speed - 150;
            }
        }
        if (element instanceof Coin) coins.add((Coin) element);

        if (element instanceof Enemy) enemy = (Enemy) element;
    }


    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public boolean addNewPlatform() {

        Platform lastPlatform = platforms.get(platforms.size() - 1);
        Position lastPos = lastPlatform.getPosition();

        int y = lastPos.getY() - 4;
        int x = (int) (lastPos.getX() + pow(-1, numberPlatforms) * 16);

        if (y < 0)
            return false;

        addElement(new Platform(x, y, 8, 1));
        return true;
    }

    public Boolean addNewCoin() {
        Random objGenerator = new Random();
        int randomNumber = objGenerator.nextInt(300);
        Position platformPos = platforms.get(platforms.size() - 1).getPosition();

        if (platformPos.getY() == 0) {
            return false;//no space for adding coins
        }
        int x = platformPos.getX();
        randomNumber = randomNumber / 100;
        switch (randomNumber) {
            case 0: //in  the platform or higher
                x += 4;
                break;

            case 1: // outer side of the platform
                if (x < width / 2.0) x -= 7; //left side
                else x += 12;
                break;

            case 2://inner side of the platform
                if (x < width / 2.0) x += 12; //left side
                else x -= 7;
                break;
        }
        Coin coin = new Coin(x, 0, 1, 1);
        addElement(coin);

        return true;
    }

    public void addNewEnemy() {
        int n = numberPlatforms % 5;// mudar depois, só para facilitar o teste
        if ((n == 0 || n == 1) && enemy.isNotOnScreen()) {
            Position pos = platforms.get(platforms.size() - 1).getPosition();

            if (pos.getY() == 0) return;

            enemy.setIsNotOnScreen(false);
        }
    }

    /*--------------------Removing Elements-----------------------*/


    public void removePlatform(Platform platform) {
        platforms.remove(platform);
    }

    public void removeCoin(Coin coin) {
        coins.remove(coin);
    }

    public void removeEnemy() {
        enemy.setIsNotOnScreen(true);
    }

    /*------------------Dealing with elements movements-------------------*/

    public boolean executeFall() {
        Position position = new Position(player.getPosition().getX(), player.getPosition().getY());

        if (!player.getCanJump()) {
            position = position.addToY(1);//speed
            if(!enemy.getTouched()) enemy.setTouched(true);
            if (!movePlayerTo(position)) return false;

            isOnGround();
            return true;
        }

        return false;
    }

    public boolean playerLost() {
        if ((player.getPosition().getY() + 1 == height) && player.getHasJumped()) return true;
        else if (player.getHealth() == 0) return true;

        return false;
    }

    public boolean playerWin(){
        if(numberPlatforms < 600) return false;

        Position lastPlatPos = platforms.get(platforms.size()-1).getPosition();
        Position playerPos = player.getPosition();

        if(lastPlatPos.getY() != (playerPos.getY()+1)) return false;

        return player.getCanJump();
    }

    public boolean movePlayerTo(Position position) {

        if (canMove(position)) {
            catchedCoin(position);
            vulnerable(position);
            player.setPosition(position);
            return true;
        }
        return false;
    }

    private boolean canMove(Position position) {
        if ((position.getX() < 0) || (position.getX() + getPlayer().getWidth() > width))
            return false;

        if ((position.getY() < 0) || position.getY() >= height)
            return false;

        if (!checkCollisions(position)) {
            isOnGround();
            return true;
        }
        return false;
    }

    private boolean checkCollisionX(int minX, int maxX, int x) { //true if it collides
        int playerMaxX = x + getPlayer().getWidth();

        if (x >= minX && (playerMaxX <= maxX)) return true;
        else if (playerMaxX > minX && (playerMaxX < maxX)) return true;
        else return x > minX && (x < maxX);
    }

    private boolean checkCollisionY(int minY, int maxY, int y) { //true if it collides
        int playerMaxY = y + getPlayer().getHeight();

        if (y == minY && (playerMaxY == maxY)) return true;
        else if (playerMaxY > minY && playerMaxY < maxY) return true;
        else return y > minY && y < maxY;
    }

    private boolean checkCollisions(Position position) { //true if it collides

        boolean inPlatform = false;
        getPlayer().setCanJump(false);

        for (Platform platform : platforms) {
            int minX = platform.getPosition().getX();
            int minY = platform.getPosition().getY();
            int maxX = platform.getPosition().getX() + platform.getWidth();
            int maxY = platform.getPosition().getY() + platform.getHeight();
            int playerMaxY = player.getHeight() + position.getY();

            if (checkCollisionX(minX, maxX, position.getX()) && (minY == playerMaxY)) {
                inPlatform = true;
            }
            if (checkCollisionX(minX, maxX, position.getX()) && checkCollisionY(minY, maxY, position.getY()))
                return true;
        }
        if (inPlatform) {
            getPlayer().setCanJump(true);
            if (!getPlayer().getHasJumped())
                getPlayer().setHasJumped();
        } else {
            getPlayer().setCanJump(false);
        }
        return false;
    }

    private boolean checkCollisionElement(Position position, Element element) {//true if it collides
        int playerMaxX = position.getX() + player.getWidth();
        int playerMaxY = position.getY() + player.getHeight();

        int minX = element.getPosition().getX();
        int minY = element.getPosition().getY();
        int maxX = element.getPosition().getX() + element.getWidth();
        int maxY = element.getPosition().getY() + element.getHeight();

        if ((position.getX() <= minX && playerMaxX >= minX)) {
            if (minY == position.getY())  return true;

            if (minY <= position.getY() && maxY >= position.getY()) return true;

            return maxY <= playerMaxY && minY >= position.getY();
        }
        else if (position.getX() <= maxX && playerMaxX >= maxX) {
            if (minY == position.getY()) return true;

            if (minY <= position.getY() && maxY >= position.getY()) return true;

            return maxY <= position.getY() && minY >= position.getY();
        }
        return false;
    }


    private void catchedCoin(Position position) {//true if it collides
        for (Coin coin : coins) {
            if (checkCollisionElement(position, coin)) {
                removeCoin(coin);
                player.addScore(5);
                return;
            }
        }
    }

    private boolean collidedEnemy(Position pos) {
        return checkCollisionElement(pos, enemy);
    }

    public boolean vulnerable(Position pos) {
        if (collidedEnemy(pos) && !enemy.getTouched()) {
            enemy.setTouched(true);
            player.reduceHealth();
            return true;
        }
        return false;
    }

    private boolean isOnGround() {
        if (player.getPosition().getY() + 1 == height && !player.getHasJumped()) {
            getPlayer().setCanJump(true);
            return true;
        }
        return false;
    }

    /*--------------------Update Objects-----------------------------*/

    public void updateObjects() {
        updateScore();
        updateElements();
        updatePlayer();
    }

    private void updateScore() {
        getPlayer().addScore(1);
    }

    private void updateElements() {

        List<Element> toRemove = new ArrayList<>();

        for (Element element : getAllElements()) {
            if (element instanceof Player) continue;
            element.moveElement();

            if (element.getPosition().getY() + element.getHeight() > getHeight())
                toRemove.add(element);

        }
        for (Element element : toRemove) {
            if (element instanceof Coin) removeCoin((Coin) element);
            if (element instanceof Platform) removePlatform((Platform) element);
            if (element instanceof Enemy) removeEnemy();
        }

        addNewPlatform();
        addNewEnemy();
    }


    private void updatePlayer() {
        Position position = new Position(getPlayer().getPosition().getX(), getPlayer().getPosition().getY());
        position = position.addToY(1);
        movePlayerTo(position);
    }

    public Boolean updateEnemy() {
        if (getEnemy().isNotOnScreen()) return false;

        getEnemy().moveEnemy();

        if (getEnemy().getPosition().getX() >= getWidth())
            enemy.resetPosition();

        return true;
    }


    /*--------------------Updating Interface-------------------------*/
    public void step() throws IOException, InterruptedException {
        this.notifyObservers();
    }

    private void notifyObservers() throws IOException, InterruptedException {
        for (Observer observer : observers) observer.gameChanged();
    }
}
