package Logging;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

/**
 * MonLogger est une classe utilitaire pour gérer la configuration du logger.
 * Elle permet d'initialiser un logger avec un FileHandler pour écrire les messages de log dans un fichier spécifique.
 */
public class MonLogger {
    // Déclarez le Logger en tant qu'attribut de classe constant
    public static final Logger LOGGER = Logger.getLogger(MonLogger.class.getName());
    private static FileHandler fileHandler = null;

    /**
     * Méthode pour initialiser le logger, pouvant être rappelée dans d'autres classes.
     * Elle configure le logger pour écrire les messages dans un fichier, en utilisant un format simple.
     *
     * @throws IOException Si une erreur survient lors de l'initialisation du FileHandler.
     */
    public static void initialiserLogger() throws IOException {
        // Initialiser un FileHandler pour écrire les logs dans un fichier
        fileHandler = new FileHandler("src/Logging/MonLogFichier.log", true);

        // Ajouter un format simple pour les messages de log
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);

        // Désactiver les handlers parents pour éviter les logs sur la console par défaut
        LOGGER.setUseParentHandlers(false);

        // Ajouter le FileHandler au LOGGER
        LOGGER.addHandler(fileHandler);
    }
}
