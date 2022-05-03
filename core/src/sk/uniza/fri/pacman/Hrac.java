package sk.uniza.fri.pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hrac implements IPostava {
    private Smer smer;
    private Vector2 pozicia;
    private float pocitadlo;
    private float pocitadlo2;
    private final ManazerTextur manazerTextur;
    private Animation<Texture> aktualnaAnimacia;
    //tmp
    private final BitmapFont font;

    public Hrac(ManazerTextur manazerTextur) {
        this.manazerTextur = manazerTextur;
        this.aktualnaAnimacia = this.manazerTextur.getAnimaciaHracaHore();
        this.smer = Smer.HORE;
        this.pozicia = new Vector2(1, 1);
        // tmp
        this.font = new BitmapFont();
    }

    public void pohniSa(float delta, Mapa mapa) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.smer = Smer.HORE;
            this.aktualnaAnimacia = this.manazerTextur.getAnimaciaHracaHore();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.smer = Smer.DOLE;
            this.aktualnaAnimacia = this.manazerTextur.getAnimaciaHracaDole();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.smer = Smer.VLAVO;
            this.aktualnaAnimacia = this.manazerTextur.getAnimaciaHracaVlavo();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.smer = Smer.VPRAVO;
            this.aktualnaAnimacia = this.manazerTextur.getAnimaciaHracaVpravo();
        }

        this.pocitadlo += delta;
        if (this.pocitadlo < 0.3) {
            return;
        }
        this.pocitadlo = 0;

        Vector2 novaPozicia = new Vector2(this.pozicia.x + this.smer.getPosunX(), this.pozicia.y + this.smer.getPosunY());
        if (mapa.getPolicko(novaPozicia) == null) {
            this.pozicia = novaPozicia;
        }
    }

    public void vykresliSa(SpriteBatch batch, float delta) {
        this.pocitadlo2 += delta;
        batch.draw(this.aktualnaAnimacia.getKeyFrame(this.pocitadlo2), this.pozicia.x * 32, this.pozicia.y * 32);
        // tmp
        this.font.setColor(Color.WHITE);
        this.font.draw(batch, String.format("%d", Gdx.graphics.getFramesPerSecond()), 50, Gdx.graphics.getHeight() - 50);
    }

    public Vector2 getPozicia() {
        return this.pozicia;
    }
}
