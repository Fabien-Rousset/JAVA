package view;

import entities.Animal;
import entities.Chien;
import entities.Oiseau;
import utilities.AnimauxChoix;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import static entities.Animaux.listeAnimaux;
import static utilities.Regex.DATE_FORMATTER;

/**
 * Classe ListeAnimaux
 * Cette classe représente une interface graphique permettant d'afficher une liste d'animaux
 * (chiens ou oiseaux) dans une JTable. Elle permet de filtrer les animaux en fonction de leur type.
 */
public class ListeAnimaux extends JFrame {
    private JPanel contentPane; // Panneau principal contenant tous les composants
    private JTable table1; // JTable pour afficher la liste des animaux
    private JButton quitterButton;
    private JButton retourAccueilButton;
    private JButton buttonOK; // Bouton OK pour valider ou quitter
    private JButton buttonCancel; // Bouton Cancel pour annuler ou quitter

    /**
     * Constructeur de la classe ListeAnimaux.
     * Initialise la fenêtre et configure ses composants.
     *
     */
    public ListeAnimaux(AnimauxChoix animauxChoix) {
        getRootPane().setDefaultButton(buttonOK); // Définit le bouton OK comme bouton par défaut
        initFrame(); // Initialise les paramètres de la fenêtre
        listeners(); // Ajoute les écouteurs d'événements
        remplissageJTable(animauxChoix);



    }

    /**
     * Initialise les paramètres de la fenêtre principale.
     */
    private void initFrame() {
        setContentPane(contentPane); // Définit le contenu principal
        setTitle("Liste Animaux"); // Définit le titre de la fenêtre
        setSize(600, 400); // Définit la taille de la fenêtre
        setVisible(false); // La fenêtre est invisible par défaut


    }

    /**
     * Configure les écouteurs d'événements pour les interactions utilisateur.
     */
    private void listeners() {
        // Appelle onCancel() lorsque la croix de fermeture est cliquée
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });



        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        retourAccueilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Accueil().setVisible(true);
                dispose();

            }
        });

        // Appelle onCancel() lorsque la touche ÉCHAP est pressée
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Remplit la JTable avec la liste d'animaux, en fonction du type d'animaux sélectionné.
     *
     * @param animauxChoix Type d'animaux à afficher (chiens ou oiseaux).
     */
    private void remplissageJTable(AnimauxChoix animauxChoix) {
        String[] entetes; // Titres des colonnes
        DefaultTableModel tableModel; // Modèle de données pour la JTable

        // Vérifie le type d'animaux sélectionné
        if (animauxChoix == AnimauxChoix.CHIEN) {
            setTitle("Liste Animaux - chien");
            entetes = new String[]{"Nom", "Race", "Age", "Espece", "Date de Naissance"};
            tableModel = new DefaultTableModel(entetes, 0);
            tableModel.addRow(entetes);

            // Remplit la table avec les chiens
            for (Animal animal : listeAnimaux) {
                if (animal instanceof Chien chien) {
                    tableModel.addRow(new Object[]{
                            chien.getNom(),
                            chien.getRace(),
                            chien.getAge(),
                            chien.getEspece(),
                            chien.getDateDeNaissance().format(DATE_FORMATTER)
                    });
                }
            }
        } else if (animauxChoix == AnimauxChoix.OISEAU) {
            setTitle("Liste Animaux - oiseau");
            entetes = new String[]{"Nombre de Plumes", "Nom", "Espece", "Age", "Date de Naissance"};
            tableModel = new DefaultTableModel(entetes, 0);
            tableModel.addRow(entetes);

            // Remplit la table avec les oiseaux
            for (Animal animal : listeAnimaux) {
                if (animal instanceof Oiseau oiseau) {
                    tableModel.addRow(new Object[]{
                            oiseau.getNombreDePlumes(),
                            oiseau.getNom(),
                            oiseau.getEspece(),
                            oiseau.getAge(),
                            oiseau.getDateDeNaissance().format(DATE_FORMATTER)
                    });
                }
            }
        } else {
            throw new IllegalArgumentException("Une erreur est survenue : Type d'animal inconnu");
        }

        // Associe le modèle de données à la JTable
        table1.setModel(tableModel);
    }

    /**
     * Action effectuée lors du clic sur le bouton OK.
     * Ferme la fenêtre.
     */
    private void onOK() {
        dispose(); // Ferme la fenêtre
    }

    /**
     * Action effectuée lors du clic sur le bouton Cancel ou de la fermeture de la fenêtre.
     * Permet d'ajouter un comportement spécifique avant de fermer la fenêtre.
     */
    private void onCancel() {
        dispose(); // Ferme la fenêtre
    }


}
