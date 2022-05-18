package sk.uniza.fri.pacman;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileNotFoundException;

public class HernaObrazovka extends ScreenAdapter {
    private final SpriteBatch batch;
    private final Hrac hrac;
    private final Mapa mapa;
    private final TextovyDisplej displej;
    private final Duch duch;

    public HernaObrazovka() {
        ManazerTextur manazerTextur = new ManazerTextur();
        this.batch = new SpriteBatch();
        this.hrac = new Hrac(manazerTextur);
        this.mapa = new Mapa(manazerTextur);
        this.displej = new TextovyDisplej();
        try {
            this.mapa.nacitaj("assets/mapa.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.duch = mapa.getDuch();
    }

    public void render(float delta) {
        if (hrac.isKoniecHry()) {
            return;
        }

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        this.batch.begin();
        this.duch.pohniSa(delta, this.mapa, this.hrac);
        this.mapa.vykresliSa(batch);
        this.hrac.pohniSa(delta, this.mapa);
        this.hrac.vykresliSa(batch, delta);
        this.displej.vykresliSa(batch, this.hrac);
        this.batch.end();
    }

    public void dispose() {
        this.batch.dispose();
    }
}
