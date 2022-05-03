package sk.uniza.fri.pacman;

import com.badlogic.gdx.Game;

public class PacmanGame extends Game {
	public void create() {
		this.setScreen(new HernaObrazovka());
	}
}
