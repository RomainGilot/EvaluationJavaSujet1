package models;

import application.Fenetre;
import java.awt.Color;

public class Barre extends Rectangle {

    protected int largeur;
    protected int hauteur;

    public Barre(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, largeur, hauteur, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Barre() {
        super(Fenetre.LARGEUR / 2 - 75, Fenetre.HAUTEUR - 100, 150, 20, Color.BLUE);
        this.largeur = 150;
        this.hauteur = 20;
    }

    public void versLaGauche(int vitesse) {
        if (x > 0) {
            x -= vitesse;
        }
    }

    public void versLaDroite(int vitesse) {
        if (x + largeur < Fenetre.LARGEUR) {
            x += vitesse;
        }
    }
}
