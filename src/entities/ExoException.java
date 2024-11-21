package entities;

/**
 * Classe représentant une exception personnalisée utilisée pour les contraintes dans l'application.
 * Cette exception est levée lorsque les conditions d'initialisation des objets ne sont pas respectées.
 */
public class ExoException extends Exception {

    /**
     * Constructeur de la classe ExoException.
     *
     * @param message Le message décrivant la cause de l'exception.
     */
    public ExoException(String message) {
        super(message);  // Appel au constructeur de la classe parente Exception pour initialiser le message d'erreur
    }
}
