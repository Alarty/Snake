package notrejeu;

import iut.Audio;
import iut.Game;
import iut.Objet;
import iut.Sprite;
import iut.SpriteStore;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static notrejeu.Potion.getNegatif;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Classe Tete de Mort
 */
public class TeteDeMort extends Damier {

    private int i = 0;
    private int xTdm;
    private int yTdm;
    private int j = 0;
    private int xC;
    private int yC;

    public TeteDeMort(Game g, int x, int y) {
        super(g, "TeteMort", x, y);
    }

    /**
     * effet de l'objet sur le joueur : Bonus qui le fais grandir
     */
    public void effect(Objet aO) {
        game().remove(this);
        if (i == 0) {
            while (xTdm < 25 || xTdm > 960) {
                xTdm = (int) (Math.random() * 1004);
            }
            while (yTdm < 28 || yTdm > 701) {
                yTdm = (int) (Math.random() * 768);
            }
            reapparition(xTdm, yTdm);
            bonusT();
            i++;
        }
        if (j == 0) {
            while (xC < 25 || xC > 960) {
                xC = (int) (Math.random() * 1004);
            }
            while (yC < 28 || yC > 701) {
                yC = (int) (Math.random() * 768);
            }
            game().add(new corp(game(), xC, yC));
            j++;
        }

    }

    public int getNbTdm() {
        return 1;
    }

    public void reapparition(int x, int y) {
        TeteDeMort tdm = new TeteDeMort(this.game(), x, y);
        game().add(tdm);

    }

    /**
     * lance la musique lorsque le joueur prend l'objet.
     */
    protected void bonusT() {
      
        Audio a = new Audio("PrendreTeteDeMort");
        a.start();
    }

    @Override
    public boolean isFriend() {
        return true;
    }

    @Override
    public boolean isEnnemy() {
        return false;
    }

    @Override
    public void move(long l) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return " " + getNbTdm();
    }

    @Override
    public void draw(Graphics g) throws Exception {
            try {
                Sprite s;
                if (!getNegatif()) {
                    s = SpriteStore.get().getSprite("TeteMort");
                } else {
                    s = SpriteStore.get().getSprite("nTeteMort");

                }
                s.draw(g, getLeft(), getTop());
            } catch (Exception ex) {
                Logger.getLogger(JeuSerpent.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
