package sk.uniza.fri.pacman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Policko {
    private Vector2 pozicia;
    private final Texture textura;

    public Policko(Texture textura, Vector2 pozicia) {
        this.textura = textura;
        this.pozicia = pozicia;
    }

    public void vykresliSa(SpriteBatch batch) {
        batch.draw(this.textura, this.pozicia.x * 32, this.pozicia.y * 32);
    }

    public Vector2 getPozicia() {
        return this.pozicia;
    }

    public void setPozicia(Vector2 pozicia) {
        this.pozicia = pozicia;
    }

    public boolean jePrekazka() {
        return false;
    }
}
