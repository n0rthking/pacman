package sk.uniza.fri.pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverObrazovka extends ScreenAdapter {
    private final BitmapFont font;
    private final SpriteBatch batch;
    private final PacmanGame pacmanGame;

    public GameOverObrazovka(PacmanGame pacmanGame) {
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.pacmanGame = pacmanGame;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.1f, 0.1f, 1);
        this.batch.begin();
        this.font.draw(this.batch, "Prehral si", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        this.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            this.pacmanGame.setScreen(new HernaObrazovka(this.pacmanGame));
        }
    }
}
