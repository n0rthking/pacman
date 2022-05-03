package sk.uniza.fri.pacman;

import com.badlogic.gdx.math.Vector2;

public class Prazdno extends Policko {
    public Prazdno(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaPrazdno(), pozicia);
    }
}
