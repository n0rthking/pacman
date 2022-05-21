package sk.uniza.fri.pacman.hra;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.policka.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Mapa {
    private final HashMap<Vector2, Policko> mapa;
    private final HashMap<Character, ITovarenNaPolicka> tovarenNaPolicka;
    private final ManazerTextur manazerTextur;
    private int maxPocetBodov;
    private final ArrayList<Vector2> pozicieTeleportov;
    private final Random generator;
    private Duch duch;

    public Mapa(ManazerTextur manazerTextur) {
        this.mapa = new HashMap<>();
        this.manazerTextur = manazerTextur;
        this.tovarenNaPolicka = new HashMap<>();
        this.tovarenNaPolicka.put('*', Stena::new);
        this.tovarenNaPolicka.put('p', Bod::new);
        this.tovarenNaPolicka.put('G', Duch::new);
        this.tovarenNaPolicka.put('t', Teleport::new);
        this.tovarenNaPolicka.put('f', Ovocie::new);
        this.tovarenNaPolicka.put(' ', Prazdno::new);
        this.pozicieTeleportov = new ArrayList<>();
        this.generator = new Random();
    }

    /**
     * Nacitanie a vytvorenie mapy zo suboru
     * @param nazovSuboru cesta k suboru
     * @throws FileNotFoundException ak nebol najdeny subor
     */
    public void nacitaj(String nazovSuboru) throws FileNotFoundException {
        File subor = new File(nazovSuboru);
        Scanner skener = new Scanner(subor);

        if (!skener.nextLine().equals("pacman")) {
            return;
        }

        int riadok = 21;
        while (skener.hasNextLine()) {
            String obsah = skener.nextLine();
            for (int stlpec = 0; stlpec < obsah.length(); stlpec++) {
                Vector2 pozicia = new Vector2(stlpec, riadok);
                this.mapa.put(pozicia, this.tovarenNaPolicka.get(obsah.charAt(stlpec)).vytvor(this.manazerTextur, pozicia));
                if (obsah.charAt(stlpec) == 'p') {
                    this.maxPocetBodov++;
                } else if (obsah.charAt(stlpec) == 't') {
                    this.pozicieTeleportov.add(pozicia.cpy());
                } else if (obsah.charAt(stlpec) == 'G') {
                    this.duch = (Duch) mapa.get(pozicia);
                }
            }
            riadok--;
        }
    }

    /**
     * Vykreslenie vsetkych policok mapy
     * @param batch batch
     */
    public void vykresliSa(SpriteBatch batch) {
        for (Policko policko : this.mapa.values()) {
            policko.vykresliSa(batch);
        }
    }

    /**
     *
     * @param pozicia suradnice
     * @return policko na zadanych suradniciach
     */
    public Policko getPolicko(Vector2 pozicia) {
        return this.mapa.get(pozicia);
    }

    /**
     *
     * @return pocet bodov v mape
     */
    public int getMaxPocetBodov() {
        return this.maxPocetBodov;
    }

    /**
     *
     * @param aktualny suradnice aktualneho teleportu
     * @return suradnice nahodne vybraneho teleportu na mape okrem aktualneho
     */
    public Vector2 getNahodnyTeleport(Vector2 aktualny) {
        ArrayList<Vector2> kopia = new ArrayList<>();
        for (Vector2 pozicia : this.pozicieTeleportov) {
            if (!aktualny.equals(pozicia)) {
                kopia.add(pozicia.cpy());
            }
        }
        return kopia.get(generator.nextInt(kopia.size()));
    }

    /**
     *
     * @return instancia ducha
     */
    public Duch getDuch() {
        return this.duch;
    }
}
