package sk.uniza.fri.pacman;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bod extends Policko {
    private boolean viditelny;

    public Bod(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaBod(), pozicia);
        this.viditelny = true;
    }

    public boolean jeViditelny() {
        return this.viditelny;
    }

    public void zneviditelni() {
        this.viditelny = false;
    }

    @Override
    public void vykresliSa(SpriteBatch batch) {
        if (this.viditelny) {
            super.vykresliSa(batch);
        }
    }
}
