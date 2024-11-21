package View;

import Entities.Animaux;
import Entities.Chien;
import Entities.ExoException;
import Entities.Oiseau;

import java.util.Scanner;
import java.util.logging.Level;

import static Logging.MonLogger.LOGGER;

public class View {

    public static void lancementApplication() {

        Scanner scanner = new Scanner(System.in);
        Animaux animaux = new Animaux();

        boolean continuer = true;

        while (continuer) {
            // Demande à l'utilisateur quel type d'animal il souhaite ajouter
            System.out.println("Voulez-vous ajouter un Chien ou un Oiseau ? (Entrez 'Chien', 'Oiseau' ou 'Quitter' pour arrêter)");
            String choix = scanner.nextLine().toLowerCase();

            try {
                switch (choix) {

                    case "chien":

                        // Demande et lit le nom du chien
                        System.out.println("Entrez le nom du chien :");
                        String nomChien = scanner.nextLine();

                        // Demande et lit l'âge du chien, avec vérification de validité
                        System.out.println("Entrez l'âge du chien :");
                        int ageChien = Integer.parseInt(scanner.nextLine());

                        // Demande et lit l'espèce du chien
                        System.out.println("Entrez l'espèce du chien :");
                        String especeChien = scanner.nextLine();

                        System.out.println("Entrez la race du chien :");
                        String raceChien = scanner.nextLine();

                        System.out.println("Entrer la date de naissance :");
                        String dateDeNaissanceChien = scanner.nextLine();

                        // Crée un objet Chien et l'ajoute à la liste des animaux
                        Chien chien = new Chien(nomChien, raceChien,ageChien,  especeChien, dateDeNaissanceChien);
                        animaux.ajouterAnimal(chien);

                        break;

                    case "oiseau":

                        // Demande et lit le nom de l'oiseau
                        System.out.println("Entrez le nom de l'oiseau :");
                        String nomOiseau = scanner.nextLine();

                        // Demande et lit l'âge de l'oiseau, avec vérification de validité
                        System.out.println("Entrez l'âge de l'oiseau :");
                        int ageOiseau = Integer.parseInt(scanner.nextLine());

                        // Demande et lit le nombre de plumes de l'oiseau
                        System.out.println("Entrez le nombre de plumes de l'oiseau :");
                        int nombreDePlumes = Integer.parseInt(scanner.nextLine());

                        // Demande et lit l'espèce de l'oiseau
                        System.out.println("Entrez l'espèce de  l'oiseau :");
                        String especeOiseau = scanner.nextLine();

                        System.out.println("Entrer la date de naissance :");
                        String dateDeNaissanceOiseau = scanner.nextLine();

                        // Crée un objet Oiseau et l'ajoute à la liste des animaux
                        Oiseau oiseau = new Oiseau(nombreDePlumes, nomOiseau, especeOiseau, ageOiseau, dateDeNaissanceOiseau);
                        animaux.ajouterAnimal(oiseau);

                        break;

                    case "quitter":
                        // Arrête la boucle si l'utilisateur souhaite quitter
                        continuer = false;
                        break;

                    default:
                        // Gère les choix invalides de l'utilisateur
                        System.out.println("Choix invalide. Veuillez entrer 'Chien', 'Oiseau' ou 'Quitter'.");
                        break;
                }

                // Affiche la liste des animaux après chaque ajout
                if (!choix.equals("quitter")) {
                    System.out.println("\nListe des animaux ajoutés :");
                    animaux.afficherAnimaux();
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Veuillez saisir un nombre entier valide pour l'âge ou le nombre de plumes.");
            } catch (ExoException ee) {
                // Gestion des exceptions liées à la validation des attributs de l'oiseau/chien
                System.out.println("Erreur : " + ee.getMessage());
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "L'application a été quittée à cause d'une exception", e);
                System.out.println("Erreur  : " + e.getMessage());
                System.exit(1);
            }
        }

        System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
    }
}
