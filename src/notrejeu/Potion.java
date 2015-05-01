package notrejeu;

import iut.*;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import static notrejeu.Potion.getNegatif;
import static notrejeu.Serpent.getScore;

/**
 * Classe Potion
 */
public class Potion extends Damier {

    private int i = 0;
    int xPot;
    int yPot;
    private static boolean negatif = false;

    public Potion(Game g, int x, int y) {
        super(g, "potion", x, y);

    }

    /**
     * effet de l'objet sur le joueur : augmente ou descend la vitesse / inverse
     * le sens et les couleurs
     */
    public void effect(Objet aO) {
        game().remove(this);
        if (i == 0) {
            while (xPot < 25 || xPot > 960) {
                xPot = (int) (Math.random() * 1004);
            }
            while (yPot < 28 || yPot > 701) {
                yPot = (int) (Math.random() * 768);
            }
            reapparition(xPot, yPot);
            bonusP();
            i++;
        }

    }

    /**
     * permet la réapparition de l'objet supprimé
     *
     * @param x coordonnées de la nouvelle potion
     * @param y coordonnées de la nouvelle potion
     */
    public void reapparition(int x, int y) {
        Potion p = new Potion(this.game(), x, y);
        game().add(p);
    }

    /**
     * savoir si le jeu est passé en négatif pour le chargement des sprites
     *
     * @return booléen indiquant si les images sont censés êtres en négatif ou
     * pas.
     */
    public static boolean getNegatif() {
        return negatif;
    }

    /**
     * lance la musique lorsque le joueur prend l'objet.
     */
    protected void bonusP() {

 
        
        Audio a = new Audio("Potion");
        a.start();
    }

    /**
     * permet aléatoirement de choisir la vitesse lors de la prise du bonus et
     * des effets en plus (négatif de l'image)
     *
     * @return la vitesse aléatoire
     */
    public double bonus() {
        double x = 0;
        double y = 3;
        x = Math.random();
        while ((y > 2.0)) {
            y = Math.random() * 4;
        }
        if (x >= 0.5) {
            y = -y;
        }
  
        if ((getScore() % 4) == 0 && getScore() != 0){

        if ((getScore() % 4) == 0 && getScore() != 0) {

            negatif = true;
        } else {
            negatif = false;
        }

    }
        return y;
    }

    /**
     *
     * @return booléen confirmant l'effet de collision
     */
    @Override
    public boolean isFriend() {
        return false;
    }

    /**
     *
     * @return booléen confirmant l'effet de collision
     */
    @Override
    public boolean isEnnemy() {
        return true;
    }

    /**
     * ne bouge pas donc rien dans la fonction move
     *
     * @param l de combien il bouge
     */
    @Override
    public void move(long l) {
    }

    /**
     * dessine le sprite de la potion
     *
     * @param g
     * @throws Exception image
     */
    @Override
    public void draw(Graphics g) throws Exception {
        try {
            Sprite s;
            if (!getNegatif()) {
                s = SpriteStore.get().getSprite("potion");
            } else {
                s = SpriteStore.get().getSprite("npotion");

            }
            s.draw(g, getLeft(), getTop());
        } catch (Exception ex) {
            Logger.getLogger(JeuSerpent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
