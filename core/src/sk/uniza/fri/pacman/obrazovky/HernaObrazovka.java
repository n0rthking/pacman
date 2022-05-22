package sk.uniza.fri.pacman.obrazovky;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import sk.uniza.fri.pacman.PacmanGame;
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
        this.duch = this.mapa.getDuch();
        this.pacmanGame = pacmanGame;
    }

    /**
     * Render
     * @param delta The time in seconds since the last render.
     */
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        this.batch.begin();
        this.duch.pohniSa(delta, this.mapa, this.hrac);
        this.mapa.vykresliSa(this.batch);
        this.hrac.pohniSa(delta, this.mapa);
        this.hrac.vykresliSa(this.batch, delta);
        this.displej.vykresliSa(this.batch, this.hrac);
        this.batch.end();

        if (this.hrac.isKoniecHry()) {
            this.zobrazGameOver();
        }

        if (this.hrac.getSkore() == this.hrac.getMaxSkore()) {
            this.zobrazVyhernuObrazovku();
        }
    }

    /**
     * Dispose
     */
    public void dispose() {
        this.batch.dispose();
    }

    /**
     * Zobrazenie game over obrazovky
     */
    public void zobrazGameOver() {
        this.pacmanGame.setScreen(new GameOverObrazovka(this.pacmanGame, this.hrac));
    }

    /**
     * Zobrazenie vyhernej obrazovky
     */
    public void zobrazVyhernuObrazovku() {
        this.pacmanGame.setScreen(new VyhernaObrazovka(this.pacmanGame));
    }
}
