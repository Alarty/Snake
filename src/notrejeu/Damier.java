
package notrejeu;

import iut.Game;
import iut.Objet;
import iut.ObjetTouchable;

/**
 *
 * @author Aurélien LAVIRON
 */
public class Damier extends ObjetTouchable {

    
    /**
    * 
    * @param g le jeu
    * @param nom le nom du damier
    * @param x sa position en x
    * @param y sa position en y
    */
    public Damier(Game g, String nom, int x, int y) {
        super(g, nom, x, y);
    }
    /**
     * 
     * @param objet l'objet qui touche déclenchant l'effect
     * 
     * il n'y a pas d'effect avec le damier
     * 
     */
    @Override
    public void effect(Objet objet) {
       
    }
    /**
     * 
     * @return vrai car il ne peut pas se collisionner
     * 
     */
    @Override
    public boolean isFriend() {
        return true;
    }
    /**
     * 
     * @return faux car il ne peut pas se collisionner
     * 
     */
    @Override
    public boolean isEnnemy() {
        return false;
    }
    /**
     * 
     * @param l de combien il bouge
     */
    @Override
    public void move(long l) {
        
    }
}