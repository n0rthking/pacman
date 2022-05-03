package sk.uniza.fri.pacman;

import com.badlogic.gdx.math.Vector2;

public class Stena extends Policko {
    public Stena(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaStena(), pozicia);
    }

    @Override
    public boolean jePrekazka() {
        return true;
    }
}
