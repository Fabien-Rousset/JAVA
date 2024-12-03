package view;

import javax.swing.*;
import java.awt.event.*;

import entities.Animal;
import entities.Chien;
import entities.Oiseau;
import utilities.AnimauxChoix;

import static entities.Animaux.listeAnimaux;

/**
 * Classe Accueil représente la fenêtre principale de l'application qui permet de choisir entre différents animaux et d'exécuter des opérations CRUD.
 * Cette classe étend JFrame et offre des options pour "Chien" et "Oiseau".
 *
 * <p>Les utilisateurs peuvent naviguer entre différents panneaux pour créer, lire, modifier et supprimer des animaux.</p>
 */
public class Accueil extends JFrame {
    // Déclaration des différents composants Swing utilisés dans la classe
    private boolean isModification = false; // Indique si l'opération en cours est une modification ou non
    private JPanel contentPane; // Le panneau principal contenant tous les éléments de l'interface graphique
    private JButton buttonCancel; // Bouton pour annuler et fermer l'application
    private JButton oiseauButton; // Bouton pour sélectionner l'option "Oiseau"
    private JButton chienButton; // Bouton pour sélectionner l'option "Chien"
    private JPanel choixAnimaux; // Panneau pour les options de choix des animaux
    private JPanel crud; // Panneau CRUD qui contient des boutons Créer, Lire, Modifier, Supprimer
    private JPanel Validation; // Panneau pour la validation des actions
    private JPanel updateDelete; // Panneau pour la mise à jour ou la suppression des animaux
    private JButton lireButton; // Bouton pour lire les informations
    private JButton creerButton; // Bouton pour créer une nouvelle entrée
    private JButton modifierButton; // Bouton pour mettre à jour une entrée existante
    private JButton annulerButton; // Bouton pour annuler une opération CRUD
    private JComboBox<String> comboBox1; // Boîte de sélection déroulante pour choisir un animal
    private JButton validerButton; // Bouton pour valider une opération
    private AnimauxChoix animauxChoix; // Enum pour stocker le type d'animal sélectionné (Chien ou Oiseau)

    /**
     * Constructeur de la classe Accueil.
     * Initialise la fenêtre, ses composants, et les listeners associés.
     */
    public Accueil() {
        initFrame(); // Initialise la fenêtre principale
        listeners(); // Ajoute les listeners pour les boutons
        afficherPanel(); // Masque les panneaux "crud" et "updateDelete" au démarrage
    }

    /**
     * Initialise l'affichage des panneaux.
     * Par défaut, les panneaux "crud" et "updateDelete" sont masqués.
     */
    private void afficherPanel() {
        crud.setVisible(false); // Masquer le panneau CRUD
        updateDelete.setVisible(false); // Masquer le panneau de mise à jour/suppression
    }

    /**
     * Initialise les propriétés de la fenêtre principale.
     * Définie le titre, la taille, et le contenu principal de la fenêtre.
     */
    private void initFrame() {
        setTitle("Accueil"); // Définit le titre de la fenêtre
        setSize(600, 400); // Définit la taille de la fenêtre
        setContentPane(contentPane); // Définit le panneau principal
    }

    /**
     * Ajoute les listeners pour les différents boutons.
     * Cela permet de définir les actions à exécuter lors des interactions de l'utilisateur avec les boutons.
     */
    private void listeners() {
        // Listener pour le bouton "Annuler" qui ferme l'application
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel(); // Ferme la fenêtre
            }
        });

        // Listener pour le bouton "Chien"
        chienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud.setVisible(true); // Affiche le panneau CRUD lorsque le bouton "Chien" est cliqué
                animauxChoix = AnimauxChoix.CHIEN; // Définit la sélection de l'animal sur "Chien"
            }
        });

        // Listener pour le bouton "Oiseau"
        oiseauButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud.setVisible(true); // Affiche le panneau CRUD lorsque le bouton "Oiseau" est cliqué
                animauxChoix = AnimauxChoix.OISEAU; // Définit la sélection de l'animal sur "Oiseau"
            }
        });

        // Listener pour le bouton "Créer"
        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MiseAJour(animauxChoix).setVisible(true); // Ouvre une nouvelle fenêtre pour créer ou mettre à jour l'animal sélectionné
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        // Listener pour le bouton "Lire"
        lireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListeAnimaux(animauxChoix).setVisible(true); // Ouvre une nouvelle fenêtre pour lire les animaux
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        // Listener pour le bouton "Modifier"
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remplissageCombo(); // Remplit la comboBox avec les animaux disponibles
                isModification = true; // Marque que l'opération en cours est une modification
                updateDelete.setVisible(true); // Affiche le panneau pour validation de la mise à jour

            }
        });

        // Listener pour le bouton "Annuler" (CRUD)
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remplissageCombo(); // Remplit la comboBox avec les animaux disponibles
                isModification = false; // Marque que l'opération en cours n'est pas une modification
                updateDelete.setVisible(true); // Affiche le panneau pour validation

            }
        });

        // Listener pour le bouton "Valider"
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBox1.getSelectedIndex(); // Obtient l'index sélectionné dans la comboBox
                if (index != -1) {
                    Animal animal = listeAnimaux.get(index); // Récupère l'animal à partir de l'index
                    new MiseAJour(animauxChoix, isModification, animal).setVisible(true); // Ouvre la fenêtre de mise à jour avec l'animal sélectionné
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un animal à modifier ou supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Listener pour la fermeture de la fenêtre par la croix (fermeture par défaut)
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel(); // Appelle la méthode pour annuler et fermer
            }
        });
    }

    /**
     * Méthode appelée lorsque l'utilisateur appuie sur le bouton Annuler ou ferme la fenêtre.
     * Ferme simplement la fenêtre sans enregistrer les modifications.
     */
    private void onCancel() {
        dispose(); // Ferme la fenêtre
    }

    /**
     * Remplit la JComboBox avec la liste des animaux disponibles, en fonction du type sélectionné (Chien ou Oiseau).
     * Cette méthode parcourt la liste des animaux et ajoute les noms à la comboBox.
     */
    private void remplissageCombo() {
        comboBox1.removeAllItems(); // Vide la comboBox avant de la remplir

        for (Animal animal : listeAnimaux) {
            // Filtre les animaux par type (chien ou oiseau)
            if ((animauxChoix == AnimauxChoix.CHIEN && animal instanceof Chien) ||
                    (animauxChoix == AnimauxChoix.OISEAU && animal instanceof Oiseau)) {
                comboBox1.addItem(animal.getNom()); // Ajoute le nom de l'animal à la comboBox
            }
        }
    }
}
