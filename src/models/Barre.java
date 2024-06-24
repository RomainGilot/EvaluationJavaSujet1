package models;

import application.Fenetre;

import java.awt.*;

public class Barre extends Sprite{

    protected int largeur;
    protected int hauteur;

    public Barre(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Barre() {
        super(Fenetre.LARGEUR / 2 - 75, Fenetre.HAUTEUR - 100, Color.BLUE);
        this.largeur = 150;
        this.hauteur = 20;
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x,y,largeur,hauteur);
    }
}
