package sk.uniza.fri.pacman.hra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.policka.ISpecialnePolicko;

import java.util.HashMap;

public class Hrac {
    private Smer smer;
    private Vector2 pozicia;
    private float pocitadlo;
    private float pocitadloAnimacia;
    private final ManazerTextur manazerTextur;
    private Animation<Texture> aktualnaAnimacia;
    private int skore;
    private int maxSkore;
    private int pocetZivotov;
    private boolean koniecHry;

    public Hrac(ManazerTextur manazerTextur) {
        this.manazerTextur = manazerTextur;
        this.aktualnaAnimacia = this.manazerTextur.getAnimaciaHracaHore();
        this.smer = Smer.HORE;
        this.pozicia = new Vector2(1, 1);
        this.maxSkore = -1;
        this.pocetZivotov = 3;
        this.koniecHry = false;
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
        if (this.pocitadlo < 0.25) {
            return;
        }
        this.pocitadlo = 0;

        Vector2 novaPozicia = this.pozicia.cpy().add(this.smer.getPosunX(), this.smer.getPosunY());
        if (!mapa.getPolicko(novaPozicia).jePrekazka()) {
            this.pozicia = novaPozicia;
        }

        if (mapa.getPolicko(this.pozicia) instanceof ISpecialnePolicko) {
            ISpecialnePolicko policko = (ISpecialnePolicko) mapa.getPolicko(this.pozicia);
            policko.pouziSa(this, mapa);
        }

        if (this.maxSkore == -1) {
            this.maxSkore = mapa.getMaxPocetBodov();
        }
    }

    public void zmenSmerPoTeleportacii(Vector2 novaPozicia) {
        HashMap<Vector2, Smer> teleportSmery = new HashMap<>();
        teleportSmery.put(new Vector2(9, 20), Smer.DOLE);
        teleportSmery.put(new Vector2(20, 1), Smer.VLAVO);
        teleportSmery.put(new Vector2(26, 5), Smer.VPRAVO);
        this.smer = teleportSmery.get(novaPozicia);
        switch (this.smer) {
            case DOLE:
                this.aktualnaAnimacia = manazerTextur.getAnimaciaHracaDole();
                break;
            case VLAVO:
                this.aktualnaAnimacia = manazerTextur.getAnimaciaHracaVlavo();
                break;
            case VPRAVO:
                this.aktualnaAnimacia = manazerTextur.getAnimaciaHracaVpravo();
                break;
            case HORE:
                this.aktualnaAnimacia = manazerTextur.getAnimaciaHracaHore();
                break;
        }
    }

    public void vykresliSa(SpriteBatch batch, float delta) {
        this.pocitadloAnimacia += delta;
        batch.draw(this.aktualnaAnimacia.getKeyFrame(this.pocitadloAnimacia), this.pozicia.x * 32, this.pozicia.y * 32);
    }

    public Vector2 getPozicia() {
        return this.pozicia;
    }

    public void setPozicia(Vector2 pozicia) {
        this.pozicia = pozicia;
    }

    public int getSkore() {
        return this.skore;
    }

    public int getMaxSkore() {
        return this.maxSkore;
    }

    public void zvysSkore() {
        this.skore += 1;
    }

    public int getPocetZivotov() {
        return this.pocetZivotov;
    }

    public void zvysPocetZivotov() {
        this.pocetZivotov += 1;
    }

    public void znizPocetZivotov() {
        this.pocetZivotov -= 1;
        if (this.pocetZivotov == 0) {
            this.koniecHry = true;
            System.out.println("koniec hty");
        }
    }

    public boolean isKoniecHry() {
        return this.koniecHry;
    }
}
