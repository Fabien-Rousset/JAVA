import entities.Animaux;
import entities.Chien;
import entities.ExoException;
import entities.Oiseau;
import view.Accueil;
import view.MiseAJour;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static entities.Animaux.listeAnimaux;
import static logging.MonLogger.LOGGER;
import static logging.MonLogger.initialiserLogger;

public class Main {
    public static void main(String[] args)  {
        //Initialisation du logger pour le programme

        try {
           initialiserLogger();
        } catch (IOException ex) {
            System.out.println("Erreur lors de l'initialisation du logger : " + ex.getMessage());
            System.exit(1);
        }
        LOGGER.log(Level.INFO, "Lancement de l'application");
//        lancementApplication();


        new Accueil().setVisible(true);

        remplissageAnimaux();




        LOGGER.log(Level.INFO, "L'application a été quittée");


                    }
    // Méthode pour remplir la liste avec 3 chiens et 3 oiseaux
    public static void remplissageAnimaux()  {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Ajout de 3 chiens
            Chien chien1 = new Chien("Max", "Labrador", 5, "chien", LocalDate.parse("12/07/2000", formatter));
            Chien chien2 = new Chien("Bella", "Caniche", 3, "chien", LocalDate.parse("09/07/1981", formatter));
            Chien chien3 = new Chien("Rocky", "Husky", 7, "chien", LocalDate.parse("25/05/2005", formatter));

            listeAnimaux.add(chien1);
            listeAnimaux.add(chien2);
            listeAnimaux.add(chien3);

            // Ajout de 3 oiseaux
            Oiseau oiseau1 = new Oiseau(1500, "Coco", "perroquet", 2, LocalDate.parse("01/07/1995", formatter));
            Oiseau oiseau2 = new Oiseau(1200, "Luna", "canari", 4, LocalDate.parse("25/12/2000", formatter));
            Oiseau oiseau3 = new Oiseau(2000, "Milo", "moineau", 1, LocalDate.parse("19/08/2015", formatter));

            listeAnimaux.add(oiseau1);
            listeAnimaux.add(oiseau2);
            listeAnimaux.add(oiseau3);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erreur lors du remplissage des animaux ");
        }
    }

}



