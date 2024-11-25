package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import utilities.Regex;

import static utilities.Regex.DATE_FORMATTER;

/**
 * Classe représentant un animal abstrait.
 * Chaque animal a un nom, une espèce, et un âge.
 */
public abstract class Animal {

    private String nom;
    private String espece;
    private int age;
    private LocalDate dateDeNaissance;

    /**
     * Constructeur de la classe Animal.
     * Initialise le nom, l'espèce, et l'âge de l'animal.
     *
     * @param nom    Le nom de l'animal (doit commencer par une majuscule et ne pas être vide).
     * @param age    L'âge de l'animal (doit être positif).
     * @param espece L'espèce de l'animal (ne peut pas être vide).
     * @throws ExoException Si les valeurs fournies ne respectent pas les contraintes définies.
     */
    public Animal(String nom, int age, String espece, LocalDate dateDeNaissance) throws ExoException {
        setNom(nom);                // Initialisation du nom avec validation
        setEspece(espece);          // Initialisation de l'espèce avec validation
        setAge(age);                // Initialisation de l'âge avec validation
        setDateNaissance(dateDeNaissance); // Initialisation de la date de naissance avec validation
    }

    public Animal() {

    }

    // Getter pour la date de naissance
    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    // Setter pour la date de naissance
    public void setDateNaissance(LocalDate date) {
        this.dateDeNaissance = date;
    }




    /**
     * Getter pour obtenir le nom de l'animal.
     *
     * @return Le nom de l'animal.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour définir le nom de l'animal.
     *
     * @param nom Le nom de l'animal (doit commencer par une majuscule et ne pas être vide).
     * @throws ExoException Si le nom est vide ou ne commence pas par une majuscule.
     */
//    public void setNom(String nom) throws ExoException {
//        if (nom == null || nom.isEmpty()) {
//            throw new ExoException("Le nom ne peut pas être vide.");
//        }
//        if (!Character.isUpperCase(nom.charAt(0))) {
//            throw new ExoException("Le nom doit commencer par une majuscule.");
//        }
//        this.nom = nom;
//    }

    public void setNom(String nom) throws ExoException {
        // Vérifie si le nom correspond à l'expression régulière définie dans Regex
        if (nom == null || !Regex.PATTERN_NOM.matcher(nom).matches()) {
            throw new ExoException("Nom invalide : Le nom doit commencer par une majuscule, ne contenir que des lettres, et comporter au moins 2 caractères.");
        }
        this.nom = nom;
    }



    /**
     * Getter pour obtenir l'espèce de l'animal.
     *
     * @return L'espèce de l'animal.
     */
    public String getEspece() {
        return espece;
    }


//    public void setEspece(String espece) throws ExoException {
//        if (espece == null || espece.isEmpty()) {
//            throw new ExoException("L'espèce ne peut pas être vide.");
//        }
//        this.espece = espece;
//    }

    public void setEspece(String espece) throws ExoException {
        // Vérifie si l'espèce correspond à l'expression régulière définie dans Regex
        if (espece == null || !Regex.PATTERN_ESPECE.matcher(espece).matches()) {
            throw new ExoException("Espece invalide : L'espece doit commencer par une majuscule, ne contenir que des lettres, et comporter au moins 3 caractères.");
        }
        this.espece = espece;
    }

    /**
     * Getter pour obtenir l'âge de l'animal.
     *
     * @return L'âge de l'animal.
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter pour définir l'âge de l'animal.
     *
     * @param age L'âge de l'animal (doit être positif).
     * @throws ExoException Si l'âge est négatif.
     */
    public void setAge(int age) throws ExoException {
        if (age < 0) {
            throw new ExoException("L'âge ne peut pas être négatif.");
        }
        this.age = age;
    }

    /**
     * Méthode pour décrire comment l'animal mange.
     *
     * @param nourriture Le type de nourriture que l'animal peut manger.
     */
    protected void manger(String nourriture) {
        System.out.println("Je peux manger " + nourriture);
    }

    /**
     * Méthode abstraite pour décrire comment l'animal dort.
     * Cette méthode doit être implémentée par les classes filles.
     */
    public abstract void dormir();

    /**
     * Méthode toString pour décrire l'animal.
     *
     * @return Une description textuelle de l'animal.
     */
    @Override
    public String toString() {
        return "Je m'appelle " + this.nom + ", j'ai " + this.age + " ans." + " ma date de naissance est: " + this.dateDeNaissance + "je suis de l'espece: " + this.espece;
    }
}