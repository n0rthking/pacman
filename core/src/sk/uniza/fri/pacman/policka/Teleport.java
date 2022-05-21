package sk.uniza.fri.pacman.policka;

import com.badlogic.gdx.math.Vector2;
import sk.uniza.fri.pacman.hra.Hrac;
import sk.uniza.fri.pacman.hra.ManazerTextur;
import sk.uniza.fri.pacman.hra.Mapa;

public class Teleport extends Policko implements ISpecialnePolicko {
    public Teleport(ManazerTextur manazerTextur, Vector2 pozicia) {
        super(manazerTextur.getTexturaTeleport(), pozicia);
    }

    /**
     * Po pouziti teleportu sa hracovi zmenia suradnice na iny teleport na mape
     * @param hrac instancia hraca
     * @param mapa instancia mapy
     */
    public void pouziSa(Hrac hrac, Mapa mapa) {
        Vector2 novaPozicia = mapa.getNahodnyTeleport(this.getPozicia());
        hrac.setPozicia(novaPozicia.cpy());
        hrac.zmenSmerPoTeleportacii(novaPozicia);
    }
}
