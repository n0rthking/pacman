package sk.uniza.fri.pacman.obrazovky;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import sk.uniza.fri.pacman.*;
import sk.uniza.fri.pacman.hra.Hrac;
import sk.uniza.fri.pacman.hra.ManazerTextur;
import sk.uniza.fri.pacman.hra.Mapa;
import sk.uniza.fri.pacman.hra.TextovyDisplej;
import sk.uniza.fri.pacman.policka.Duch;

import java.io.FileNotFoundException;

public class HernaObrazovka extends ScreenAdapter {
    private final SpriteBatch batch;
    private final Hrac hrac;
    private final Mapa mapa;
    private final TextovyDisplej displej;
    private final Duch duch;
    private final PacmanGame pacmanGame;

    public HernaObrazovka(PacmanGame pacmanGame) {
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
        this.pacmanGame = pacmanGame;
    }

    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        this.batch.begin();
        this.duch.pohniSa(delta, this.mapa, this.hrac);
        this.mapa.vykresliSa(batch);
        this.hrac.pohniSa(delta, this.mapa);
        this.hrac.vykresliSa(batch, delta);
        this.displej.vykresliSa(batch, this.hrac);
        this.batch.end();

        if (this.hrac.isKoniecHry()) {
            this.zobrazGameOver();
        }
    }

    public void dispose() {
        this.batch.dispose();
    }

    public void zobrazGameOver() {
        this.pacmanGame.setScreen(new GameOverObrazovka(this.pacmanGame));
    }
}
