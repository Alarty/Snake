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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static notrejeu.Potion.getNegatif;

/**
 * Classe Serpent
 */
public class Serpent extends Damier implements KeyListener {

    private int direction;
    private double vitesse = 0.3;
    private double vitesseAct;
    private int lastKey = KeyEvent.VK_RIGHT;
    private String imagetete = "tete1";
    private static int nbPoints = 0;

    /**
     * constructeur qui créé la tete de serpent, initialise les variables
     * @param g le jeu
     */
    public Serpent(Game g) {
        super(g, "tete1", 100, 100);
        this.direction = 2;

    }

    /**
     * Action lorsque le joueur appuie sur une touche changeant la direction
     */
    public void keyPressed(KeyEvent aE) {
        if (aE.getKeyCode() == KeyEvent.VK_LEFT && lastKey != KeyEvent.VK_RIGHT && lastKey != KeyEvent.VK_LEFT) {

            lastKey = KeyEvent.VK_LEFT;
            direction = 4;
        }
        if (aE.getKeyCode() == KeyEvent.VK_RIGHT && lastKey != KeyEvent.VK_RIGHT && lastKey != KeyEvent.VK_LEFT) {

            lastKey = KeyEvent.VK_RIGHT;
        }
        if (aE.getKeyCode() == KeyEvent.VK_UP && lastKey != KeyEvent.VK_UP && lastKey != KeyEvent.VK_DOWN) {

            lastKey = KeyEvent.VK_UP;
        }
        if (aE.getKeyCode() == KeyEvent.VK_DOWN && lastKey != KeyEvent.VK_UP && lastKey != KeyEvent.VK_DOWN) {

            lastKey = KeyEvent.VK_DOWN;
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    /**
     * Déplace l'objet selon la vitesse et la dernière touche afin qu'il aille tout droit
     */
    public void move(long aDt) {
        final int nbPixels = 5;
        int dx = nbPixels;
        int futurLeft = getLeft() + dx;
        int futurRight = getRight() + dx;
        int futurUp = getTop() + dx;
        int futurDown = getBottom() + dx;
            if (lastKey == KeyEvent.VK_RIGHT && (futurLeft >= 20 && futurRight < game().width() - 20) && futurUp >= 20 && futurDown < game().height() - 20) {
                this.moveX(dx * vitesse);
                imagetete = "tete1";

            } else if (lastKey == KeyEvent.VK_LEFT && (futurLeft >= 35 && futurRight < game().width() - 35) && futurUp >= 35 && futurDown < game().height() - 35) {
                this.moveX(-dx * vitesse);
                imagetete = "tete3";
            } else if (lastKey == KeyEvent.VK_UP && (futurLeft >= 28 && futurRight < game().width() - 28) && futurUp >= 28 && futurDown < game().height() - 28) {
                this.moveY(-dx * vitesse);
                imagetete = "tete4";
            } else if (lastKey == KeyEvent.VK_DOWN && (futurLeft >= 20 && futurRight < game().width() - 20) && futurUp >= 20 && futurDown < game().height() - 20) {
                this.moveY(dx * vitesse);
                imagetete = "tete2";
            } else {
                game().dead();
            }
    }
    /**
     * 
     * @param g
     * @throws Exception 
     */
    @Override
    public void draw(Graphics g) throws Exception {
        try {
            Sprite s;
            if (!getNegatif()) {
                s = SpriteStore.get().getSprite(imagetete);
            } else {
                s = SpriteStore.get().getSprite("n" + imagetete);

            }
            s.draw(g, getLeft(), getTop());
        } catch (Exception ex) {
            Logger.getLogger(JeuSerpent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isFriend() {
        return false;
    }

    @Override
    public boolean isEnnemy() {
        return true;
    }

    public void setV(double x) {
        vitesse += x;
    }

    public static int getScore() {
        return nbPoints;
    }

    @Override
    public void effect(Objet objet) {
        if (Potion.class.isInstance(objet)) {
            int change = 0;
            Potion p = (Potion) objet;
            while (change == 0){
            vitesseAct = vitesse;
            double v = p.bonus();
            double z = (vitesseAct + v);
            if ((( z <= 1.5) && (z >= 0.3)) || ((z <= (1-1.2)) && (z>=(1-2.5)) ) ) {
                setV(v);
                change+=1; 
            }
            }
            
            
            nbPoints += 1;

        }
        if (TeteDeMort.class.isInstance(objet)) {
            nbPoints += 3;

        }
    }
}
