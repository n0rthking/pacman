package sk.uniza.fri.pacman;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class Duch extends Policko {
    private float pocitadlo;
    private final ArrayList<Smer> vsetkySmery;
    private Smer predchadzajuciSmer;
    private final Vector2 pociatocnaPozicia;

    public Duch(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaDuch(), pozicia);
        this.vsetkySmery = new ArrayList<>();
        this.vsetkySmery.add(Smer.HORE);
        this.vsetkySmery.add(Smer.DOLE);
        this.vsetkySmery.add(Smer.VLAVO);
        this.vsetkySmery.add(Smer.VPRAVO);
        this.pociatocnaPozicia = pozicia.cpy();
        this.predchadzajuciSmer = null;
    }

    private Smer dajOpacnySmer(Smer smer) {
        switch(smer) {
            case HORE:
                return Smer.DOLE;
            case DOLE:
                return Smer.HORE;
            case VPRAVO:
                return Smer.VLAVO;
            case VLAVO:
                return Smer.VPRAVO;
            default:
                return null;
        }
    }

    private ArrayList<Smer> dajMozneSmery(Mapa mapa) {
        ArrayList<Smer> vysledok = new ArrayList<>();

        for (Smer aktualnySmer : this.vsetkySmery) {
            Vector2 pozicia = this.getPozicia().cpy().add(aktualnySmer.getPosunX(), aktualnySmer.getPosunY());
            if (!mapa.getPolicko(pozicia).jePrekazka()) {
                if (this.predchadzajuciSmer == null) {
                    vysledok.add(aktualnySmer);
                } else {
                    if (this.dajOpacnySmer(aktualnySmer) != this.predchadzajuciSmer) {
                        vysledok.add(aktualnySmer);
                    }
                }
            }
        }

        return vysledok;
    }

    private Smer najdiNajlepsiSmer(ArrayList<Smer> mozneSmery, Hrac hrac) {
        Smer vysledok = null;
        float minimum = -1;

        if (mozneSmery.isEmpty()) {
            System.out.println("prazdne moznosti");
            return null;
        }

        for (Smer aktualnySmer : mozneSmery) {
            Vector2 pozicia = this.getPozicia().cpy().add(aktualnySmer.getPosunX(), aktualnySmer.getPosunY());
            float rozdiel = hrac.getPozicia().cpy().sub(pozicia).len2();
            if (minimum == -1 || rozdiel < minimum) {
                minimum = rozdiel;
                vysledok = aktualnySmer;
            }
        }

        return vysledok;
    }

    public void pohniSa(float delta, Mapa mapa, Hrac hrac) {
        if (hrac.getPozicia().equals(this.getPozicia())) {
            hrac.znizPocetZivotov();
            this.setPozicia(this.pociatocnaPozicia.cpy());
            this.predchadzajuciSmer = null;
            return;
        }

        this.pocitadlo += delta;
        if (this.pocitadlo < 0.35) {
            return;
        }
        this.pocitadlo = 0;

        ArrayList<Smer> mozneSmery = this.dajMozneSmery(mapa);
        Smer vybranySmer = this.najdiNajlepsiSmer(mozneSmery, hrac);

        if (vybranySmer == null) {
            this.setPozicia(this.pociatocnaPozicia.cpy());
            this.predchadzajuciSmer = null;
            return;
        }

        this.predchadzajuciSmer = vybranySmer;
        this.setPozicia(this.getPozicia().cpy().add(vybranySmer.getPosunX(), vybranySmer.getPosunY()));
    }
}
