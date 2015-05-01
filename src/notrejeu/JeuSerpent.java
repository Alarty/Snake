package notrejeu;

import iut.Audio;
import iut.Game;
import iut.Sprite;
import iut.SpriteStore;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static notrejeu.Potion.getNegatif;
import static notrejeu.Serpent.getScore;

/**
 * Jeu
 *
 * @author Aurélien Laviron
 *
 */
public class JeuSerpent extends Game {

    /**
     * Le constructeur qui créé le jeu, lance la musique et écrit le message à
     * l'écran.
     */
    public JeuSerpent() {
        super(1024, 768, "Snake");
        musique();
        JOptionPane.showMessageDialog(null, "Bonjour et bienvenue sur notre variante du Snake !\n La tête de mort fait apparaître des obstacles à éviter\n La potion augmente, ralentie ou change le sens du serpent que vous contrôlez\n Bon jeu ! ");
    }

    /**
     * créé tous les objets du jeu. Appelé en début de partie.
     */
    public void createObjects() {
        int xPot = 0;
        int yPot = 0;
        int xTdm = 0;
        int yTdm = 0;
        Damier d = new Damier(this, "Damier", 0, 0);
        Serpent joue = new Serpent(this);
        this.add(joue);
        this.addKeyInteractiveObject(joue);
        while (xPot < 25 || xPot > 960) {
            xPot = (int) (Math.random() * 1004);
        }
        while (yPot < 28 || yPot > 701) {
            yPot = (int) (Math.random() * 768);
        }
        Potion pot = new Potion(this, xPot, yPot);
        this.add(pot);
        while (xTdm < 25 || xTdm > 960) {
            xTdm = (int) (Math.random() * 1004);
        }
        while (yTdm < 28 || yTdm > 701) {
            yTdm = (int) (Math.random() * 768);
        }
        TeteDeMort tdm = new TeteDeMort(this, xTdm, yTdm);
        this.add(tdm);
        Score s = new Score(this);
        this.add(s);
    }

    /**
     * Dessine le fond d'écran
     */
    public void drawBackground(Graphics aG) {
        try {
            Sprite s;
            if (getNegatif()) {
                s = SpriteStore.get().getSprite("nFond");
            } else {
                s = SpriteStore.get().getSprite("Fond");
            }
            s.draw(aG, 0, 0);
        } catch (Exception ex) {
            Logger.getLogger(JeuSerpent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /**
     * Ce qu'il se passe lorsque le joueur perd
     */
    public void perdu() {
        Audio a = new Audio("GameOver");
        a.start();

        String message = "Vous avez perdu, votre score est de " + getScore() / 2 + "\n\nVoulez-vous rejouer à ce jeu ?";
        String title = "REJOUER ?";
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {

            JeuSerpent jeu = new JeuSerpent();
            jeu.run();
        }
    }

    /**
     * lance la musique de fond
     */
    protected void musique() {

        Audio a = new Audio("Serpent");
        a.start();

    }

    /**
     * Ce qu'il se passe lorsque le joueur gagne, rien car c'est impossible de
     * gagner au snake
     */
    @Override
    protected void gagne() {
    }
}
