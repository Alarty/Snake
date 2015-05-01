package notrejeu;

import iut.Game;
import iut.Objet;
import iut.Sprite;
import iut.SpriteStore;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static notrejeu.Potion.getNegatif;

/**
 * Classe Serpent
 */
public class corp extends Damier implements KeyListener {


    public corp(Game g, int x, int y) {
        super(g, "corp", x, y);

    }
    /**
     * effet de l'objet sur le joueur : augmente ou descend la vitesse
     */
    public void effect(Objet aO) {
        if(Serpent.class.isInstance(aO)){
            game().dead();
        }
    }

    /**
     * Déplace l'objet
     */
    public void move(long aDt) {
    }

    /**
     * Action lorsque le joueur appuie sur une touche
     */
    public void keyPressed(KeyEvent aE) {
    }

    /**
     * dessine le sprite
     */
    /*
     * public void draw(Graphics aG) { throw new
     * UnsupportedOperationException(); }
     */
    @Override
    public boolean isFriend() {
        return false;
    }

    @Override
    public boolean isEnnemy() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

@Override
    public void draw(Graphics g) throws Exception {
            try {
                Sprite s;
                if (!getNegatif()) {
                    s = SpriteStore.get().getSprite("corp");
                } else {
                    s = SpriteStore.get().getSprite("ncorp");

                }
                s.draw(g, getLeft(), getTop());
            } catch (Exception ex) {
                Logger.getLogger(JeuSerpent.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
