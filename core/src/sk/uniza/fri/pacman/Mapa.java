package sk.uniza.fri.pacman;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Mapa {
    private final HashMap<Vector2, Policko> mapa;
    public Mapa() {
        this.mapa = new HashMap<>();
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
                if (obsah.charAt(stlpec) == '*') {
                    this.mapa.put(new Vector2(stlpec, riadok), new Stena(new Vector2(stlpec, riadok)));
                }
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
