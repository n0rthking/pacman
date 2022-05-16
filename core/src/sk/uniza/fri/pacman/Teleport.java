package sk.uniza.fri.pacman;

import com.badlogic.gdx.math.Vector2;

public class Teleport extends Policko {
    public Teleport(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaTeleport(), pozicia);
    }
}
