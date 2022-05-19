package sk.uniza.fri.pacman.policka;

import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.hra.ManazerTextur;

public interface ITovarenNaPolicka {
    Policko vytvor(ManazerTextur manazerTextur, Vector2 pozicia);
}
