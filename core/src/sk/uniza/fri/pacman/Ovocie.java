package sk.uniza.fri.pacman;

import com.badlogic.gdx.math.Vector2;

public class Ovocie extends Policko implements ISpecialnePolicko {
    public Ovocie(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaOvocie(), pozicia);
    }

    @Override
    public void pouziSa(Hrac hrac, Mapa mapa) {
        System.out.println("zjedol si ovocie");
    }
}
