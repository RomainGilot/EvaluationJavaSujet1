package application;

import models.Balle;
import models.Barre;
import models.Brique;
import models.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Fenetre extends Canvas implements KeyListener {

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;

    protected boolean toucheEspace = false;
    protected boolean toucheGauche = false;
    protected boolean toucheDroite = false;

    protected int vitesseRectangle = 10;

    ArrayList<Balle> listeBalles = new ArrayList<>();
    ArrayList<Sprite> listeSprites = new ArrayList<>();
    Barre barre;

    Fenetre()  throws InterruptedException {
        JFrame fenetre = new JFrame();

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);

        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR );
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);

        Container panneau = fenetre.getContentPane();
        panneau.add(this);

        fenetre.setVisible(true);
        this.createBufferStrategy(2);
        this.demarrer();
    }

    public void demarrer() throws InterruptedException {
        barre = new Barre();
        listeSprites.add(barre);

        Balle balle = new Balle(100, 200 , Color.BLACK, 30);

        listeBalles.add(balle);
        listeSprites.add(balle);

        int nombreColonnes = 6;
        int nombreLignes = 8;

        int briqueLargeur = 80;
        int briqueHauteur = 20;

        int briqueEspace = 7;


        for (int row = 0; row < nombreLignes; row++) {
            for (int col = 0; col < nombreColonnes; col++) {
                int x = col * (briqueLargeur + briqueEspace);
                int y = row * (briqueHauteur + briqueEspace);
                Color couleur = Color.GREEN;
                Brique brique = new Brique(x, y, briqueLargeur, briqueHauteur, couleur);
                listeSprites.add(brique);
            }
        }

        while(true) {

            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,0,LARGEUR,HAUTEUR);

            //----- app -----
            for(Balle uneBalle : listeBalles) {
                for (int i = 0; i < listeSprites.size(); i++) {
                    Sprite sprite = listeSprites.get(i);
                    if (sprite instanceof Brique) {
                        Brique brique = (Brique) sprite;
                        if (balle.intersectionAvec(brique)) {
                            listeSprites.remove(i);
                            balle.inverserDirectionY();
                            break;
                        }
                    }
                }

                balle.deplacement();
                balle.collisionAvec(barre);
            }

            for(Sprite s : listeSprites) {
                s.dessiner(dessin);
            }


            if(toucheEspace) {
                listeBalles.add( new Balle(200, 400 , Color.BLUE, 50));
            }

            if(toucheDroite) {
                barre.versLaDroite(vitesseRectangle);
            } else if(toucheGauche) {
                barre.versLaGauche(vitesseRectangle);
            }

            //---------------

            dessin.dispose();
            this.getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> toucheEspace = true;
            case KeyEvent.VK_LEFT -> toucheGauche = true;
            case KeyEvent.VK_RIGHT -> toucheDroite = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> toucheEspace = false;
            case KeyEvent.VK_LEFT -> toucheGauche = false;
            case KeyEvent.VK_RIGHT -> toucheDroite = false;
        }
    }

}
