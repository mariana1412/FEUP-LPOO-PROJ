package g76.Model;

public class Enemy extends Element {
    private Boolean touched, isNotOnScreen;

    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.touched = false;
        this.isNotOnScreen = true;
    }

    public void setIsNotOnScreen(Boolean notOnScreen) {
        this.isNotOnScreen = notOnScreen;
        if (isNotOnScreen) setPosition(new Position(0, 0));
    }

    public void setTouched(boolean touched) {

        this.touched = touched;
    }

    public Boolean getTouched() {
        return touched;
    }

    public void moveEnemy() {
        Position pos = new Position(getPosition().getX() + 1, getPosition().getY());
        setPosition(pos);
    }

    public Boolean isNotOnScreen() {
        return isNotOnScreen;
    }

    public void resetPosition() {
        setPosition(new Position(0, getPosition().getY()));
        touched = false;
    }
}