package sk.uniza.fri.pacman.hra;

public enum Smer {
    HORE(0, 1),
    DOLE(0, -1),
    VPRAVO(1, 0),
    VLAVO(-1, 0);

    private final int posunX;
    private final int posunY;

    Smer(int posunX, int posunY) {
        this.posunX = posunX;
        this.posunY = posunY;
    }

    /**
     *
     * @return posun v smere x
     */
    public int getPosunX() {
        return this.posunX;
    }

    /**
     *
     * @return posun v smere y
     */
    public int getPosunY() {
        return this.posunY;
    }
}
