package g76.Model;

import java.util.Random;

public class GameLogic {
    Random objGenerator;
    int counterForNextCoin;
    long startTime;
    int timeSecs;
    int timeMillis;
    boolean lost;

    public GameLogic() {
        this.objGenerator = new Random();
        generateRandomNumber();
        this.timeSecs = 0;
        this.lost = false;
    }

    public void setStartTime() {
        this.startTime = System.nanoTime();
    }

    public boolean checkGame(Arena arena) {
        boolean changedNonStatic, changedStatic;

        calculateElapsedTime();

        changedStatic = checkStatic(arena);

        if (lost = arena.playerLost()) return false;

        changedNonStatic = checkNonStatic(arena);

        return (changedNonStatic || changedStatic);
    }

    public boolean isLost() {
        return lost;
    }

    private boolean checkNonStatic(Arena arena) {
        if (timeMillis < 50) return false;

        setStartTime();

        if (arena.getPlayer().getHasJumped()) incrementTimeSecs();

        return (arena.executeFall() || arena.updateEnemy() || arena.vulnerable(arena.getPlayer().getPosition()));
    }

    private boolean checkStatic(Arena arena) {

        if(timeSecs > 100 && (arena.getEnemy().getTouched()))
            arena.getEnemy().setTouched(false);

        if (timeSecs < arena.getSpeed()) return false;
        arena.updateObjects();
        counterManager(arena);

        this.timeSecs = 0;

        return true;
    }

    private void counterManager(Arena arena) {
        if ((this.counterForNextCoin == 0) && arena.addNewCoin())
            generateRandomNumber();
        else if (this.counterForNextCoin > 0)
            this.counterForNextCoin--;
    }

    private void calculateElapsedTime() {
        long endTime = System.nanoTime();
        int elapsedTime = (int) (endTime - startTime);

        this.timeMillis = elapsedTime / 1000000;
    }

    private void incrementTimeSecs() {
        this.timeSecs += this.timeMillis;
    }

    private void generateRandomNumber() {
        this.counterForNextCoin = objGenerator.nextInt(300) / 100 + 7;
    }
}