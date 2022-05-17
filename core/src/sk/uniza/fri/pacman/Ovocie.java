package sk.uniza.fri.pacman;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ovocie extends Policko implements ISpecialnePolicko {
    private boolean viditelne;

    public Ovocie(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaOvocie(), pozicia);
        this.viditelne = true;
    }

    @Override
    public void vykresliSa(SpriteBatch batch) {
        if (this.viditelne) {
            super.vykresliSa(batch);
        }
    }

    @Override
    public void pouziSa(Hrac hrac, Mapa mapa) {
        if (this.viditelne) {
            hrac.zvysPocetZivotov();
            this.viditelne = false;
        }
    }
}
