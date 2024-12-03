package view;

import entities.*;
import utilities.AnimauxChoix;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;

import static logging.MonLogger.LOGGER;

/**
 * Classe MiseAJour représente une fenêtre de mise à jour des informations sur un animal.
 * Cette classe permet de saisir et modifier des informations concernant un animal, que ce soit un chien ou un oiseau.
 *
 * <p>Elle offre des champs pour renseigner des informations spécifiques en fonction du type d'animal sélectionné.</p>
 */
public class MiseAJour extends JFrame {
    private boolean isModification = false; // Indique si la fenêtre est en mode modification ou création
    private Animal animal = null; // Référence de l'animal à modifier, s'il y en a un
    // Déclaration des différents composants Swing utilisés dans la classe
    private JPanel contentPane; // Panneau principal de la fenêtre
    private JButton buttonOK; // Bouton pour confirmer la mise à jour
    private JButton buttonCancel; // Bouton pour annuler la mise à jour
    private JTextField nomAnimalTextField; // Champ de texte pour le nom de l'animal
    private JTextField especAnimalTextField; // Champ de texte pour l'espèce de l'animal
    private JTextField ageAnimalTextField; // Champ de texte pour l'âge de l'animal
    private JTextField dateDeNaissanceAnimalTextField; // Champ de texte pour la date de naissance de l'animal
    private JTextField raceChienTextField; // Champ de texte pour la race du chien (utilisé si l'animal est un chien)
    private JTextField nombreDePlumesOiseauxTextField; // Champ de texte pour le nombre de plumes (utilisé si l'animal est un oiseau)
    private JLabel labelPLumes; // Label pour indiquer le champ des plumes (visible seulement pour un oiseau)
    private JLabel labelRace; // Label pour indiquer le champ de la race (visible seulement pour un chien)

    private AnimauxChoix animauxChoix; // Enum qui stocke le type d'animal sélectionné (Chien ou Oiseau)

    /**
     * Constructeur de la classe MiseAJour.
     * Initialise la fenêtre de mise à jour et les listeners des différents boutons.
     *
     * @param animauxChoix Le type d'animal (Chien ou Oiseau) sélectionné pour la mise à jour.
     */
    public MiseAJour(AnimauxChoix animauxChoix) {
        this.animauxChoix = animauxChoix; // Stocke le type d'animal sélectionné pour la mise à jour
        getRootPane().setDefaultButton(buttonOK); // Définit le bouton OK comme le bouton par défaut quand "Entrée" est pressée
        initFrame(); // Initialise la fenêtre et ses composants
        listeners(); // Ajoute les listeners pour les boutons
    }

    /**
     * Deuxième constructeur de la classe MiseAJour.
     * Permet d'initialiser la fenêtre en mode modification avec les informations de l'animal existant.
     *
     * @param animauxChoix Le type d'animal (Chien ou Oiseau) à mettre à jour.
     * @param isModification True si c'est pour une modification, false pour une création.
     * @param animal L'animal à modifier (peut être null si c'est une création).
     */
    public MiseAJour(AnimauxChoix animauxChoix, boolean isModification, Animal animal) {
        this.animauxChoix = animauxChoix;
        this.isModification = isModification;
        this.animal = animal;

        getRootPane().setDefaultButton(buttonOK); // Définit le bouton OK comme le bouton par défaut quand "Entrée" est pressée
        initFrame(); // Initialise la fenêtre et ses composants
        listeners(); // Ajoute les listeners pour les boutons

        if (isModification && animal != null) {
            preRemplirChamps(); // Pré-remplit les champs de texte avec les informations de l'animal
        }
    }

    /**
     * Initialise les propriétés de la fenêtre de mise à jour.
     * Définit le titre, la taille, le contenu principal de la fenêtre et masque les champs inutilisés.
     */
    private void initFrame() {
        setTitle("MiseAJour"); // Définit le titre de la fenêtre
        setContentPane(contentPane); // Définit le panneau principal
        setSize(600, 400); // Définit la taille de la fenêtre
        setVisible(false); // La fenêtre n'est pas visible par défaut
        if (!isModification) {
            preRemplirChampsNonEditable();
        }

        // Masquer les champs et labels en fonction de l'animal sélectionné
        if (animauxChoix == AnimauxChoix.CHIEN) {
            labelPLumes.setVisible(false); // Masquer le label des plumes
            nombreDePlumesOiseauxTextField.setVisible(false); // Masquer le champ de texte pour le nombre de plumes
        } else if (animauxChoix == AnimauxChoix.OISEAU) {
            labelRace.setVisible(false); // Masquer le label de la race
            raceChienTextField.setVisible(false); // Masquer le champ de texte pour la race du chien
        }
    }

    /**
     * Pré-remplit les champs de saisie avec les informations de l'animal si une modification est en cours.
     * Cela facilite la mise à jour en affichant les informations existantes.
     */
    public void preRemplirChamps() {
        // Remplir les champs de saisie avec les informations de l'animal existant
        nomAnimalTextField.setText(animal.getNom());
        especAnimalTextField.setText(animal.getEspece());
        ageAnimalTextField.setText(String.valueOf(animal.getAge()));
        dateDeNaissanceAnimalTextField.setText(animal.getDateDeNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Cacher ou afficher les champs spécifiques selon le type d'animal
        if (animal instanceof Chien) {
            raceChienTextField.setText(((Chien) animal).getRace());
            labelPLumes.setVisible(false);
            nombreDePlumesOiseauxTextField.setVisible(false);
        } else if (animal instanceof Oiseau) {
            nombreDePlumesOiseauxTextField.setText(String.valueOf(((Oiseau) animal).getNombreDePlumes()));
            labelRace.setVisible(false);
            raceChienTextField.setVisible(false);
        }
    }

    public void preRemplirChampsNonEditable() {
        // Remplir les champs de saisie avec les informations de l'animal existant
        nomAnimalTextField.setText(animal.getNom());
        especAnimalTextField.setText(animal.getEspece());
        ageAnimalTextField.setText(String.valueOf(animal.getAge()));
        dateDeNaissanceAnimalTextField.setText(animal.getDateDeNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        nomAnimalTextField.setEditable(false);
        especAnimalTextField.setEditable(false);
        ageAnimalTextField.setEditable(false);
        dateDeNaissanceAnimalTextField.setEditable(false);

        // Cacher ou afficher les champs spécifiques selon le type d'animal
        if (animal instanceof Chien) {
            raceChienTextField.setText(((Chien) animal).getRace());
            labelPLumes.setVisible(false);
            nombreDePlumesOiseauxTextField.setVisible(false);
        } else if (animal instanceof Oiseau) {
            nombreDePlumesOiseauxTextField.setText(String.valueOf(((Oiseau) animal).getNombreDePlumes()));
            labelRace.setVisible(false);
            raceChienTextField.setVisible(false);
        }

        raceChienTextField.setEditable(false);
        nombreDePlumesOiseauxTextField.setEditable(false);
    }

    /**
     * Ajoute les listeners pour les différents boutons et les actions clavier.
     * Cela permet de définir les actions à exécuter lors des interactions de l'utilisateur.
     */
    private void listeners() {
        // Listener pour le bouton "OK" qui confirme la mise à jour
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(); // Exécute la méthode onOK() quand le bouton OK est cliqué
            }
        });

        // Listener pour le bouton "Annuler" qui ferme la fenêtre
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel(); // Exécute la méthode onCancel() quand le bouton Annuler est cliqué
            }
        });

        // Listener pour annuler avec la touche Échap (ESCAPE)
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel(); // Exécute la méthode onCancel() quand la touche Échap est pressée
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Méthode appelée lorsque l'utilisateur appuie sur le bouton OK.
     * Permet de valider les modifications ou la création et de fermer la fenêtre.
     * Cette méthode récupère les valeurs des champs de texte et crée ou met à jour un objet Chien ou Oiseau en fonction du type sélectionné.
     */
    private void onOK() {
        try {
            // Récupérer les valeurs des champs de texte
            String nom = nomAnimalTextField.getText();
            String espece = especAnimalTextField.getText();
            int age = Integer.parseInt(ageAnimalTextField.getText());

            // Formatter pour le format de date attendu
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateDeNaissance = LocalDate.parse(dateDeNaissanceAnimalTextField.getText(), formatter);

            // Vérifier le type d'animal et créer ou mettre à jour un objet correspondant
            if (animauxChoix == AnimauxChoix.CHIEN) {
                String race = raceChienTextField.getText();
                Chien chien = new Chien(nom, race, age, espece, dateDeNaissance);
                Animaux.listeAnimaux.add(chien); // Ajoute l'animal à la liste d'animaux
                JOptionPane.showMessageDialog(null, "Le chien a bien été créé ou mis à jour ou supprimer.");
            } else if (animauxChoix == AnimauxChoix.OISEAU) {
                int nombreDePlumes = Integer.parseInt(nombreDePlumesOiseauxTextField.getText());
                Oiseau oiseau = new Oiseau(nombreDePlumes, nom, espece, age, dateDeNaissance);
                Animaux.listeAnimaux.add(oiseau); // Ajoute l'animal à la liste d'animaux
                JOptionPane.showMessageDialog(null, "L'oiseau a bien été créé ou mis à jour ou supprimer.");
            }
        } catch (DateTimeParseException de) {
            LOGGER.log(Level.WARNING, "Erreur de parsing de la date", de);
            JOptionPane.showMessageDialog(this, "Date de naissance invalide : La date doit être au format jj/MM/aaaa et être valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException nfe) {
            LOGGER.log(Level.WARNING, "Erreur de parsing d'un nombre", nfe);
            JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre entier valide pour l'âge ou le nombre de plumes.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (ExoException ee) {
            LOGGER.log(Level.WARNING, "Erreur spécifique de l'application", ee);
            JOptionPane.showMessageDialog(this, "Erreur : " + ee.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "L'application a été quittée à cause d'une exception", e);
            JOptionPane.showMessageDialog(this, "Une erreur inattendue s'est produite : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Méthode appelée lorsque l'utilisateur appuie sur le bouton Annuler ou la touche Échap.
     * Ferme simplement la fenêtre sans enregistrer de modifications.
     */
    private void onCancel() {
        dispose(); // Ferme la fenêtre après annulation
    }
}
