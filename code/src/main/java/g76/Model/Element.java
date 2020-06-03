package g76.Model;


public abstract class Element {
    private Position position;
    protected int height, width;

    public Element(int x, int y, int width, int height) {
        this.position = new Position(x, y);
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void moveElement() {
        Position pos = new Position(getPosition().getX(), getPosition().getY() + 1);
        setPosition(pos);
    }

}
