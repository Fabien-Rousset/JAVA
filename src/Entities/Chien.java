package Entities;

/**
 * Classe représentant un chien, qui est un type d'animal.
 * Chaque chien a une race, un nom, un âge et une espèce.
 */
public class Chien extends Animal {

    private String race;

    /**
     * Constructeur de la classe Chien.
     * Initialise la race, le nom, l'âge, et l'espèce du chien.
     *
     * @param race   La race du chien (doit contenir au moins 3 caractères).
     * @param nom    Le nom du chien (doit commencer par une majuscule et ne pas être vide).
     * @param age    L'âge du chien (doit être positif).
     * @param espece L'espèce du chien (ne peut pas être vide).
     * @throws ExoException Si les valeurs fournies ne respectent pas les contraintes définies.
     */
    public Chien(String nom, String race,  int age, String espece, String dateDeNaissance) throws ExoException {
        super(nom, age, espece, dateDeNaissance);  // Appel au constructeur de la classe parente Animal
        setRace(race);            // Initialisation de la race avec validation
    }

    /**
     * Getter pour obtenir la race du chien.
     *
     * @return La race du chien.
     */
    public String getRace() {
        return race;
    }



    /**
     * Setter pour définir la race du chien.
     *
     * @param race La race du chien (doit contenir au moins 3 caractères).
     * @throws ExoException Si la race contient moins de 3 caractères.
     */
    public void setRace(String race) throws ExoException {
        if (race.length() < 3) {
            throw new ExoException("La race doit contenir un minimum de 3 caractères");
        }
        this.race = race;
    }

    /**
     * Méthode pour décrire comment le chien dort.
     * Affiche un message indiquant que le chien dort dans une niche.
     */
    @Override
    public void dormir() {
        System.out.println("Je dors dans une niche");
    }

    /**
     * Méthode pour faire aboyer le chien.
     * Affiche un message indiquant que le chien aboie.
     */
    public void aboyer() {
        System.out.println("J'aboie");
    }

    /**
     * Méthode toString pour décrire le chien.
     * Utilise la description de la classe parente et ajoute la race.
     *
     * @return Une description textuelle du chien.
     */
    @Override
    public String toString() {
        return super.toString() + " et je suis de la race " + this.race;
    }
}
