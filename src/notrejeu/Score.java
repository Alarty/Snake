/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notrejeu;

import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static notrejeu.Potion.getNegatif;
import static notrejeu.Serpent.getScore;

/**
 * Indique visuellement le score du joueur
 */
public class Score extends Damier {

    private Game j;
    Serpent ser = new Serpent(j);

    private Font font;
    /**
     * constructeur qui créé le score, et définie la police
     * @param g le jeu
     */
    public Score(Game g) {
        super(g, "", 0, 0);
        font = new Font("Arial", Font.PLAIN, 20);
        this.j = g;
    }

    /**
     * Teste la collision entre deux objets
     *
     * @return true si la collision a eu lieu
     */
    public boolean collision(Objet o) {
        return false;
    }

    /**
     * Action : effet d'une collision entre l'objet et le paramètre
     */
    public void effect(Objet o) {

    }

    /**
     * @return true si l'objet est un "ami" du joueur
     */
    public boolean isFriend() {
        return false;
    }

    /**
     * @return false si l'objet est un "ennemi" du joueur
     */
    public boolean isEnnemy() {
        return true; //c'est moche mais ça évite de freeze quand y'a plus de SC
    }

    /**
     * Déplace l'objet
     *
     * @param dt le temps écoulé en millisecondes depuis le précédent
     * déplacement
     */
    public void move(long dt) {

    }

    public void draw(Graphics g) throws Exception {
        String s = "Score : " + getScore() / 2;
        g.setFont(font);
        if (!getNegatif()) {

            g.setColor(Color.decode("0xB02020"));
        } else {
            g.setColor(Color.decode("0x4FDFDF"));
        }

        g.drawString(s, 25, 22);
    }
}
