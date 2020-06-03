package g76.Model;

public class Player extends Element {
    private boolean canJump, hasJumped;
    private int score, health;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.canJump = true;
        this.hasJumped = false;
        this.score = 0;
        this.health = 3;
    }

    public boolean getCanJump() {
        return canJump;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public void setHasJumped() {
        this.hasJumped = true;
    }

    public boolean getHasJumped() {
        return hasJumped;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    public void addScore(int added) {
        this.score += added;
    }

    public void reduceHealth() {
        this.health--;
    }

}