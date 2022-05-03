package sk.uniza.fri.pacman;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Mapa {
    private final HashMap<Vector2, Policko> mapa;
    private final HashMap<Character, ITovarenNaPolicka> tovarenNaPolicka;
    private final ManazerTextur manazerTextur;

    public Mapa(ManazerTextur manazerTextur) {
        this.mapa = new HashMap<>();
        this.manazerTextur = manazerTextur;
        this.tovarenNaPolicka = new HashMap<>();
        this.tovarenNaPolicka.put('*', Stena::new);
        this.tovarenNaPolicka.put('p', Bod::new);
        this.tovarenNaPolicka.put('G', Duch::new);
        this.tovarenNaPolicka.put(' ', Prazdno::new);
    }

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
                // x stlpec
                // y riadok
                Vector2 pozicia = new Vector2(stlpec, riadok);
                this.mapa.put(pozicia, this.tovarenNaPolicka.get(obsah.charAt(stlpec)).vytvor(this.manazerTextur, pozicia));
            }
            riadok--;
        }
    }

    public void vykresliSa(SpriteBatch batch) {
        for (Policko policko : this.mapa.values()) {
            policko.vykresliSa(batch);
        }
    }

    public Policko getPolicko(Vector2 pozicia) {
        return this.mapa.get(pozicia);
    }
}
