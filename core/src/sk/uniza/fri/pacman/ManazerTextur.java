package sk.uniza.fri.pacman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class ManazerTextur {
    private Animation<Texture> hracHore;
    private Animation<Texture> hracDole;
    private Animation<Texture> hracVlavo;
    private Animation<Texture> hracVpravo;
    private final Texture stena;
    private final Texture bod;
    private final Texture duch;
    private final Texture prazdno;
    private final Texture teleport;
    private final Texture ovocie;

    public ManazerTextur() {
        this.inicializujTexturyHraca();
        this.stena = new Texture("stena.png");
        this.bod = new Texture("bod.png");
        this.duch = new Texture("duch.png");
        this.prazdno = new Texture("prazdno.png");
        this.teleport = new Texture("teleport.png");
        this.ovocie = new Texture("ovocie.png");
    }

    private void inicializujTexturyHraca() {
        Texture pacmanHore0 = new Texture("pacman-up-0.png");
        Texture pacmanHore1 = new Texture("pacman-up-1.png");
        Texture pacmanHore2 = new Texture("pacman-up-2.png");

        Texture pacmanDole0 = new Texture("pacman-down-0.png");
        Texture pacmanDole1 = new Texture("pacman-down-1.png");
        Texture pacmanDole2 = new Texture("pacman-down-2.png");

        Texture pacmanVlavo0 = new Texture("pacman-left-0.png");
        Texture pacmanVlavo1 = new Texture("pacman-left-1.png");
        Texture pacmanVlavo2 = new Texture("pacman-left-2.png");

        Texture pacmanVpravo0 = new Texture("pacman-right-0.png");
        Texture pacmanVpravo1 = new Texture("pacman-right-1.png");
        Texture pacmanVpravo2 = new Texture("pacman-right-2.png");

        this.hracHore = new Animation<>(0.15f, pacmanHore0, pacmanHore1, pacmanHore2);
        this.hracDole = new Animation<>(0.15f, pacmanDole0, pacmanDole1, pacmanDole2);
        this.hracVlavo = new Animation<>(0.15f, pacmanVlavo0, pacmanVlavo1, pacmanVlavo2);
        this.hracVpravo = new Animation<>(0.15f, pacmanVpravo0, pacmanVpravo1, pacmanVpravo2);

        this.hracHore.setPlayMode(Animation.PlayMode.LOOP);
        this.hracDole.setPlayMode(Animation.PlayMode.LOOP);
        this.hracVlavo.setPlayMode(Animation.PlayMode.LOOP);
        this.hracVpravo.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Animation<Texture> getAnimaciaHracaHore() {
        return this.hracHore;
    }

    public Animation<Texture> getAnimaciaHracaDole() {
        return this.hracDole;
    }

    public Animation<Texture> getAnimaciaHracaVlavo() {
        return this.hracVlavo;
    }

    public Animation<Texture> getAnimaciaHracaVpravo() {
        return this.hracVpravo;
    }

    public Texture getTexturaStena() {
        return this.stena;
    }

    public Texture getTexturaBod() {
        return this.bod;
    }

    public Texture getTexturaDuch() {
        return this.duch;
    }

    public Texture getTexturaPrazdno() {
        return this.prazdno;
    }

    public Texture getTexturaTeleport() {
        return this.teleport;
    }

    public Texture getTexturaOvocie() {
        return this.ovocie;
    }
}
