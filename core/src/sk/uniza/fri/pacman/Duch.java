package sk.uniza.fri.pacman;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class Duch extends Policko {
    private float pocitadlo;
    private final ArrayList<Smer> vsetkySmery;
    private final Random generator;

    public Duch(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaDuch(), pozicia);
        this.vsetkySmery = new ArrayList<>();
        this.vsetkySmery.add(Smer.HORE);
        this.vsetkySmery.add(Smer.DOLE);
        this.vsetkySmery.add(Smer.VLAVO);
        this.vsetkySmery.add(Smer.VPRAVO);
        this.generator = new Random();
    }

    public void pohniSa(float delta, Mapa mapa, Hrac hrac) {
        if (hrac.getPozicia().equals(this.getPozicia())) {
            System.out.println("game over");
        }

        this.pocitadlo += delta;
        if (this.pocitadlo < 0.25) {
            return;
        }
        this.pocitadlo = 0;

        Smer smer = Smer.VPRAVO;
        Vector2 novaPozicia = new Vector2(this.getPozicia().cpy().add(smer.getPosunX(), smer.getPosunY()));
        if (!mapa.getPolicko(novaPozicia).jePrekazka()) {
            this.setPozicia(this.getPozicia().cpy().add(smer.getPosunX(), smer.getPosunY()));
        } else {
            ArrayList<Smer> mozneSmery = new ArrayList<>();
            for (Smer aktualnySmer : this.vsetkySmery) {
                if (!mapa.getPolicko(this.getPozicia().cpy().add(aktualnySmer.getPosunX(), aktualnySmer.getPosunY())).jePrekazka()) {
                    mozneSmery.add(aktualnySmer);
                }
            }
            smer = mozneSmery.get(generator.nextInt(mozneSmery.size()));
            this.setPozicia(this.getPozicia().cpy().add(smer.getPosunX(), smer.getPosunY()));
        }
    }
}
