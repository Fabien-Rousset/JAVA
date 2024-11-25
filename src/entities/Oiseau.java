package entities;

import java.time.LocalDate;

/**
 * Classe représentant un oiseau, qui est un type d'animal.
 * Chaque oiseau a un nombre de plumes, un nom, une espèce et un âge.
 */
public class Oiseau extends Animal {

    private int nombreDePlumes;

    /**
     * Constructeur de la classe Oiseau.
     * Prend en paramètre le nombre de plumes, le nom, l'espèce, et l'âge de l'oiseau.
     *
     * @param nombreDePlumes        Le nombre de plumes de l'oiseau (doit être supérieur ou égal à zéro).
     * @param nom                   Le nom de l'oiseau (doit commencer par une majuscule et ne pas être vide).
     * @param espece                L'espèce de l'oiseau (ne peut pas être vide).
     * @param age                   L'âge de l'oiseau (doit être positif).
     * @param dateDeNaissance
     * @throws ExoException Si les valeurs fournies ne respectent pas les contraintes définies.
     */
    public Oiseau(int nombreDePlumes, String nom, String espece, int age, LocalDate dateDeNaissance) throws ExoException {
        super(nom, age, espece, dateDeNaissance);  // Appel au constructeur de la classe parente Animal
        setNombreDePlumes(nombreDePlumes);  // Initialisation du nombre de plumes avec validation
    }

    public Oiseau(){};

    /**
     * Getter pour obtenir le nombre de plumes de l'oiseau.
     *
     * @return Le nombre de plumes de l'oiseau.
     */
    public int getNombreDePlumes() {
        return nombreDePlumes;
    }

    /**
     * Setter pour définir le nombre de plumes de l'oiseau.
     *
     * @param nombreDePlumes Le nombre de plumes de l'oiseau (doit être supérieur ou égal à zéro).
     * @throws ExoException Si le nombre de plumes est inférieur à zéro.
     */
    public void setNombreDePlumes(int nombreDePlumes) throws ExoException {
        if (nombreDePlumes < 0) {
            throw new ExoException("Le nombre de plumes ne peut pas être inférieur à zéro.");
        }
        this.nombreDePlumes = nombreDePlumes;
    }

    /**
     * Méthode pour que l'oiseau s'envole.
     * Affiche un message indiquant que l'oiseau vole.
     */
    public void senvoler() {
        System.out.println("Je vole");
    }

    /**
     * Méthode surchargée pour décrire comment l'oiseau mange.
     * Affiche le type de nourriture que l'oiseau mange.
     *
     * @param nourriture Le type de nourriture que l'oiseau mange.
     */
    @Override
    public void manger(String nourriture) {
        System.out.println("Je ne mange que " + nourriture);
    }

    /**
     * Méthode surchargée pour décrire comment l'oiseau dort.
     * Affiche un message indiquant que l'oiseau dort dans un nid.
     */
    @Override
    public void dormir() {
        System.out.println("Je dors dans un nid");
    }

    /**
     * Méthode toString pour décrire l'oiseau.
     * Utilise la description de la classe parente et ajoute le nombre de plumes.
     *
     * @return Une description textuelle de l'oiseau.
     */
    @Override
    public String toString() {
        return super.toString() + " et j'ai " + this.nombreDePlumes + " plumes ";
    }
}
