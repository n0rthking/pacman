package sk.uniza.fri.pacman.policka;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.hra.Hrac;
import sk.uniza.fri.pacman.hra.ManazerTextur;
import sk.uniza.fri.pacman.hra.Mapa;

public class Bod extends Policko implements ISpecialnePolicko {
    private boolean viditelny;

    public Bod(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaBod(), pozicia);
        this.viditelny = true;
    }

    /**
     * Vykreslenie textury iba ak bod nebol zjedeny
     * @param batch batch
     */
    public void vykresliSa(SpriteBatch batch) {
        if (this.viditelny) {
            super.vykresliSa(batch);
        }
    }

    /**
     * Zvysi skore hracovi
     * @param hrac instancia hraca
     * @param mapa instancia mapy
     */
    public void pouziSa(Hrac hrac, Mapa mapa) {
        if (this.viditelny) {
            hrac.zvysSkore();
            this.viditelny = false;
        }
    }
}
