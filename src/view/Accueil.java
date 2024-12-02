package view;

import javax.swing.*;
import java.awt.event.*;

import utilities.AnimauxChoix;

/**
 * Classe Accueil représente la fenêtre principale de l'application qui permet de choisir entre différents animaux et exécuter des opérations CRUD.
 * Cette classe étend JFrame et offre des options pour "Chien" et "Oiseau".
 *
 * <p>Les utilisateurs peuvent naviguer entre différents panneaux en utilisant un "CardLayout".</p>
 */
public class Accueil extends JFrame {
    // Déclaration des différents composants Swing utilisés dans la classe
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
    private JComboBox comboBox1; // Boîte de sélection déroulante pour diverses options (usage non précisé)
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
     * Définie le titre, la taille et le contenu principal de la fenêtre.
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
            public void actionPerformed(ActionEvent e) {
                crud.setVisible(true); // Affiche le panneau CRUD lorsque le bouton "Chien" est cliqué
                animauxChoix = AnimauxChoix.CHIEN; // Définit la sélection de l'animal sur "Chien"

            }
        });

        // Listener pour le bouton "Oiseau"
        oiseauButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crud.setVisible(true); // Affiche le panneau CRUD lorsque le bouton "Oiseau" est cliqué
                animauxChoix = AnimauxChoix.OISEAU; // Définit la sélection de l'animal sur "Oiseau"
            }
        });

        // Listener pour le bouton "Créer"
        creerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MiseAJour(animauxChoix).setVisible(true); // Ouvre une nouvelle fenêtre pour mettre à jour l'animal sélectionné
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
     * Méthode appelée lorsque l'utilisateur appuie sur le bouton OK (non utilisée ici).
     * Ferme simplement la fenêtre.
     */
    private void onOK() {
        dispose(); // Ferme la fenêtre
    }

    /**
     * Méthode appelée lorsque l'utilisateur appuie sur le bouton Annuler ou ferme la fenêtre.
     * Ferme simplement la fenêtre.
     */
    private void onCancel() {
        dispose(); // Ferme la fenêtre
    }
}
