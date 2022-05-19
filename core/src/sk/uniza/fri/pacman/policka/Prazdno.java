package sk.uniza.fri.pacman.policka;

import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.hra.ManazerTextur;

public class Prazdno extends Policko {
    public Prazdno(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaPrazdno(), pozicia);
    }
}
