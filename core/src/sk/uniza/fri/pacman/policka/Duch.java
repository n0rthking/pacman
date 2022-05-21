package sk.uniza.fri.pacman.policka;

import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.hra.Hrac;
import sk.uniza.fri.pacman.hra.ManazerTextur;
import sk.uniza.fri.pacman.hra.Mapa;
import sk.uniza.fri.pacman.hra.Smer;

import java.util.ArrayList;

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

    /**
     * Vrati opacny smer k aktualnemu smeru, napriklad hore vrati dole
     * @param smer aktualny smer
     * @return opacny smer
     */
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

    /**
     * Podla aktualnej pozicie vrati mozne smery pohybu, novy smer nemoze byt rovnaky ako predchadzajuci
     * ani smerovat do policka, ktore je prekazka
     * @param mapa instancia mapy
     * @return ArrayList aktualnych moznych smerov
     */
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

    /**
     * Z vstupnych smerov vyberie najlepsi smer k hracovi podla vzdialenosti,
     * null hodnota je vratena v pripade, ze jediny smer by bol opacny k predchadzajucemu
     * @param mozneSmery ArrayList moznych smerov
     * @param hrac       instancia hraca
     * @return najlepsi smer k hracovi, null ak taky smer neexistuje
     */
    private Smer najdiNajlepsiSmer(ArrayList<Smer> mozneSmery, Hrac hrac) {
        Smer vysledok = null;
        float minimum = -1;

        if (mozneSmery.isEmpty()) {
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

    /**
     * Pohyb ducha podla aktualnej pozicie hraca, v pripade kolizie s hracom mu znizi pocet zivotov
     * @param delta cas od posledneho vykreslenia
     * @param mapa  instancia mapy
     * @param hrac  instancia hraca
     */
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
