package sk.uniza.fri.pacman.policka;

import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.hra.ManazerTextur;

public class Stena extends Policko {
    public Stena(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaStena(), pozicia);
    }

    @Override
    public boolean jePrekazka() {
        return true;
    }
}
