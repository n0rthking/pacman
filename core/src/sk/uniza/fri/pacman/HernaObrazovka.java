package sk.uniza.fri.pacman;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileNotFoundException;

public class HernaObrazovka extends ScreenAdapter {
    private final SpriteBatch batch;
    private final Hrac hrac;
    private final Mapa mapa;
    private ManazerTextur manazerTextur;

    public HernaObrazovka() {
        this.batch = new SpriteBatch();
        this.manazerTextur = new ManazerTextur();
        this.hrac = new Hrac(this.manazerTextur);
        this.mapa = new Mapa();
        try {
            this.mapa.nacitaj("assets/mapa.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        this.batch.begin();
        this.mapa.vykresliSa(batch);
        this.hrac.pohniSa(delta, this.mapa);
        this.hrac.vykresliSa(batch, delta);
        this.batch.end();
    }

    public void dispose() {
        this.batch.dispose();
    }
}
