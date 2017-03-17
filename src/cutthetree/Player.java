package cutthetree;

public class Player {
    private int xPos;
    private int yPos;

    private Lumberaxe axe;

    public Player(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void move(int dx, int dy) {
        xPos += dx;
        yPos += dy;
    }

    public void grabLumberaxe(Lumberaxe axe) {
        this.axe = axe;
    }

    public Lumberaxe getAxe() {
        return this.axe;
    }
}
