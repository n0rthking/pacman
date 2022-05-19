package sk.uniza.fri.pacman;

import com.badlogic.gdx.Game;
import sk.uniza.fri.pacman.obrazovky.HernaObrazovka;

public class PacmanGame extends Game {
	public void create() {
		this.setScreen(new HernaObrazovka(this));
	}
}
