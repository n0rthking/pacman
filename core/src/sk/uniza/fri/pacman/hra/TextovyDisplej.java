package sk.uniza.fri.pacman.hra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextovyDisplej {
    private final BitmapFont font;

    public TextovyDisplej() {
        this.font = new BitmapFont();
    }

    public void vykresliSa(SpriteBatch batch, Hrac hrac) {
        int sirkaMapy = 1280;
        String riadokFPS = String.format("FPS: %d", Gdx.graphics.getFramesPerSecond());
        String riadokSkore = String.format("Skore: %d", hrac.getSkore() * 10);
        String riadokZivoty = String.format("Zivoty: %d", hrac.getPocetZivotov());
        this.font.draw(batch, riadokFPS, sirkaMapy + 10, Gdx.graphics.getHeight() - 10);
        this.font.draw(batch, riadokSkore, sirkaMapy + 10, Gdx.graphics.getHeight() - 30);
        this.font.draw(batch, riadokZivoty, sirkaMapy + 10, Gdx.graphics.getHeight() - 50);
    }
}
