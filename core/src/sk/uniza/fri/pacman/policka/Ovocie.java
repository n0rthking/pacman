package sk.uniza.fri.pacman.policka;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.hra.Hrac;
import sk.uniza.fri.pacman.hra.ManazerTextur;
import sk.uniza.fri.pacman.hra.Mapa;

public class Ovocie extends Policko implements ISpecialnePolicko {
    private boolean viditelne;

    public Ovocie(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaOvocie(), pozicia);
        this.viditelne = true;
    }

    /**
     * Vykreslenie textury len v pripade, ze ovocie este nebolo zjedene
     * @param batch batch
     */
    public void vykresliSa(SpriteBatch batch) {
        if (this.viditelne) {
            super.vykresliSa(batch);
        }
    }

    /**
     * Prida 1 zivot hracovi po zjedeni ovocia
     * @param hrac instancia hraca
     * @param mapa instancia mapy
     */
    public void pouziSa(Hrac hrac, Mapa mapa) {
        if (this.viditelne) {
            hrac.zvysPocetZivotov();
            this.viditelne = false;
        }
    }
}
