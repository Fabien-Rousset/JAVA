package view;

import entities.Animaux;
import entities.Chien;
import entities.ExoException;
import entities.Oiseau;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.logging.Level;

import static logging.MonLogger.LOGGER;
import static utilities.Regex.DATE_FORMATTER;

public class View {

    public static void lancementApplication() {

        Scanner scanner = new Scanner(System.in);
        Animaux animaux = new Animaux();

        boolean continuer = true;

        while (continuer) {
            System.out.println("Voulez-vous ajouter un animal ? (Entrez 'Chien', 'Oiseau', 'Lister', ou 'Quitter' pour arrêter)");
            String choix = scanner.nextLine().toLowerCase();

            try {
                switch (choix) {

                    case "chien":
                    case "oiseau":
                        // Demande et lit le nom de l'animal
                        System.out.println("Entrez le nom de l'animal :");
                        String nomAnimal = scanner.nextLine();

                        // Demande et lit l'âge de l'animal, avec vérification de validité
                        System.out.println("Entrez l'âge de l'animal :");
                        int ageAnimal = Integer.parseInt(scanner.nextLine());

                        // Demande et lit l'espèce de l'animal
                        System.out.println("Entrez l'espèce de l'animal :");
                        String especeAnimal = scanner.nextLine();

                        System.out.println("Entrer la date de naissance :");
                        String dateDeNaissanceAnimal = scanner.nextLine();

                        LocalDate dateDeNaissanceTransforme;
                        try {
                            dateDeNaissanceTransforme = LocalDate.parse(dateDeNaissanceAnimal, DATE_FORMATTER);
                        } catch (DateTimeParseException de) {
                            throw new ExoException("Date de naissance invalide : La date doit être au format jj/MM/aaaa et être valide.");
                        }

                        // Si c'est un chien, demande les attributs spécifiques au chien
                        if (choix.equals("chien")) {
                            System.out.println("Entrez la race du chien :");
                            String raceChien = scanner.nextLine();

                            Chien chien = new Chien(nomAnimal, raceChien, ageAnimal, especeAnimal, dateDeNaissanceTransforme);
                            animaux.ajouterAnimal(chien);

                        } else if (choix.equals("oiseau")) {
                            // Si c'est un oiseau, demande les attributs spécifiques à l'oiseau
                            System.out.println("Entrez le nombre de plumes de l'oiseau :");
                            int nombreDePlumes = Integer.parseInt(scanner.nextLine());

                            Oiseau oiseau = new Oiseau(nombreDePlumes, nomAnimal, especeAnimal, ageAnimal, dateDeNaissanceTransforme);
                            animaux.ajouterAnimal(oiseau);
                        }

                        break;

                    case "lister":
                        System.out.println("\nListe des animaux ajoutés :");
                        animaux.afficherAnimaux();

                        // Downcasting et utilisation de instanceof pour afficher des informations spécifiques aux animaux
                        for (Object animal : animaux.getAnimaux()) {
                            if (animal instanceof Chien chienInstance) {
                                System.out.println("C'est un chien : " + chienInstance.getNom() + ", Race : " + chienInstance.getRace());
                            } else if (animal instanceof Oiseau oiseauInstance) {
                                System.out.println("C'est un oiseau : " + oiseauInstance.getNom() + ", Nombre de plumes : " + oiseauInstance.getNombreDePlumes());
                            }
                        }
                        break;

                    case "quitter":
                        continuer = false;
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez entrer 'Chien', 'Oiseau', 'Lister' ou 'Quitter'.");
                        break;
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Veuillez saisir un nombre entier valide pour l'âge ou le nombre de plumes.");
            } catch (ExoException ee) {
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
