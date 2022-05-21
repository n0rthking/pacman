package sk.uniza.fri.pacman.obrazovky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import sk.uniza.fri.pacman.PacmanGame;
import sk.uniza.fri.pacman.hra.Hrac;

public class GameOverObrazovka extends ScreenAdapter {
    private final BitmapFont font;
    private final SpriteBatch batch;
    private final PacmanGame pacmanGame;
    private final Hrac hrac;

    public GameOverObrazovka(PacmanGame pacmanGame, Hrac hrac) {
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.pacmanGame = pacmanGame;
        this.hrac = hrac;
    }

    /**
     * Render
     * @param delta The time in seconds since the last render.
     */
    public void render(float delta) {
        int skore = hrac.getSkore() * 10;
        ScreenUtils.clear(0.2f, 0.1f, 0.1f, 1);
        this.batch.begin();
        this.font.draw(this.batch, "Prehral si", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        this.font.draw(this.batch, "Pre spustenie novej hry stlac R", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 30);
        this.font.draw(this.batch, String.format("Dosiahol si skore %d", skore), Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 60);
        this.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            this.pacmanGame.setScreen(new HernaObrazovka(this.pacmanGame));
        }
    }

    /**
     * Dispose
     */
    public void dispose() {
        this.batch.dispose();
    }
}
