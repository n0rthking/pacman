package sk.uniza.fri.pacman;

import com.badlogic.gdx.math.Vector2;

public class Teleport extends Policko implements ISpecialnePolicko {
    public Teleport(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaTeleport(), pozicia);
    }

    public void pouziSa(Hrac hrac, Mapa mapa) {
        Vector2 novaPozicia = mapa.getNahodnyTeleport(this.getPozicia());
        hrac.setPozicia(novaPozicia.cpy());
        hrac.zmenSmerPoTeleportacii(novaPozicia);
    }
}
