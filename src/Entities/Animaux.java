package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe représentant une collection d'animaux.
 * Fournit des méthodes pour ajouter, afficher et trier les animaux.
 */
public class Animaux {

    // listeAnimaux est une ArrayList typée pour contenir uniquement des objets de type Animal.
    private final ArrayList<Animal> listeAnimaux = new ArrayList<>();

    /**
     * Comparateur pour trier les animaux par nom.
     */
    public static Comparator<Animal> comparerParNom = new Comparator<Animal>() {
        @Override
        public int compare(Animal a1, Animal a2) {
            return a1.getNom().compareTo(a2.getNom());
        }
    };

    /**
     * Comparateur pour trier les animaux par âge.
     */
    public static Comparator<Animal> comparerParAge = new Comparator<Animal>() {
        @Override
        public int compare(Animal a1, Animal a2) {
            return Integer.compare(a1.getAge(), a2.getAge());
        }
    };

    /**
     * Comparateur pour trier les animaux par nom, puis par âge en cas d'égalité de nom.
     */
    public static Comparator<Animal> comparerParNomEtAge = Comparator
            .comparing(Animal::getNom) // Premier critère : tri par nom
            .thenComparing(Animal::getAge); // Deuxième critère : tri par âge si les noms sont identiques

    /**
     * Comparateur pour trier les animaux par âge, puis par nom en cas d'égalité d'âge.
     */
    public static Comparator<Animal> comparerParAgeEtNom = Comparator
            .comparing(Animal::getAge) // Premier critère : tri par âge
            .thenComparing(Animal::getNom); // Deuxième critère : tri par nom si les âges sont identiques

    /**
     * Méthode pour ajouter un animal à la liste des animaux.
     *
     * @param animal L'animal à ajouter à la liste.
     */
    public void ajouterAnimal(Animal animal) {
        listeAnimaux.add(animal);
    }

    /**
     * Méthode pour afficher la liste des animaux.
     * Affiche chaque animal dans la console.
     */
    public void afficherAnimaux() {
        for (Animal animal : listeAnimaux) {
            System.out.println(animal);
        }
    }

    /**
     * Méthode pour trier la liste des animaux par nom.
     */
    public void trierParNom() {
        Collections.sort(listeAnimaux, comparerParNom);
    }

    /**
     * Méthode pour trier la liste des animaux par âge.
     */
    public void trierParAge() {
        Collections.sort(listeAnimaux, comparerParAge);
    }

    /**
     * Méthode pour trier la liste des animaux par nom, puis par âge.
     */
    public void trierParNomEtAge() {
        Collections.sort(listeAnimaux, comparerParNomEtAge);
    }

    /**
     * Méthode pour trier la liste des animaux par âge, puis par nom.
     */
    public void trierParAgeEtNom() {
        Collections.sort(listeAnimaux, comparerParAgeEtNom);
    }

}